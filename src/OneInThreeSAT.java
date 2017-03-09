import java.util.ArrayList;


public class OneInThreeSAT {

	ArrayList<Literal> x;
	ArrayList<OneInThreeSATClause> c;
	String alreadyOutput = "";
	
	public OneInThreeSAT(ArrayList<Literal> allLiterals, ArrayList<OneInThreeSATClause> clause)
	{
		x = allLiterals;
		c = clause;
	}
	//uses the fact that it takes 4 1in3sat clauses for every 1 3sat clause
	public ArrayList<Literal> forceAllTruth()
	{
		ArrayList<Literal> lits = new ArrayList<Literal>();
		for(int i = 0; i < c.size(); i += 4)
		{
			OneInThreeSATClause one = c.get(i);
			OneInThreeSATClause two = c.get(i + 1);
			OneInThreeSATClause three = c.get(i + 2);
			OneInThreeSATClause four = c.get(i + 3);
			
			if(!one.forceTruth())
			{
				if(!two.forceTruth())
				{
					three.forceTruth();
					//x3 = three.getX1();
					//c = three.getX2();
					//a = three.getX3();
					
					four.forceTruth();
					//b = four.getX2();
					
					one.forceTruth();
					//x1 = one.getX1();
					
					two.forceTruth();
					//x2 = two.getX1();
				}
				else
				{
					two.forceTruth();
					//x2 = two.getX1();
					//b = two.getX2();
					//c = two.getX3();
					
					four.forceTruth();
					//a = four.getX1();
					
					one.forceTruth();
					//x1 = one.getX1();
					
					three.forceTruth();
					//x3 = three.getX1();
				}
			}
			else
			{
				one.forceTruth();
				//x1 = one.getX1();
				//a = one.getX2();
				//b = one.getX3();
				
				four.forceTruth();
				//c = four.getX3();
				
				two.forceTruth();
				//x2 = two.getX1();
				
				three.forceTruth();
				//x3 = three.getX1();
			}
		}
		
		for(Literal l : x)
		{
			if(!l.isNegated())
			{
				if(l.toString().contains("x") && !alreadyOutput.contains(l.toString()))
				{
					lits.add(l);
					alreadyOutput += l.toString();
				}
			}
			else
			{
				Literal lPrime = new Literal(l.toString().replace("Not", ""), false);
				lPrime.setTruth(l.complement());
				if(!alreadyOutput.contains(lPrime.toString()))
				{
					lits.add(lPrime);
					alreadyOutput += lPrime.toString();					
				}
			}
				
		}
		return lits;
	}
	public SubsetSum reduceToSubsetSum()
	{
		ArrayList<Bucket> s = new ArrayList<Bucket>();
		double vi[][], viNot[][];
		double t=0;
		int countLiteral=0, countClause=0;
		vi = new double[x.size()][x.size() + c.size()];
		viNot = new double[x.size()][x.size() + c.size()];
		for(Literal l: x)
		{
			vi[countLiteral][countLiteral] = 1;
			viNot[countLiteral][countLiteral] = 1;
			countLiteral++;
		}
		for(OneInThreeSATClause clause: c)
		{
			countLiteral = 0;
			for(Literal l: x)
			{
				if(clause.contains(l) && !l.isNegated())
				{
					vi[countLiteral][x.size() + countClause] = 1;
				}
				if(clause.contains(l) && l.isNegated())
				{
					viNot[countLiteral][x.size() + countClause] = 1;
				}
				
				countLiteral++;
			}
			countClause++;
		}
		for(int i = 0; i < x.size(); i++)
		{
			for(int j = 0; j < x.size() + c.size(); j++)
			{
				if(vi[i][j] != 0)
				{
					Bucket b = new Bucket(vi[i][j], i + 1);
					s.add(b);
				}
				if(viNot[i][j] != 0)
				{
					Bucket bNot = new Bucket(viNot[i][j], i + 1);	
					s.add(bNot);
				}
			}
		}
		for(int i = 0; i < x.size() + c.size(); i++)
		{
			t += 1;
		}
		
		SubsetSum subSum = new SubsetSum(s, t);
		return subSum;
	}
	public void interpretSubsetSumDynamicProgrammingNW()
	{
		SubsetSum subSum = reduceToSubsetSum();
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

		this.forceAllTruth();
	}
	public String printTruthAssignment()
	{
		String s = "";
		for(Literal l : x)
		{
			if(l.isNegated())
			{
				s += l.toString().replace("Not", "") + ": " + l.isTrue() + "\n";
			}
			else
				s += l.toString() + ": " + l.isTrue() + "\n";
		}
		return s;
	}
	public String toString()
	{
		String s = "";
		for(OneInThreeSATClause clause: c)
		{
			s += clause + "\n";
		}
		
		return s;
	}
}
