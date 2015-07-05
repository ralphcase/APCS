import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// DateCell contains a date.
public class DateCell extends Cell
{
	// The format used for representing a date.
	private SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");

	private Date value;

	public DateCell(String string) throws CellParseException
	{
		super(string);
		try
		{
			value = myFormat.parse(string);
		}
		catch (ParseException e)
		{
			throw new CellParseException();
		}
	}

	public String toString()
	{
		return display(myFormat.format(value));
		// return display(String.format("%1$tm/%1$td/%1$tY", value));
	}
	
	
	
	
	
/*
 * Tests
 */
	
	public static void main(String[] args)
	{
		try
		{
			Cell c;
			c = new DateCell("03/7/1996");
			System.out.println("length: " + c.toString().length());
			System.out.println("value: " + c.toString());
			TestEqual(" 03/07/1996 |", c.toString());

			c = new DateCell("1/27/1996");
			System.out.println("value: " + c.toString());
			TestEqual(" 01/27/1996 |", c.toString());

			c = new DateCell("12/25/14");
			System.out.println("value: " + c.toString());
			TestEqual(" 12/25/0014 |", c.toString());
		}
		catch (CellParseException e)
		{
			System.out.println("FAIL! Could not parse date value.");
			e.printStackTrace();
		}
		
		try
		{
			Cell c = new DateCell("error");
			System.out.println("FAIL! Incorrect DateCell creation: " + c.toString());
		}
		catch (CellParseException e)
		{
			System.out.println("Correct DateCell parse error handling");
		}
	}

	private static void TestEqual(String expected, String actual)
	{
		if (!expected.equals(actual))
		{
			System.out.println("FAIL! expected:"+expected+" actual:"+actual);
		}
	}
}
