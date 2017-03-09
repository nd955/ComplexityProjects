
public class Item {

	private double cost;
	private double value;
	private int index;
	
	public Item(double c, double v, int i)
	{
		cost = c;
		value = v;
		index = i;
	}
	
	public double getCost()
	{
		return cost;
	}
	public double getValue()
	{
		return value;
	}
	public int getIndex()
	{
		return index;
	}
	public void setValue(double newValue)
	{
		value = newValue;
	}
	public String toString()
	{
		String s = "Cost: " + cost + "\t" + "Value: " + value;
		
		return s;
	}
	
}
