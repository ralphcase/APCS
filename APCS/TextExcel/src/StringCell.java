// StringCell holds a test string
public class StringCell extends Cell 
{

	private String value;
		
	public StringCell(String string) 
	{
		super(string);
		// Remove the initial and final quote.
		value = string.substring(1);
		value = value.substring(0, value.length()-1);
	}
	
	public String toString()
	{
		return display(value);
	}

}
