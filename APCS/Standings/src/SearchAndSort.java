import java.util.ArrayList;

public class SearchAndSort
{

	// Book keeping: Curious how long each of the sorts actually takes
	private static int comparisonCount = 0;

	public static void main(String[] args)
	{
		// Create the league
		League league = new League("American");

		// Check out what is in the league; note that the values are unsorted,
		// so we will have to perform linear search
		System.out.println(league);

		String cityName = "Tampa Bay";
		int index = findIndex(league, cityName);
		if (index != -1)
		{
			System.out.println("Found " + cityName + " @ index " + index);
			System.out.println("Comparison Count: " + comparisonCount);
		}

		// Sort the league so that searches can be done with binary search
		league.sort();
		System.out.println(league);

		// Use binary search to find the city
		cityName = "Tampa Bay";
		index = findIndexBinaryRecursive(league, cityName);
		if (index != -1)
		{
			System.out.println("Found " + cityName + " @ index " + index);
			System.out.println("Comparison Count: " + comparisonCount);
		}
	}

	/**
	 * Searches for a team with the specified city name in the League. Return
	 * the index in the league, or -1 if the league does not contain the city
	 * 
	 * @param league
	 *            The league to search through
	 * @param targetCity
	 *            The name of the city to search for
	 * @return the index of the Team from the specified city if found; -1
	 *         otherwise
	 */
	private static int findIndex(League league, String targetCity)
	{
		comparisonCount = 0;

		// Go through the entire list of teams in the league, looking for a team
		// for which the city matches the required name
		ArrayList<Team> teams = league.getTeams();
		for (int i = 0; i < teams.size(); i++)
		{
			comparisonCount++;
			Team currentTeam = teams.get(i);
			String currentCity = currentTeam.getCity();
			if (currentCity.compareToIgnoreCase(targetCity) == 0)
			{
				return i;
			}
		}

		// The team was not found, so return -1
		return -1;
	}

	/**
	 * Searches for a team with the specified city name in the League. Return
	 * the index in the league, or -1 if the league does not contain the city.
	 * It is assumed the league has teams in sorted order (based upon the team's
	 * city name)
	 * 
	 * @param league
	 *            The league to search through
	 * @param targetCity
	 *            The name of the city to search for
	 * @return the index of the Team from the specified city if found; -1
	 *         otherwise
	 */
	private static int findIndexBinary(League league, String targetCity)
	{
		comparisonCount = 0;

		ArrayList<Team> teams = league.getTeams();
		int left = 0;
		int right = teams.size() - 1;

		// When left > right, there are no elements left to look at.
		while (left <= right)
		{
			// Find the midpoint of the range of values currently under
			// investigation
			// Grab the team and the city to make the comparison
			int middle = (right + left) / 2; // Think about this: why is this
												// the "middle" value?
			Team currentTeam = teams.get(middle);
			String currentCity = currentTeam.getCity();

			// Find out how the middle team's city compares to the target city
			comparisonCount++;
			int comparisonResult = currentCity.compareToIgnoreCase(targetCity);
			if (comparisonResult == 0)
			{
				// A match was found! Return this index
				return middle;
			}
			else if (comparisonResult < 0)
			{
				// The current city is less than the target city.
				// We need to continue the search in the "right" part of the
				// elements under investigation
				// Update the left index and keep searching
				left = middle + 1;
			}
			else
			{
				// The current city is greater than the target city.
				// We need to continue the search in the "left" part of the
				// elements under investigation
				// Update the right index and keep searching
				right = middle - 1;
			}
		}

		// The team in that city is not found; return -1
		return -1;
	}

	/**
	 * Searches for a team with the specified city name in the League. Return
	 * the index in the league, or -1 if the league does not contain the city.
	 * It is assumed the league has teams in sorted order (based upon the team's
	 * city name)
	 * 
	 * @param league
	 *            The league to search through
	 * @param targetCity
	 *            The name of the city to search for
	 * @return the index of the Team from the specified city if found; -1
	 *         otherwise
	 */
	private static int findIndexBinaryRecursive(League league, String targetCity)
	{
		comparisonCount = 0;

		ArrayList<Team> teams = league.getTeams();
		int left = 0;
		int right = teams.size() - 1;

		return findIndexBinaryRecursive(teams, targetCity, left, right);
	}

	// Recursive method for the Recursive algorithm.
	private static int findIndexBinaryRecursive(ArrayList<Team> teams,
			String targetCity, int left, int right)
	{
		// Find the midpoint of the range of values currently under
		// investigation
		// Grab the team and the city to make the comparison
		int middle = (right + left) / 2; 
		Team currentTeam = teams.get(middle);
		String currentCity = currentTeam.getCity();

		// Find out how the middle team's city compares to the target city
		comparisonCount++;
		int comparisonResult = currentCity.compareToIgnoreCase(targetCity);
		if (comparisonResult == 0)
		{
			// A match was found! Return this index
			return middle;
		}
		else if (left == right)
		{
			// We've searched all the elements in the given range and not found it.
			// This is the recursive base case
			return -1;
		}
		if (comparisonResult < 0)
		{
			// The current city is less than the target city.
			// We need to continue the search in the "right" part of the
			// elements under investigation
			// Update the left index and keep searching
			return findIndexBinaryRecursive(teams, targetCity, middle + 1, right);
		}
		else
		{
			// The current city is greater than the target city.
			// We need to continue the search in the "left" part of the elements
			// under investigation
			// Update the right index and keep searching
			return findIndexBinaryRecursive(teams, targetCity, left, middle - 1);
		}
	}
}
