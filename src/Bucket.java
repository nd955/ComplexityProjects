
public class Bucket {
	
	private double capacity;
	private int index;
	
	public Bucket(double num, int i)
	{
		capacity = num;
		index = i;
	}
	public double getCapacity()
	{
		return capacity;
	}
	public int getIndex()
	{
		return index;
	}

}
