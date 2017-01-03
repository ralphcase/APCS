
public class Card {
	// Model a card as an int from 1 to 13 for the rank and a char for the suit.
	private int rank;
	private char suit;
	
	private static final char[] SUITS = new char[] { 'S', 'C', 'D', 'H' };
	
	public static char[] getAllSuits() {
		return SUITS;
	}

	public Card(int r, char s) {
		rank = r;
		suit = s;
	}

	/**
	 * Apply the point rules for the Blackjack game: - Ace is always worth 1. -
	 * Face cards are worth 10.
	 * 
	 * @return the point value of the card
	 */
	public int getPoints() {
		if (rank > 10)
			return 10;
		else
			return rank;
	}

	public String toString() {
		String output = "";
		if (rank == 1) {
			output = "Ace";
		} else if (rank == 11) {
			output = "Jack";
		} else if (rank == 12) {
			output = "Queen";
		} else if (rank == 13) {
			output = "King";
		} else {
			output = output + rank;
		}
		output += " of ";
		String s;
		if (suit == 'C')
			s = "Clubs";
		else if (suit == 'D')
			s = "Diamonds";
		else if (suit == 'S')
			s = "Spades";
		else
			s = "Hearts";
		return output + s;
	}
}
