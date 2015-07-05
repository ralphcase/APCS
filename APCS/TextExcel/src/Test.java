// This is a second entry point for the TextExcel project.
// It just calls various methods with test data to validate the implementations.
public class Test
{

	public static void main(String[] args)
	{
		// size of a cell should be 13 (counting the separator).
		Cell c = new Cell();
		System.out.println("length: " + c.toString().length());
		System.out.println("value: " + c.toString());
		TestEqual("            |", c.toString());

		// long string should be truncated.
		c = new StringCell("\"this is a longer string\"");
		System.out.println("length: " + c.toString().length());
		System.out.println("value: " + c.toString());
		TestEqual("this is a l>|", c.toString());

		// short string should be centered.
		c = new StringCell("\"Aha!\"");
		System.out.println("length: " + c.toString().length());
		System.out.println("value: " + c.toString());
		TestEqual("    Aha!    |", c.toString());

		DateCell.main(args);

		try
		{
			c = new DoubleCell("6");
			System.out.println("length: " + c.toString().length());
			System.out.println("value: " + c.toString());
			TestEqual("6.0     |", c.toString().trim());

			c = new DoubleCell(
					"-3.1415926535897932384626433832795028841971693993751058209749445");
			System.out.println("value: " + c.toString());
			TestEqual("-3.14159265>|", c.toString().trim());

			c = new DoubleCell("999.123456789");
			System.out.println("value: " + c.toString());
			TestEqual("999.1234567>|", c.toString().trim());

		}
		catch (CellParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Spreadsheet s;
		s = new Spreadsheet();

		try
		{
			c = new FormulaCell("( 3 / 2 + 4 )", s);
			System.out.println("value: " + c.toString());
			TestEqual("    5.5     |", c.toString());

			c = new FormulaCell("( 1.1 * 3 - 78 )", s);
			System.out.println("value: " + c.toString());
			TestEqual("-74.7    |", c.toString().trim());

			c = new FormulaCell("( 2 / 3 )", s);
			System.out.println("value: " + c.toString());
			TestEqual("0.666666666>|", c.toString().trim());
		}
		catch (CellParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s.displayInitial("A1");
		s.displayInitial("A10");
		s.displayInitial("G1");
		s.displayInitial("G10");
		s.set("A7", "3.4");
		s.displayInitial("A7");
		s.displayInitial("A7");
		s.set("C3", "-234.567689111");
		s.displayInitial("C3");
		s.set("F2", "\" a long string that won't fit\"");
		s.displayInitial("F2");
		s.set("A4", "\" \"quote\" \"");
		s.displayInitial("A4");
		s.print();

		s.clear("A4");
		s.displayInitial("A4");

		s = new Spreadsheet();
		s.set("A1", "1");
		s.set("B1", "2");
		s.set("C1", "3");
		s.set("A2", "4");
		s.set("B2", "5");
		s.set("C2", "6");
		s.set("A4", "( A1 )");
		s.set("B4", "( A1 + C1 / 3 )");
		s.set("C4", "( A4 - B4 * -1 )");
		s.set("A5", "( sum A1 - C2 )");
		s.set("B5", "( avg A1 - C2 )");
		s.set("C5", "( sum A1 - C1 )");
		s.set("D3", "( sum B2 - C1 )");
		s.print();

		// sort....
		s = new Spreadsheet();
		s.set("B3", "100");
		s.set("C3", "2");
		s.set("D3", "10");
		s.set("E3", "200");
		s.set("F3", "2");
		s.set("D6", "5");
		s.set("D7", "60");
		s.set("D8", "500");
		s.set("D9", "6");
		s.set("D10", "5");
		s.print();
		s.sort(true, "B3", "F3");
		s.sort(true, "D6", "D10");
		s.print();
		s.sort(false, "B3", "F3");
		s.sort(false, "D6", "D10");
		s.print();

		s = new Spreadsheet();
		s.set("A1", "\"Optional\"");
		s.set("B2", "7");
		s.set("C2", "23");
		s.set("D2", "-2.5");
		s.set("B3", "15");
		s.set("C3", "0");
		s.set("D3", "7");
		s.print();
		s.sort(true, "B2", "D3");
		s.print();
		s.sort(false, "B2", "D3");
		s.print();
		
		
	}

	private static void TestEqual(String string, String string2)
	{
		if (!string.equals(string2))
		{
			System.out.println("FAIL!");
		}
	}

}
