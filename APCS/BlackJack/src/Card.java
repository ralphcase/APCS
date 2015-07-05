
public class Card {
    public int value;
    public char suit;
    
    public Card(int v, char s) {
        value = v;
        suit = s;
    }
    
    public String toString() {
        String output = "";
        if (value == 1) {
            output = "Ace";
        }
        else if (value == 11) {
            output = "Jack";
        }
        else if (value == 12) {
            output = "Queen";
        }
        else if (value == 13) {
            output = "King";
        }
        else {
            output = output + value;
        }
        
        return output + " of " + suit;
    }
}
