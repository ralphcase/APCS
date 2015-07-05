import persistence.Savable;

// This class contains the data as a two-dimensional array of Cells
public class Spreadsheet implements Savable
{

	private static Cell[][] sheet;
	private static Cell[] colLabels;
	private static Cell[] rowLabels;
	private static int NumberOfCols = 7;
	private static int NumberOfRows = 10;
	private static String[] header = { "A", "B", "C", "D", "E", "F", "G" };

	public Spreadsheet()
	{
		// Create an array.
		sheet = new Cell[NumberOfCols][NumberOfRows];
		// Initialize it with empty cells.
		for (int row = 0; row < NumberOfRows; row++)
		{
			for (int col = 0; col < NumberOfCols; col++)
			{
				sheet[col][row] = new Cell();
			}
		}

		// Model the axis labels to be printed as one dimensional arrays of
		// StringCells.
		rowLabels = new Cell[NumberOfRows];
		for (int i = 0; i < NumberOfRows; i++)
		{
			// rowLabels[i] = new StringCell(String.format("\"%d\"", i+1));
			rowLabels[i] = new StringCell("\"" + ( i + 1 ) + "\"");
		}

		colLabels = new Cell[NumberOfCols];
		for (int i = 0; i < NumberOfCols; i++)
		{
			colLabels[i] = new StringCell("\"" + header[i] + "\"");
		}
	}

	public Cell[][] getSheet()
	{
		return sheet;
	}

	// Print out the whole spreadsheet.
	public void print()
	{
		System.out.print(new Cell());
		// Print the column labels.
		for (int col = 0; col < NumberOfCols; col++)
		{
			System.out.print(colLabels[col]);
		}
		System.out.println();
		for (int row = 0; row < NumberOfRows; row++)
		{
			// Print a row label.
			System.out.print(rowLabels[row]);
			// Print the cells in the row.
			for (int col = 0; col < NumberOfCols; col++)
			{
				System.out.print(sheet[col][row].toString());
			}
			System.out.println();
		}
	}

	public Cell getCell(String cellName)
	{
		CellAddress ca = getCellAddress(cellName);
		return sheet[ca.col][ca.row];
	}

	public void displayInitial(String cellName)
	{
		System.out.println(cellName + " = " + getCell(cellName).getInitialValue());
	}

	public void clear(String cellName)
	{
		// Clear the cell named.
		CellAddress ca = getCellAddress(cellName);
		sheet[ca.col][ca.row] = new Cell();
	}

	public void set(String cellName, String value)
	{
		CellAddress a = getCellAddress(cellName);
		set(a, value);
	}

	private void set(CellAddress a, String value)
	{
		// Try to create different types of cells.
		// If the value is not correct for the type, the constructor throws an
		// exception
		// and we can try another type.
		if (value.equals("<empty>"))
		{
			sheet[a.col][a.row] = new Cell();
			return;
		}
		try
		{
			sheet[a.col][a.row] = new DoubleCell(value);
			return;
		}
		catch (CellParseException e)
		{
			// Do nothing. Just try another type.
		}
		try
		{
			sheet[a.col][a.row] = new DateCell(value);
			return;
		}
		catch (CellParseException e)
		{
			// Do nothing. Just try another type.
		}
		try
		{
			// A formula cell needs to know the spreadsheet it's part of so it
			// can refer to other cells in that spreadsheet.
			sheet[a.col][a.row] = new FormulaCell(value, this);
			return;
		}
		catch (CellParseException e)
		{
			// Do nothing. Just try another type.
		}

		sheet[a.col][a.row] = new StringCell(value);
		return;
	}

	// Return the address of the cell named in the cellName.
	// If the value is null, return the cell as it is.
	// If the value is not null, use it to replace the cell with a new one.
	public CellAddress getCellAddress(String cellName)
	{
		int col = -1;
		int row = -1;
		// Find the column number
		for (int i = 0; i < NumberOfCols; i++)
		{
			if (cellName.subSequence(0, 1).equals(header[i]))
			{
				col = i;
			}
		}

		// Find the row number
		if (cellName.length() > 2)
		{
			row = Integer.parseInt(cellName.substring(1, 3)) - 1;
		}
		else
		{
			row = Integer.parseInt(cellName.substring(1)) - 1;
		}
		return new CellAddress(col, row);
	}

	// Collect the information needed to save all the cells.
	@Override
	public String[] getSaveData()
	{
		String[] saveData = new String[NumberOfRows * NumberOfCols];
		for (int r = 0; r < NumberOfRows; r++)
			for (int c = 0; c < NumberOfCols; c++)
				saveData[r * NumberOfCols + c] = sheet[c][r].getInitialValue();
		return saveData;
	}

	// Load the saveData into the cells of the spreadsheet.
	@Override
	public void loadFrom(String[] saveData)
	{
		for (int r = 0; r < NumberOfRows; r++)
			for (int c = 0; c < NumberOfCols; c++)
				set(new CellAddress(c, r), saveData[r * NumberOfCols + c]);
	}

	// Sort the values in a range of cells from cellName1 to CellName2.
	// If ascending is true, sort ascending, otherwise sort descending.
	public void sort(boolean ascending, String cellName1, String cellName2)
	{
		Range range = getRange(cellName1, cellName2);
		// Implements a selection sort

		// The outer loops go through the desired range
		for (int outr = range.top; outr <= range.bottom; outr++)
			for (int outc = range.left; outc <= range.right; outc++)
			{
				Double cursorValue = ((Evaluatable) sheet[outc][outr]).evaluate();
				// The inner loop checks from where we are to the end of the
				// input range.
				// First check the rest of the row we're on.
				for (int inc = outc + 1; inc <= range.right; inc++)
				{
					Double thisValue = ((Evaluatable) sheet[inc][outr]).evaluate();
					if ((ascending && (thisValue < cursorValue))
							|| (!ascending && (thisValue > cursorValue)))
					{
						// Swap the cells
						swap(outc, outr, inc, outr);
						cursorValue = thisValue;
					}
				}
				// Now check the rest of the rows.
				for (int inr = outr + 1; inr <= range.bottom; inr++)
					for (int inc = range.left; inc <= range.right; inc++)
					{
						Double thisValue = ((Evaluatable) sheet[inc][inr]).evaluate();
						if ((ascending && (thisValue < cursorValue))
								|| (!ascending && (thisValue > cursorValue)))
						{
							// Swap the cells
							swap(outc, outr, inc, inr);
							cursorValue = thisValue;
						}
					}
			}
	}

	// Swap the values of the two cells addressed by the column and row
	// parameters.
	private void swap(int c1, int r1, int c2, int r2)
	{
		try
		{
			String temp = sheet[c1][r1].getInitialValue();
			sheet[c1][r1] = new DoubleCell(sheet[c2][r2].getInitialValue());
			sheet[c2][r2] = new DoubleCell(temp);
		}
		catch (CellParseException e)
		{
			// We can't get here since the initial values were already validated
			// when the cells were initially created.
		}
	}

	public Range getRange(String cellName1, String cellName2)
	{
		return new Range(this, cellName1, cellName2);
	}

}
