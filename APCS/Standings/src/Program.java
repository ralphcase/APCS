import java.util.ArrayList;

public class Program
{

	private static int compareCount;

	public static void main(String[] args)
	{
		// Create the league.
		League d = new League("American League");
		System.out.println(d);

		// Find a specific team.
		int ind = getIndex(d.getTeams(), "Seattle");
		System.out.printf("found at %d: %s \n", 
				ind, d.getTeams().get(ind).getNickname());

		// Sort the teams and see the sorted list.
		d.sort();
		System.out.println(d);

		compareCount = 0;
		// Two different ways to find the target team.
		// ind = getIndex(d.getTeams(), "Oakland");
		ind = getIndexBinary(d.getTeams(), "Oakland");
		
		System.out.printf("found at %d: %s \n", 
				ind, d.getTeams().get(ind).getNickname());
		System.out.println("number of compares: " + compareCount);
	}

	public static int getIndex(ArrayList<Team> teams, String city)
	{
		// Loop through all the teams sequentially.
		for (int i = 0; i < teams.size(); i++)
		{
			Team t = teams.get(i);
			compareCount++; // Not part of search. Just used to see how many
							// times we get here.
			if (t.getCity().equalsIgnoreCase(city))
				return i;
		}
		// Not found, return -1
		return -1;
	}

	public static int getIndexBinary(ArrayList<Team> teams, String city)
	{
		int min = 0;
		int max = teams.size() - 1;
		while (min <= max)
		{
//			System.out.printf("%d, %d\n", min, max);

			int mid = (min + max) / 2;
			compareCount++;
			int compare = teams.get(mid).getCity().compareToIgnoreCase(city);
			if (compare == 0)	// Found what we were looking for!
				return mid;
			if (compare < 0)	// Search the right half of the current range.
				min = mid + 1;
			else // (compare > 0)  Search the left half of the current range.
				max = mid - 1;
		}
		// Not found, return -1
		return -1;
	}

}
