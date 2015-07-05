// FormulaCell contains a text formula that is evaluated when needed.
public class FormulaCell extends Cell implements Evaluatable
{
	private String value;
	private Spreadsheet sheet;

	public FormulaCell(String string, Spreadsheet spreadsheet) throws CellParseException 
	{
		// Just save the formula when we create a cell. We'll evaluate it when we get the value.
		super(string);
		if (string.trim().startsWith("(") && string.trim().endsWith(")")) 
		{
			// This looks like a formula.
			// Remove the leading and trailing parentheses.
			value = string.substring(1);
			value = value.substring(0, value.length() - 1).trim();
			sheet = spreadsheet;
		} 
		else 
		{
			// This doesn't look like a formula.
			throw new CellParseException();
		}
	}
	
	public String toString()
	{
		return display(evaluate().toString());
	}
	
	public Double evaluate()
	{
		// Evaluate the formula and return the value.
		Double total = 0.0;
		int count = 0;
		
		// Parse the formula to see what kind of formula it is.
		String delims = "[ ]+";
		String[] tokens = value.split(delims);
		if ((tokens[0].equals("sum")) || (tokens[0].equals("avg")))
		{
			// Find the sum or average of a range.
			Range range = sheet.getRange(tokens[1], tokens[3]);
			
			// Loop through all the cells in the range and accumulate the total.
			for (int r = range.top; r <= range.bottom; r++)
				for (int c = range.left; c <= range.right; c++)
				{
					total = total + ((Evaluatable) sheet.getSheet()[c][r]).evaluate();
					count++;
				}
			if (tokens[0].equals("avg"))
			{
				total = total / count;
			}
		}
		else
		{
			// This is an arithmetic formula.
			total = parseOperand(tokens[0]);
			// TODO: This evaluates left-to-right. Should we respect traditional
			// order of operations?
			for (int i = 1; i < tokens.length; i++)
			{
				String operator = tokens[i];
				i++;
				double operand = parseOperand(tokens[i]);
				if (operator.equals("+"))
				{
					total = total + operand;
				}
				else if (operator.equals("-"))
				{
					total = total - operand;
				}
				else if (operator.equals("*"))
				{
					total = total * operand;
				}
				else if (operator.equals("/"))
				{
					total = total / operand;
				}
				else
				// error
				{
					System.out.println("Could not parse formula " + value);
				}
			}
		}
		return total;
	}

	// Parse an operand in a formula. An operand may be either a number or a cell reference.
	private Double parseOperand(String input)
	{
		double operand;
		try
		{
			// Try to parse the operand as a number.
			operand = Double.parseDouble(input);
		}
		catch (NumberFormatException e)
		{
			operand =  ((Evaluatable) sheet.getCell(input)).evaluate();
		}

		return operand;
	}

}
