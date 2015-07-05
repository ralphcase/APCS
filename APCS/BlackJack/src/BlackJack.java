import java.util.Scanner;

public class BlackJack
{
	static Scanner input;
	public static void main(String[] args)
	{
		input = new Scanner(System.in);
		Player[] players = new Player[2];

		System.out.println("Welcome to BlackJack!");
		System.out.print("Please enter the name of player 1: ");
		players[0] = new Player(input.next());
		System.out.print("Please enter the name of player 2: ");
		players[1] = new Player(input.next());

		boolean again = true;
		while (again)
		{
			play(players);
			System.out.print("Play again (y/n)? ");
			String action = input.next();

			if (action.charAt(0) != 'y')
				again = false;
		}
		input.close();
	}

	public static void play(Player[] players)
	{
		Match match = new Match(players, input);
		Player[] winner = match.play();
		if (winner.length > 1)
		{
			System.out.println("The game was a tie.");
			// TODO do we need to add a half point to each?
		}
		else
		{
			System.out.println(winner[0].getName() + " wins!");
			winner[0].addWin();
		}

		System.out.println(players[0].name + " has " + players[0].wins
				+ " wins");
		System.out.println(players[1].name + " has " + players[1].wins
				+ " wins");

	}
}
