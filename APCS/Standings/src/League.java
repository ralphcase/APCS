import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

public class League {
    private ArrayList<Team> teams;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    private String name;

    public League(String n) {
        teams = new ArrayList<Team>();
        add(new Team("Kansas City", "Royals"));
        add(new Team("New York", "Yankees"));
        add(new Team("Boston", "Red Sox"));
        add(new Team("Oakland", "A's"));
        add(new Team("Houston", "Astros"));
        add(new Team("Detroit", "Tigers"));
        add(new Team("Seattle", "Mariners"));
        add(new Team("Texas", "Rangers"));
        add(new Team("Minnesota", "Twins"));
        add(new Team("Chicago", "White Sox"));
        add(new Team("Cleveland", "Indians"));
        add(new Team("Tampa Bay", "Rays"));
        add(new Team("Baltimore", "Orioles"));
        add(new Team("Los Angeles", "Angels"));
        add(new Team("Toronto", "Blue Jays"));

        name = n;
    }

    public void add(Team t) {
        teams.add(t);
    }

    public String toString() {
        Iterator<Team> d = teams.iterator();
        String result = name + "\n";

        while (d.hasNext()) {
            Team t = d.next();
            result += t.toString();
            result += "\n";
        }
        return result;
    }

    // Sort the teams using the compareTo() method in Team.
    public void sort() {
        Collections.sort(teams);
    }
    
    public void sort(Comparator<Team> comparator) {
        Collections.sort(teams, comparator);
    }
}
