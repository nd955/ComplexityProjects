
public class OneInThreeSATClause {

	Literal x1,x2,x3;
	
	public OneInThreeSATClause(Literal one, Literal two, Literal three)
	{
		x1 = one;
		x2 = two;
		x3 = three;
	}
	
	public boolean isSatisfied()
	{
		if(x1.isTrue() && !x2.isTrue() && !x3.isTrue())
			return true;
		else if(!x1.isTrue() && x2.isTrue() && !x3.isTrue())
			return true;
		else if(!x1.isTrue() && !x2.isTrue() && x3.isTrue())
			return true;
		else
			return false;
	}
	public Literal getX1()
	{
		return x1;
	}
	public Literal getX2()
	{
		return x2;
	}
	public Literal getX3()
	{
		return x3;
	}
	public boolean forceTruth()
	{
		if(x1.isTrue())
		{
			x2.setTruth(false);
			x3.setTruth(false);
			return true;
		}
		else if(x2.isTrue())
		{
			x1.setTruth(false);
			x3.setTruth(false);
			return true;
		}
		else if(x3.isTrue())
		{
			x1.setTruth(false);
			x2.setTruth(false);
			return true;
		}
		else if(!x1.isTrue() && !x2.isTrue())
		{
			x3.setTruth(true);
			return true;
		}
		else if(!x1.isTrue() && !x3.isTrue())
		{
			x2.setTruth(true);
			return true;
		}
		else if(!x2.isTrue() && !x3.isTrue())
		{
			x1.setTruth(true);
			return true;
		}
		return false;	//returns false if nothing was set
	}
	public boolean contains(Literal l)
	{
		if(x1.toString().equals(l.toString()))
			return true;
		else if(x2.toString().equals(l.toString()))
			return true;
		else if(x3.toString().equals(l.toString()))
			return true;
		else
			return false;
	}
	public String toString()
	{
		return "( " + x1 + " v " + x2 + " v " + x3 + " )";
	}
	
}
