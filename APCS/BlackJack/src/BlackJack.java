import java.util.Scanner;

/**
 * This is the entry point for a Blackjack game.
 *
 */
public class BlackJack {
	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		// Store players in an array.
		Player[] players = new Player[2];

		System.out.println("Welcome to Blackjack!");
		System.out.print("Please enter the name of player 1: ");
		players[0] = new Player(input.next());
		System.out.print("Please enter the name of player 2: ");
		players[1] = new Player(input.next());

		boolean again = true;
		while (again) {
			play(players);
			System.out.print("Play again (y/n)? ");
			String action = input.next();

			if (action.charAt(0) != 'y')
				again = false;
		}
		input.close();
	}

	/**
	 * Play one match and update the players scores.
	 * 
	 * @param players
	 *            - an array of Player
	 */
	public static void play(Player[] players) {
		Match match = new Match(players, input);
		Player[] winner = match.play();
		if (winner.length > 1) {
			System.out.println("The game was a tie.");
			for (Player p : winner) {
				p.addTie();
			}
		} else if (winner.length == 0) {
			System.out.println("Both players lose!");
		} else {
			System.out.println(winner[0].getName() + " wins!");
			winner[0].addWin();
		}
		System.out.println();
		System.out.println(players[0].getScore());
		System.out.println(players[1].getScore());
	}
}
