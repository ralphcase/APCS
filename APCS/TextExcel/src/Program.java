import java.util.Scanner;

import persistence.PersistenceHelper;

// Name
// AP Computer Science 
// Project 2 - TextExcel
// Milestone 1
// Extra Credit so far:
//   * Order of Operations
//   * Error handling and help command

// This is the main entry point for the TextExcel project.
public class Program {

	public static void main(String[] args) 
	{
		System.out.println("Welcome to TextExcel!");
		
		Scanner sc = new Scanner(System.in);
		
		Spreadsheet theSheet = new Spreadsheet();
		
		boolean moreToDo = true;
		// Repeatedly get input from the user and process it until the user enters "exit".
		while (moreToDo)
		{
			System.out.println("Enter an command, or \"exit\" to end.");
			String input = sc.nextLine().trim();
			// System.out.println("You entered: " + input);
			if (input.equals("exit")) 
			{
				// The user entered "exit" so break out of here.
				moreToDo = false;
			}
			else if (input.equals("clear"))
			{
				theSheet = new Spreadsheet();	
			}
			else if (input.equals("print"))
			{
				theSheet.print();
			}
			else if (input.startsWith("load "))
			{
				String filePath = input.substring(5);
				try 
				{
					PersistenceHelper.load(filePath, theSheet);
				} 
				catch (Exception e) 
				{
					System.out.println("Could not load file.");
				}
			}
			else if (input.startsWith("save "))
			{
				String filePath = input.substring(5);
				try
				{
					PersistenceHelper.save(filePath, theSheet);
				}
				catch (Exception e)
				{
					System.out.println("Could not save file.");
				}
			}
			else 
			{
				// Look for a set or display command.
				String delims = "[ ]+";
				String[] tokens = input.split(delims);
				if (tokens.length == 1)
				{
					// Try to process as a display command.
					theSheet.displayInitial(tokens[0].trim());
				}
				else if ((tokens.length == 2) && tokens[0].equals("clear"))
				{
					theSheet.clear(tokens[1]);
				}
				else if ((tokens.length >= 3) && tokens[1].equals("="))
				{
					// Try to process as a set command.
					int right = input.indexOf('=') + 1;
					theSheet.set(tokens[0], input.substring(right).trim());
				}
				else if (tokens[0].startsWith("sort"))
				{
					// A sort command 
					theSheet.sort((tokens[0].equals("sorta")), tokens[1], tokens[3]);
				}
				else
				{
					System.out.println("Could not recognize that command.");
				}
			}
		}
		
		sc.close();
		System.out.println("Goodbye.");
	}

}
