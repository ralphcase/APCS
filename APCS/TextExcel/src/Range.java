// Helper class to model a rectangular range of cells
public class Range
{
	// A rectangle has 4 sides:
	public int top, bottom, left, right;
	
	// Create the range from the text form of the cell names (e.g. "C5")
	public Range(Spreadsheet sheet, String cellName1, String cellName2)
	{
		CellAddress corner1 = sheet.getCellAddress(cellName1);
		CellAddress corner2 = sheet.getCellAddress(cellName2);
		if (corner1.row < corner2.row)
		{
			top = corner1.row;
			bottom = corner2.row;
		}
		else 
		{
			top = corner2.row;
			bottom = corner1.row;
		}
		if (corner1.col < corner2.col)
		{
			left = corner1.col;
			right = corner2.col;
		}
		else 
		{
			left = corner2.col;
			right = corner1.col;
		}
	}
}
