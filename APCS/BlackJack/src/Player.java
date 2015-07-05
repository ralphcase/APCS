
public class Player {
    public Hand hand;
    public String name;
    public int wins = 0;
    
    public Player(String n) {
        name = n;
        hand = new Hand();
    }
    public int addWin() {
        wins++;
        return wins;
    }
    
    public String toString() {
        return name + ": " + hand + " (" + hand.getSum() + ")";
    }
	public String getName()
	{
		return name;
	}
	public Hand getHand()
	{
		return hand;
	}
}
