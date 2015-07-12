import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Program {
	
	private static int atackers = 16;
	private static int[] defenders = { 3, 5, 2};

	public static void main(String[] args) {
		multiTrial(100000, atackers, defenders);

	}

	// Run a trial rep times and print a summary.
	static void multiTrial(int rep, int attackers, int[] defenders) {
		int rem;
		int victories = 0, defeats = 0;
		int armies = 0;
		int count = 0;
		int sumSquares = 0;
		Boolean done = false;
		StatModel previous = new StatModel(0, 0, 0);
		while (!done) {
			rem = trial(attackers, defenders);
			count++;
			if (rem == 0)
				defeats++;
			else
				victories++;
			if (count >= rep)
				done = true;
			armies = armies + rem;
			sumSquares += rem * rem;

			StatModel current = new StatModel(count, armies, sumSquares);

//			System.out.printf("Percent: %.2f %s",
//					(double) victories / count, 
//					current.ToString());
			if (current.IsCloseTo(previous))
				done = true;

			previous = current;

			if (count < 20)
				done = false;

		}
		StringBuilder message = new StringBuilder(20);
		message.append(victories).append(" wins with ")
				.append((float) armies / victories).append(" left. ");
		// Console.WriteLine(victories + " wins with " + (float)armies/victories
		// + " left.");
		message.append(defeats + " defeats\n");
		message.append(String.format("winning: %.1f%%", 100*(double) victories / (victories + defeats)));
		System.out.println(message);
		
	}

	private static int trial(int attackers, int[] defenders) {
		return trial(attackers, defenders, 1);
	}

	// Run a trial of attackers invading a series of countries with defenders.
	// Return the number of armies that occupy the final country, or 0 for
	// failure.
	static int trial(int attackers, int[] defenders, int toRemain) {
		int rem = attackers;
		try {
			for (int i = 0; i < defenders.length; i++)
				rem = attack(rem, defenders[i]) - toRemain;
		} catch (DefeatException de) {
			System.out.println("failed - ");
			rem = 0;
		}
		System.out.println("remaining:" + rem);
		return rem;
	}

	// Run one battle.
	// Return the number of attackers remaining.
	// Throw exception for defeat.
	private static int attack(int attackers, int defenders)
			throws DefeatException {
		List<Integer> attackRoll, defendRoll;
		while (defenders > 0 && attackers > 1) {
			// Roll the correct number of attacking dice.
			if (attackers <= 3)
				attackRoll = roll(attackers - 1);
			else
				attackRoll = roll(3);
			// Roll the correct number of defending dice.
			if (defenders == 1)
				defendRoll = roll(1);
			else
				defendRoll = roll(2);
			// Compare the two rolls.
			while (attackRoll.size() > 0 && defendRoll.size() > 0) {
				if (attackRoll.get(0) > defendRoll.get(0))
					defenders--;
				else
					attackers--;
				attackRoll.remove(0);
				defendRoll.remove(0);
			}
		}
		if (attackers <= 1)
			throw new DefeatException();
		return attackers;
	}

	// Roll a number of dice and return a sorted list of the values.
	private static List<Integer> roll(int num) {
		List<Integer> result = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int r = 1 + rand.nextInt(6);
			result.add(r);
		}
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}

}
