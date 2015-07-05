import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Division {

	private String name;
	private ArrayList<Team> teams;

	public Division(String n) {
		name = n;
		teams = new ArrayList<Team>();
	}

	public void addTeam(Team t) {
		teams.add(t);
	}

	public String toString() {
		// Print the division name
		String result = name + "\n";
		
		// Now print all the teams...
		Iterator<Team> div = teams.iterator();
		while (div.hasNext()) {
			Team t = div.next();
			result += t.toString();
			result += "\n";
		}
		
		return result;
	}

	public void sort() {
		// teams.sort(null);  // Works in Java 8, but not Java 7.
		Collections.sort(teams);
	}

}
