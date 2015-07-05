import java.util.ArrayList;

public class Program
{
	public static ArrayList<Integer> piles = new ArrayList<Integer>();

	public static void main(String[] args)
	{
		initialize();
		System.out.println(display());
		while (!isDone())
		{
			turn();
			System.out.println(display());	
		}
	}

	public static void initialize()
	{
		int remaining = 45;
		while (remaining > 0)
		{
			int v = (int) (1 + (Math.random() * remaining));
			piles.add(v);
			remaining -= v;
		}
	}

	public static String display()
	{
		String result = "";
		for (Integer i : piles)
		{
			result += i.toString() + " ";
		}
		return result;
	}

	public static void turn()
	{
		int num = piles.size();
		int count = num;
		for (int i = 0; i < count; i++)
		{
			int v = piles.get(i);
			if (v <= 1)
			{
				// remove the pile
				piles.remove(i);
				count--;
				i--;
			}
			else
			{
				// remove one from the pile.
				piles.set(i, piles.get(i) - 1);
			}
		}
		// Now create a new pile with the former number of piles.
		piles.add(num);
	}
	public static boolean isDone()
	{
		for (int i = 1; i <= 9; i++)
		{
			if (!piles.contains(i)) return false;
		}
		return true;
	}

}
