import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards = new ArrayList<Card>();

	// No constructor needed - just use the default

	public void addCard(Card card) {
		cards.add(card);
	}

	public Card getTopCard() {
		return cards.get(0);
	}

	public void empty() {
		cards.clear();
	}

	/**
	 * Get the total value of all the cards in the hand
	 * @return the total points
	 */
	public int getSum() {
		int sum = 0;
		for (Card c : cards) {
			sum += c.getPoints();
		}
		return sum;
	}

	public String toString() {
		String output = "[";
		for (int i = 0; i < cards.size() - 1; i++) {
			output += cards.get(i) + ", ";
		}

		if (cards.size() > 0) {
			output += cards.get(cards.size() - 1);
		}
		output += "] (" + getSum() + ")";
		return output;
	}
}
