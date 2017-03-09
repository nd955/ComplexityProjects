import java.util.ArrayList;
import java.util.Collections;


public class ThreeSAT {

	ArrayList<Literal> x;
	ArrayList<ThreeSATClause> c;
	
	public ThreeSAT(ArrayList<Literal> allLiterals, ArrayList<ThreeSATClause> clause)
	{
		x = allLiterals;
		c = clause;
	}
	
	public OneInThreeSAT reduceToOneInThreeSAT()
	{
		ArrayList<OneInThreeSATClause> oneInThreeSATClauses = new ArrayList<OneInThreeSATClause>();
		ArrayList<Literal> oneInThreeSATLiterals = new ArrayList<Literal>();
		ArrayList<Literal> extraOneInThreeSATLiterals = new ArrayList<Literal>();
		int count = 1;
		
		for(ThreeSATClause clause: c)
		{
			OneInThreeSATClause one, two, three, four;
			Literal x1, x2, x3, a, b, c;
			x1 = clause.getX1();
			x2 = clause.getX2();
			x3 = clause.getX3();
			a = new Literal("a" + count, false);
			b = new Literal("b" + count, false);
			c = new Literal("c" + count, false);
			one = new OneInThreeSATClause(x1,b,c);
			two = new OneInThreeSATClause(a,x2,c);
			three = new OneInThreeSATClause(a,b,x3);
			four = new OneInThreeSATClause(a,b,c);
			
			oneInThreeSATClauses.add(one);
			oneInThreeSATClauses.add(two);
			oneInThreeSATClauses.add(three);
			oneInThreeSATClauses.add(four);
			
			oneInThreeSATLiterals.add(x1);
			oneInThreeSATLiterals.add(x2);
			oneInThreeSATLiterals.add(x3);
			extraOneInThreeSATLiterals.add(a);
			extraOneInThreeSATLiterals.add(b);
			extraOneInThreeSATLiterals.add(c);

			count++;
		}
		//orders the x's in increasing order
		Collections.sort(oneInThreeSATLiterals);
		//makes sure x's are in front
		for(Literal l : extraOneInThreeSATLiterals)
		{
			oneInThreeSATLiterals.add(l);
		}
		OneInThreeSAT instance = new OneInThreeSAT(oneInThreeSATLiterals, oneInThreeSATClauses);
		return instance;
	}
	public ArrayList<Literal> useDynamicProgrammingNW()
	{
		OneInThreeSAT oneInThreeSAT = reduceToOneInThreeSAT();
		SubsetSum subSum = oneInThreeSAT.reduceToSubsetSum();
		MaxKnapsack maxKnap = subSum.convertToMaxKnap();
		Knapsack knap = maxKnap.dynamicProgrammingNW(maxKnap.getItemList());
		ArrayList<Bucket> bucketSolutions = new ArrayList<Bucket>();

		for(Item i : knap.getItemList())
		{
			Bucket b = new Bucket(i.getCost(), i.getIndex());
			bucketSolutions.add(b);
		}

		for(Bucket b : bucketSolutions)
		{
			for(Literal l : x)
			{
				if(l.toString().contains(Integer.toString((b.getIndex()))))
				{
					l.setTruth(true);
				}
			}
		}

		ArrayList<Literal> assignedLits = oneInThreeSAT.forceAllTruth();
		return assignedLits;
	}
	public ArrayList<Literal> usePseudoPolyTime()
	{
		OneInThreeSAT oneInThreeSAT = reduceToOneInThreeSAT();
		SubsetSum subSum = oneInThreeSAT.reduceToSubsetSum();
		MaxKnapsack maxKnap = subSum.convertToMaxKnap();
		Knapsack knap = maxKnap.pseudoPolyTimeDynamicProgramming(maxKnap.getItemList());
		ArrayList<Bucket> bucketSolutions = new ArrayList<Bucket>();

		for(Item i : knap.getItemList())
		{
			Bucket b = new Bucket(i.getCost(), i.getIndex());
			bucketSolutions.add(b);
		}

		for(Bucket b : bucketSolutions)
		{
			for(Literal l : x)
			{
				if(l.toString().contains(Integer.toString((b.getIndex()))))
				{
					l.setTruth(true);
				}
			}
		}

		ArrayList<Literal> assignedLits = oneInThreeSAT.forceAllTruth();
		return assignedLits;
	}
	public String printTruthAssignment(ArrayList<Literal> lits)
	{
		String s = "";
		String allVars = "";
		for(Literal literal : x)
		{
			if(!literal.isNegated())
				allVars += literal;
			else
				allVars += literal.toString().replace("Not", "");
		}
		for(Literal l : lits)
		{
			if(!l.isNegated())
			{
				if(allVars.contains(l.toString()))
				{
					s += l + ":\t" + l.isTrue() + "\r\n";
					allVars.replace(l.toString(), "");
				}
			}
			else
			{
				if(allVars.contains(l.toString().replace("Not", "")))
				{
					s += l.toString().replace("Not", "") + ":\t" + l.complement() + "\r\n";
					allVars.replace(l.toString().replace("Not", ""), "");
				}
			}
		}
		return s;
	}
	public String toString()
	{
		String s = "";
		for(ThreeSATClause clause: c)
		{
			s += clause + "\r\n";
		}
		
		return s;
	}
}
