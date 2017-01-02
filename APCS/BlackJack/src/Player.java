
public class Player {
	private Hand hand;
	private String name;
	private double wins = 0;

	public Player(String n) {
		name = n;
		hand = new Hand();
	}

	public void addWin() {
		wins++;
	}

	public String toString() {
		return name + ": " + hand + " (" + hand.getSum() + ")";
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public String getTally() {
		return name + " has " + wins + " wins.";
	}

	public void addTie() {
		// a tie is half a win
		wins += 0.5;
	}
}
