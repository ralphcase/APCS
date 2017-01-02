import java.util.Random;

public class Deck {
	private static final char[] SUITS = new char[] { 'S', 'C', 'D', 'H' };
	private Card[] cards = new Card[52];
	private int currentCard = 0;

	public Deck() {
		// Fill the deck with one of each kind of card.
		int index = 0;
		for (char suit : SUITS) {
			for (int i = 1; i <= 13; i++) {
				cards[index] = new Card(i, suit);
				index++;
			}
		}
	}

	public void shuffle() {
		// Loop through each card in the deck and swap it with a random card.
		Random rand = new Random();
		for (int i = cards.length - 1; i >= currentCard + 1; i--) {
			int index = rand.nextInt(i - currentCard);
			Card temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}
	}

	/**
	 * Draw the next card from the deck.
	 * 
	 * @return a Card
	 */
	public Card draw() {
		Card returnCard = cards[currentCard];
		currentCard++;
		return returnCard;
	}
}
