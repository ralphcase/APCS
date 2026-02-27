import java.util.Scanner;

public class Program
{
	//
	// FracCalc implements an AP Computer Science class project.
	// Ralph Case 2014/07/03
	//
	public static void main(String[] args)
	{
		System.out.println("Welcome to FracCalc!");

		Scanner sc = new Scanner(System.in);

		boolean moreToDo = true;
		// Repeatedly get input from the user and process it until the user
		// enters "quit".
		while (moreToDo)
		{
			System.out.println("Enter an equation, or \"quit\" to end.");
			String input = sc.nextLine().trim();
			// System.out.println("You entered: " + input);
			if (input.equalsIgnoreCase("quit"))
			{
				// The user entered "quit" so break out of here.
				moreToDo = false;
			}
			else if (input.equalsIgnoreCase("test"))
			{
				runTests();
			}
			else
			{
				// The input looks like a fraction statement. Process it.
				processStatement(input);
			}
		}
		sc.close();
		System.out.println("Goodbye.");
	}

	private static void runTests()
	{
		printFraction(0, 2);
		printFraction(3, -4);
		printFraction(-7, 3);
		printFraction(8, 2);
		printFraction(504, 924);

		processStatement("1/4 + 1_1/2");
		processStatement(" 8/4 + 2 ");
		processStatement("-1 * -1/2 ");
		processStatement(" 11/17 - -1/17");
		processStatement("1/2 + 2/3");
		processStatement(" 1 + 2 * 3 ");
		// processStatement("1 + + 1/2");
		processStatement("4/3 / 1/2 + 3_2/7 ");
		processStatement(" 6/7 - 2_3/4 * 1_1/2");
		processStatement("3/8");
	}

	/*
	 * This method does the main work of parsing an arithmetic statement with
	 * fractions and then printing the answer.
	 */
	private static void processStatement(String input)
	{
		// A space separates the terms of the statement.
		// Get the terms.
		String delims = "[ ]+";
		String[] tokens = input.trim().split(delims);

		if (tokens.length < 3)
		{
			System.out.println("That doesn't look like a statement.");
		}
		else
		{
			// Finish parsing the terms of the statement.

			// Fractions are stored in improper form as just a numerator and a
			// denominator.
			// To handle order of operations correctly, we look at the first
			// three fractions and the first
			// two operators in the statement:
			// left fraction
			// left operator
			// middle fraction
			// right operator
			// right fraction

			int leftNumerator;
			int leftDenominator;
			int middleNumerator;
			int middleDenominator;
			int rightNumerator;
			int rightDenominator;
			String leftOperator;
			String rightOperator;

			int cursor = 0; // cursor points to the current token in the input

			// A valid statement must have at least two fractions separated by
			// an operator.
			// Get the initial values.
			parseFraction(tokens[cursor]);
			leftNumerator = outNumerator;
			leftDenominator = outDenominator;
			cursor++;
			leftOperator = tokens[cursor];
			cursor++;
			parseFraction(tokens[cursor]);
			middleNumerator = outNumerator;
			middleDenominator = outDenominator;
			cursor++;

			// Loop through additional operations.
			// Each operation has one operator and one fraction operand.
			boolean moreOperations = true;
			boolean calcOK = false;

			while (moreOperations)
			{
				if (tokens.length <= cursor)
				{
					// There are no more operations, so process what we have.
					moreOperations = false;
					calcOK = calculate(leftNumerator, leftDenominator,
							middleNumerator, middleDenominator, leftOperator);
				}
				else
				{
					// Get the next operator.
					rightOperator = tokens[cursor];
					cursor++;
					// Get the next fraction.
					parseFraction(tokens[cursor]);
					rightNumerator = outNumerator;
					rightDenominator = outDenominator;
					cursor++;

					// Check the order of operations.
					if ((leftOperator.equals("+") || (leftOperator.equals("-")))
							&& (rightOperator.equals("*") || rightOperator
									.equals("/")))
					{
						// The right operator has higher precedence, so do it
						// first.
						calcOK = calculate(middleNumerator, middleDenominator,
								rightNumerator, rightDenominator, rightOperator);
						// Save the answer as the new middle fraction.
						middleNumerator = outNumerator;
						middleDenominator = outDenominator;
					}
					else
					{
						// The left operator has higher or equal precedence, so
						// do it first.
						calcOK = calculate(leftNumerator, leftDenominator,
								middleNumerator, middleDenominator,
								leftOperator);
						// Save the answer as the new left fraction. The right
						// fraction is now the middle.
						leftNumerator = outNumerator;
						leftDenominator = outDenominator;
						leftOperator = rightOperator;
						middleNumerator = rightNumerator;
						middleDenominator = rightDenominator;
					}
				}
			}

			if (calcOK)
			{
				printFraction(outNumerator, outDenominator);
			}
		}
	}

