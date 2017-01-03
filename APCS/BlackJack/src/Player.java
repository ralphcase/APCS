
public class Player {
	private Hand hand;
	private String name;
	private double wins = 0;	// double because a tie is half a win

	public Player(String n) {
		name = n;
		hand = new Hand();
	}

	public void addWin() {
		wins++;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public String getScore() {
		return name + " has " + wins + " wins.";
	}

	public void addTie() {
		// a tie is half a win
		wins += 0.5;
	}
}
