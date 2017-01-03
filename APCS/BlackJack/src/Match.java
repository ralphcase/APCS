import java.util.Scanner;

public class Match {
	private static final int MAXHAND = 21;
	private static int firstPlayer = 0; // a switch to alternate play order in
										// each Match.
	private Player players[]; 	// The players in the Match
	private Scanner input; 		// get input from the user

	public Match(Player[] p, Scanner in) {
		players = p;
		input = in;
		// Switch the first Player to the other one from last time.
		firstPlayer++;
		firstPlayer %= 2;
	}

	// Play one match and return the winner.
	public Player[] play() {
		// Create and shuffle a new deck for this match.
		Deck deck = new Deck();
		deck.shuffle();

		// Empty the players' hands
		for (Player current : players)
			current.getHand().empty();

		// Deal the initial hands - two cards each
		for (int i = 1; i <= 2; i++)
			for (Player current : players)
				current.getHand().addCard(deck.draw());

		// Let both players play
		for (int i = 0; i < players.length; i++)
			processPlayer((firstPlayer + i) % 2, deck);

		return getWinners();
	}

	// Return the winning sum - the highest hand not over 21.
	private int getWinningSum() {
		int winningSum = -1;
		for (Player current : players) {
			int sum = current.getHand().getSum();
			if (sum <= MAXHAND && sum > winningSum)
				winningSum = sum;
		}
		return winningSum;
	}

	// return an array of players that had the given sum.
	private Player[] getWinners() {
		int winningSum = getWinningSum();
		// No one won. Return an array with 0 players.
		if (winningSum == -1)
			return new Player[0];

		// count the winning players and create an array of the correct size.
		int count = 0;
		for (Player current : players) {
			if (current.getHand().getSum() == winningSum)
				count++;
		}
		Player[] winners = new Player[count];

		// Now fill in the array with all the winners.
		int index = 0;
		for (Player current : players) {
			if (current.getHand().getSum() == winningSum) {
				winners[index] = current;
				index++;
			}
		}
		return winners;
	}

	// Allow one player to play the game.
	private void processPlayer(int index, Deck deck) {
		Player currentPlayer = players[index];
		System.out.println("==============");

		// Show the hands.
		for (Player p : players)
			System.out.println(p.getName() + " shows: " + p.getHand().getTopCard());
		System.out.println();
		System.out.println(currentPlayer.getName() + ", it is your turn.");

		boolean keepAsking = true;
		while (keepAsking)
			keepAsking = executePlayerActions(currentPlayer, deck);
	}

	// Prompt the player for an action, and check to see if the player can still
	// play.
	private boolean executePlayerActions(Player current, Deck deck) {
		boolean result = true;
		System.out.println(current.getName() + ", you have " + current.getHand());
		System.out.println("Do you want to [h]it or [s]tand? ");
		String action = input.next();
		if (action.charAt(0) == 'h') {
			// player chose to hit, so draw a card and add it to the hand.
			Card newCard = deck.draw();
			System.out.println("You drew " + newCard.toString());
			current.getHand().addCard(newCard);
			if (current.getHand().getSum() > MAXHAND) {
				System.out.println("Bust!");
				result = false;
			}
		} else
			result = false;
		return result;
	}
}
