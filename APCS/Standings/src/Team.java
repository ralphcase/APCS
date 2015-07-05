	public class Team implements Comparable<Team> {
	private String city;
	private String nickname;
	public String getNickname()
	{
		return nickname;
	}

	private int wins;
	private int losses;

	public Team(String c, String n) {
		city = c;
		nickname = n;
	}

	public void won() {
		wins++;
	}

	public void lost() {
		losses++;
	}

	public void setRecord(int w, int l) {
		if (w < 0)
			w = 0;
		if (l < 0)
			l = 0;
		wins = w;
		losses = l;
	}
	
	public String getCity() {
		return this.city;
	}

	public String toString() {
		return city + " " + nickname + ": \t" + wins + "-" + losses + "... \t"
				+ getPct();
	}

	// return winning percentage
	public double getPct() {
		if (wins == 0)
			return 0.0;
		return wins / (double) (wins + losses);
	}

	@Override
	public int compareTo(Team arg) {
		// We want the highest winning % to be first.
		// A "normal" sort places lower values before higher.
		// So this looks "backwards" from a "normal" sort.
		if (this.getPct() < arg.getPct())
			return 1;
		if (this.getPct() > arg.getPct())
			return -1;
		// return 0;
		// Don't just return 0 if the records are the same.
		// We need some tie-breaker so that the ordering is
		// "complete" for all possible Teams.
		// Use the city name for now, although they might
		// not be unique across all divisions!
		return this.city.compareTo(arg.city);
	}
}
