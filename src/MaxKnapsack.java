import java.sql.PseudoColumnUsage;
import java.util.ArrayList;


public class MaxKnapsack {

	private double maxBudget;
	private double currBudget = 0;
	private Knapsack itemList;
	private Knapsack knapsack;
	
	public MaxKnapsack(double maxB, Knapsack list)
	{
		maxBudget = maxB;
		itemList = list;
		knapsack = new Knapsack(new ArrayList<Item>());
	}
	
	public Knapsack getItemList()
	{
		return itemList;
	}
	public Knapsack getKnapsack()
	{
		return knapsack;
	}
	public Knapsack dynamicProgrammingNW(Knapsack items)
	{
		Knapsack choices = new Knapsack(new ArrayList<Item>());
		double b = maxBudget;
		
		double v[][] = new double[items.getItemList().size()+1][(int) (maxBudget+1)];
		boolean taken[][] = new boolean[items.getItemList().size()+1][(int) (maxBudget+1)];
		
		for(int i = 0; i <= b; i++)
		{
			v[0][i] = 0;
		}
		for(int i = 1; i <= items.getItemList().size(); i++)
		{
			for(int j = 0; j <= b; j++)
			{
				if((j - (items.get(i-1).getCost())) >= 0 && v[i-1][(int) (j-(items.get(i-1).getCost()))] + items.get(i-1).getValue() > 
				v[i-1][j])
				{
					v[i][j] = v[i-1][(int) (j-(items.get(i-1).getCost()))] + items.get(i-1).getValue();
					taken[i][j] = true;
				}
				else
				{
					v[i][j] = v[i-1][j];
					taken[i][j] = false;
				}
			}
		}
		
		for(int i = items.getItemList().size(); i >= 1 ; i--)
		{
			if(b > 0)
			{
				if(taken[i][(int) b])
				{
					choices.add(items.get(i-1));
					b -= items.get(i-1).getCost();
				}
			}
		}
		
		return choices;
		
	}
	
	public Knapsack pseudoPolyTimeDynamicProgramming(Knapsack items)
	{
		Knapsack choices = new Knapsack(new ArrayList<Item>());
		Item amax = items.getMaxValue();
		
		double c[][] = new double[items.getItemList().size()+1][(int) (items.getItemList().size() * amax.getValue()+1)];
		boolean taken[][] = new boolean[items.getItemList().size()+1][(int) (items.getItemList().size() * amax.getValue()+1)];
		
		for(int i = 0; i <= items.getItemList().size(); i++)
		{
			c[i][0] = 0;
		}
		for(int i = 0; i <= items.getItemList().size() * amax.getValue(); i++)
		{
			c[0][i] = 0;
		}
		for(int i = 1; i <= items.getItemList().size() * amax.getValue(); i++)
		{
			if(items.get(0).getValue()>i)
			{
				c[1][i] = items.get(0).getCost();
				taken[1][i] = true;
			}
			else
			{
				c[1][i] = Double.POSITIVE_INFINITY;
				taken[1][i] = false;
			}
		}
		for(int i = 2; i <= items.getItemList().size(); i++)
		{
			for(int j = 1; j <= items.getItemList().size() * amax.getValue(); j++)
			{
				int nextT = (int) (j-(items.get(i-1).getValue()));
				if(nextT < 0)
					nextT = 0;
				if(c[i-1][j] < items.get(i-1).getCost() + c[i-1][nextT])
				{
					c[i][j] = c[i-1][j];
					taken[i][j] = false;
				}
				else
				{
					if(items.get(i-1).getCost() + c[i-1][nextT] > 0)
					{
						c[i][j] = items.get(i-1).getCost() + c[i-1][nextT];
						taken[i][j] = true;
					}
					else
					{
						c[i][j] = -(items.get(i-1).getCost() + c[i-1][nextT]);
						taken[i][j] = true;
					}
				}
			}
		}
		int highestT = (int) (items.getItemList().size() * amax.getValue());
		while(highestT > 0 && c[items.getItemList().size()][highestT] > maxBudget)
		{
			highestT--;
		}

		for(int i = items.getItemList().size(); i >= 1; i--)
		{
			if(highestT >= 0 && taken[i][highestT])
			{
				choices.add(items.get(i-1));
				highestT -= items.get(i-1).getValue();
			}
		}
		
		return choices;
	}
	
	public Knapsack greedyAlgorithm(Knapsack items)
	{
		Knapsack choices = new Knapsack(new ArrayList<Item>());
		Knapsack sortedItems = new Knapsack(items.getItemList());
		sortedItems = sortedItems.sortDecreasing();
		double l = maxBudget;
		
		for(Item i: sortedItems.getItemList())
		{
			if(l > 0)
			{
				if(i.getCost() <= l)
				{
					choices.add(i);
					l -= i.getCost();
				}
			}
		}
		
		Item amax = sortedItems.getMaxValue();
		if(amax.getValue() > choices.totalValue() && amax.getCost() <= maxBudget)
		{
			Knapsack aKnap = new Knapsack(new ArrayList<Item>());
			aKnap.add(amax);
			return aKnap;
		}
		else
			return choices;
	}
	
	public Knapsack fullyPolynomialTimeApproximation(Knapsack items, double errorTolerance)
	{
		Knapsack choices = new Knapsack(new ArrayList<Item>());
		Item amax = items.getMaxValue();
		double vmax = amax.getValue();
		ArrayList<Item> itemsCopy = new ArrayList<Item>();
		int count = 0;
		
		for(Item i : items.getItemList())
		{
			itemsCopy.add(new Item(i.getCost(), (int)Math.floor(i.getValue()/(errorTolerance*vmax/items.getItemList().size())),items.get(count).getIndex()));
			count++;
		}
		
		Knapsack scaledKnap = new Knapsack(itemsCopy);
		
		choices = this.pseudoPolyTimeDynamicProgramming(scaledKnap);
		
		count = 0;
		for(Item i : choices.getItemList())
		{
			i.setValue(items.get(count).getValue());
			count++;
		}
		
		return choices;		
	}
	
}