	// Global variables to store the results of parseFraction() or calculate().
	static int outNumerator;
	static int outDenominator;

	// Parses a fraction string.
	// Sets the global variables outNumerator and outDenominator.
	private static void parseFraction(String input)
	{
		// parse left fraction
		int under = input.indexOf('_');
		int slash = input.indexOf('/');
		if (under > 0)
		{
			// There is a '_', so this is a mixed number.
			int whole = Integer.parseInt(input.substring(0, under));
			outNumerator = Integer.parseInt(input.substring(under + 1, slash));
			// If the whole part is negative, that applies to the fraction part
			// too.
			if (whole < 0)
				outNumerator = 0 - outNumerator;
			outDenominator = Integer.parseInt(input.substring(slash + 1));
			outNumerator = outNumerator + whole * outDenominator;
		}
		else if (slash > 0)
		{
			// There is a '/', so this is a fraction.
			outNumerator = Integer.parseInt(input.substring(0, slash));
			outDenominator = Integer.parseInt(input.substring(slash + 1));
		}
		else
		{
			// This is a whole number.
			outNumerator = Integer.parseInt(input);
			outDenominator = 1;
		}
	}

	// Process one fraction calculation.
	// The result is stored in the global variables outNumerator, and
	// outDenominator
	// Return false if the operator is not valid.
	private static boolean calculate(int num1, int denom1, int num2,
			int denom2, String operator)
	{
		if (operator.equals("+"))
		{
			outNumerator = num1 * denom2 + num2 * denom1;
			outDenominator = denom1 * denom2;
		}
		else if (operator.equals("-"))
		{
			outNumerator = num1 * denom2 - num2 * denom1;
			outDenominator = denom1 * denom2;
		}
		else if (operator.equals("*"))
		{
			outNumerator = num1 * num2;
			outDenominator = denom1 * denom2;
		}
		else if (operator.equals("/"))
		{
			outNumerator = num1 * denom2;
			outDenominator = denom1 * num2;
		}
		else
		{
			System.out.println("Unrecognized operator " + operator);
			return false;
		}
		return true;
	}

	// Simplify and print out a fraction.
	private static void printFraction(int numerator, int denominator)
	{
		// Make sure the denominator is positive.
		if (denominator < 0)
		{
			denominator = 0 - denominator;
			numerator = 0 - numerator;
		}
		if (numerator < 0)
		{
			// This is a negative number, so print the '-'.
			System.out.print("-");
			numerator = 0 - numerator;
		}
		// Simplify by finding the GCD of the parts.
		int gcd = findGcd(numerator, denominator);
		numerator = numerator / gcd;
		denominator = denominator / gcd;

		// Now simplify to a mixed number.
		int whole = numerator / denominator;
		numerator = numerator % denominator;

		// Now print out the answer.
		if (numerator == 0)
		{
			// There's no fraction, so just print the whole part.
			System.out.println(whole);
		}
		else
		{
			if (whole > 0)
			{
				// Print the whole part, if there is one.
				System.out.print(whole + "_");
			}
			// Print the fraction part.
			System.out.println(numerator + "/" + denominator);
		}
	}

	// Return the Greatest Common Divisor of two ints.
	// Euclid's algorithm
	private static int findGcd(int a, int b)
	{
		while (true) {
			if (a < b) {
				int t = a;
				a = b;
				b = t;
			}
			if (b == 0)
				return a;
			a = a % b;
		}
	}
}