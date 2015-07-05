import java.util.Random;

public class Deck {
    public Card[] cards = new Card[52];
    public int currentCard = 0;
    
    public Deck() {
        char[] suits = new char[] { 'S', 'C', 'D', 'H' };
        int index = 0;
        for (char suit : suits) {
            for (int i = 1; i <= 13; i++) {
                cards[index] = new Card(i, suit);
                index++;
            }
        }
    }
    
    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i >= currentCard + 1; i--) {
            int index = rand.nextInt(i - currentCard);
            Card temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
    }
    
    public Card nextCard() {
        Card returnCard = cards[currentCard];
        currentCard++;
        return returnCard;
    }
}
