import java.util.ArrayList;

public class SubsetSum {
	
	ArrayList<Bucket> bucketList; 
	double targetNumber;
	
	public SubsetSum(ArrayList<Bucket> buckets, double target)
	{
		bucketList = buckets;
		targetNumber = target;
	}
	
	public MaxKnapsack convertToMaxKnap()
	{
		double budget = targetNumber;
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		for(Bucket b: bucketList)
		{
			if(b.getCapacity() != 0)
			{
				Item i = new Item(b.getCapacity(), b.getCapacity(), b.getIndex());
				itemList.add(i);
			}
		}
		
		Knapsack knap = new Knapsack(itemList);
		MaxKnapsack maxKnap = new MaxKnapsack(budget, knap);
		return maxKnap;
	}
	public ArrayList<Bucket> getBuckets()
	{
		return bucketList;
	}
	public String toString()
	{
		String s = "Target Value: \n" + targetNumber + "\nBucket Capacities:\n";
		for(Bucket b : bucketList)
		{
			s += b.getCapacity() + "\n";
		}
		
		return s;
	}
}
