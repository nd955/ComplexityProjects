
public class Literal implements Comparable<Literal>{
	
	private boolean x;
	private boolean complement;
	private String name;
	private boolean not;	//true if negated, false if not
	
	public Literal(String s, boolean n)
	{
		name = s;
		not = n;
	}
	
	public void setTruth(boolean lit)
	{
		if(!not)
		{
			x = lit;
			complement = !lit;
		}
		else
		{
			complement = lit;
			x = !lit;
		}
	}
	public boolean isNegated()
	{
		return not;
	}
	public boolean complement()
	{
		if(!not)
			return complement;
		else
			return x;
	}
	public boolean isTrue()
	{
		if(!not)
			return x;
		else
			return complement;
	}
	public String toString()
	{
		return name;
	}
	public int compareTo(Literal l) {
		return toString().compareTo(l.toString());
	}
}
