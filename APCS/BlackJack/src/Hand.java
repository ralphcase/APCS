
public class Hand {
	private Card[] cards = new Card[52];
	private int cardCount = 0;

	// No constructor needed - just use the default

	public void addCard(Card card) {
		cards[cardCount] = card;
		cardCount++;
	}

	public void empty() {
		cardCount = 0;
	}

	/**
	 * Get the total value of all the cards in the hand
	 * 
	 * @return the total points
	 */
	public int getSum() {
		int sum = 0;
		for (int i = 0; i < cardCount; i++) {
			sum += cards[i].getPoints();
		}
		return sum;
	}

	public String toString() {
		String output = "[";
		for (int i = 0; i < cardCount - 1; i++) {
			output += cards[i] + ", ";
		}

		if (cardCount > 0) {
			output += cards[cardCount - 1];
		}
		output += "] ( " + getSum() + ")";
		return output;
	}

	public Card getTopCard() {
		return cards[0];
	}
}
