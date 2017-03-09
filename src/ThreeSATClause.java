
public class ThreeSATClause {

	Literal x1,x2,x3;
	
	public ThreeSATClause(Literal one, Literal two, Literal three)
	{
		x1=one;
		x2=two;
		x3=three;
	}
	
	public boolean isSatisfied()
	{
		if(x1 != null && x2 != null && x3 != null)
		{
			if(x1.isTrue() || x2.isTrue() || x3.isTrue())
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public int numLiteralsSatisfied()
	{
		int count = 0;
		if(x1 != null && x1.isTrue())
			count++;
		if(x2 != null && x2.isTrue())
			count++;
		if(x3 != null && x3.isTrue())
			count++;
		return count;
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
	public String toString()
	{
		return "( " + x1 + " v " + x2 + " v " + x3 + " )";
	}
}
