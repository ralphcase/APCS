// DoubleCell is a cell that contains a double floating point number.
public class DoubleCell extends Cell implements Evaluatable
{
	private double value;

	public DoubleCell(String string) throws CellParseException
	{
		super(string);
		try
		{
			value = Double.parseDouble(string);
		}
		catch (NumberFormatException e)
		{
			throw new CellParseException();
		}
	}

	public String toString()
	{
		return display(Double.toString(value).trim());
	}

	public Double evaluate()
	{
		return value;
	}

}
