import java.util.ArrayList;


public class Knapsack {

	private ArrayList<Item> itemList;
	
	public Knapsack(ArrayList<Item> list)
	{
		itemList = list;
	}
	
	public ArrayList<Item> getItemList()
	{
		return itemList;
	}
	public double totalCost()
	{
		double total = 0;
		for(Item i : itemList)
		{
			total += i.getCost();
		}
		return total;
	}
	public double totalValue()
	{
		double total = 0;
		for(Item i : itemList)
		{
			total += i.getValue();
		}
		return total;
	}
	public Knapsack add(Item i)
	{
		itemList.add(i);
		return this;
	}
	public Item remove(Item i)
	{
		itemList.remove(i);
		return i;
	}
	public Item remove(int i)
	{
		Item j = itemList.remove(i);
		return j;
	}
	public Item get(int i)
	{
		return itemList.get(i);
	}
	public boolean isEmpty()
	{
		return itemList.isEmpty();
	}
	public Item getMaxCost()
	{
		Item max = itemList.get(0);
		for(Item i: itemList)
		{
			if(max.getCost() < i.getCost())
				max = i;
		}
		return max;
	}
	public Item getMinCost()
	{
		Item min = itemList.get(0);
		for(Item i: itemList)
		{
			if(min.getCost() > i.getCost())
				min = i;
		}
		return min;
	}
	public Item getMaxValue()
	{
		if(!itemList.isEmpty())
		{
			Item max = itemList.get(0);
			for(Item i: itemList)
			{
				if(max.getValue() < i.getValue())
					max = i;
			}
			return max;
		}
		else 
			return null;
	}
	public Item getMinValue()
	{
		Item min = itemList.get(0);
		for(Item i: itemList)
		{
			if(min.getValue() > i.getValue())
				min = i;
		}
		return min;
	}
	public Item getMinValueOverCost()
	{
		Item min = itemList.get(0);
		for(Item i: itemList)
		{
			if((double)min.getValue()/(double)min.getCost() < (double)i.getValue()/(double)i.getCost())
				min = i;
		}
		return min;
	}
	public Knapsack sortDecreasing()
	{
		int limit = itemList.size()-1;
		ArrayList<Item> itemListCopy = new ArrayList<Item>();
		itemListCopy.addAll(itemList);
		Knapsack knap = new Knapsack(itemListCopy);
		Knapsack sortedKnap = new Knapsack(new ArrayList<Item>());
		for(int i = 0; i <= limit; i++)
			sortedKnap.add(knap.remove(knap.getMinValueOverCost()));
		return sortedKnap;
	}
	public String toString()
	{
		String s = "KNAPSACK CONTENTS: \r\n";
		for(Item i: itemList)
		{
			s += "Cost: " + i.getCost() + "\t" + "Value: " + i.getValue() + "\r\n";
		}
		s += "TOTAL COST: \r\n";
		s += totalCost();
		s += "\r\n";
		s += "TOTAL VALUE : \r\n";
		s += totalValue();
		s += "\r\n";
		return s;
	}
}
