// Cell is the root class for all types of Cells used in this project.
// It is not abstract since we use this base class for an empty cell.
public class Cell
{

	private String initialValue; // The initial value used to create the cell.
	private int cellSize = 12;

	// Simple constructor for an empty cell. An empty cell has no type.
	public Cell()
	{
		initialValue = "<empty>";
	}

	//  Base constructor for cells of different subclasses
	public Cell(String string)
	{
		initialValue = string;
	}

	public String toString()
	{
		return display(" ");
	}

	// Format a string to display in a fixed size cell.
	public String display(String value)
	{
		String display;
		int len = value.length();
		if (len == cellSize)
		{
			// The value is the right size, so use it.
			display = value;
		}
		else if (len > cellSize)
		{
			// The value is too long, so truncate it and add an indicator that
			// it has been truncated.
			display = value.substring(0, cellSize - 1) + ">";
		}
		else
		{
			// The value is too short, so pad the beginning and end to center
			// the value.
			int pad = (cellSize - len) / 2;
			String spaces = "";
			for (int i = 0; i < pad; i++)
			{
				spaces = spaces + " ";
			}
			display = spaces + value + spaces;
			if ((cellSize - len) % 2 == 1)
			{
				display = display + " ";
			}
		}
		return display + "|";
	}

	public String getInitialValue()
	{
		return initialValue;
	}

	// evaluate() is not implemented for all cell types, but it is implemented for all types where it is needed.
	// An interface could be used to declare this, but defining this in the base class allows the compiler to 
	// allow it for all class types.
//	public Double evaluate()
//	{
//		System.out.println("Should not get here. evaluate() is not implemented for all cell types.");
//		return 0.0;
//	}
}
