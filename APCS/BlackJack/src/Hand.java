
public class Hand {
    public Card[] cards = new Card[52];
    public int cardCount = 0;
    
    public void addCard(Card card) {
        cards[cardCount] = card;
        cardCount++;
    }
    
    public void empty() {
        cardCount = 0;
    }
    
    public int getSum() {
        int sum = 0;
        for (int i = 0; i < cardCount; i++) {
            if (cards[i].value > 10) {
                sum += 10;
            }
            else {
                sum += cards[i].value;
            }
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

	public Card getTopCard()
	{
		return cards[0];
	}
}
