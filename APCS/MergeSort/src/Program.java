import java.util.Arrays;

public class Program
{

	static int[] a = { 1, 3, 6, 9, 123, 34, 436, 1, 3, 26 };
	static String sentence = "The quick brown fox jumps over the lazy dog.";
	static String sentence2 = "Why am I always the one who has to clean up the mess?";
	static String sentence3 = "The earliest known appearance of the phrase is from The Michigan School Moderator, a journal that provided many teachers with education-related news and suggestions for lessons. In an article titled Interesting Notes in the March 14, 1885 issue, the phrase is given as a suggestion for writing practice: The following sentence makes a good copy for practice, as it contains every letter of the alphabet: A quick brown fox jumps over the lazy dog. Note that the phrase in this case begins with the word A rather than The. Several other early sources also use this variation.";
	static String punct;

	public static void main(String[] args)
	{
		System.out.println("before: " + Arrays.toString(a));
		mergeSort(a);
		System.out.println("after: " + Arrays.toString(a));
		
		System.out.println(sentence);
		String[] words = sentenceToWords(sentence);
		mergeSort(words);
		System.out.println(wordsToSentence(words));
		
		System.out.println(sentence2);
		words = sentenceToWords(sentence2);
		mergeSort(words);
		System.out.println(wordsToSentence(words));
		
		System.out.println(mostFrequentWord(words));
		
		words = sentenceToWords(sentence3);
		mergeSort(words);
		System.out.println(wordsToSentence(words));
		System.out.println(mostFrequentWord(words));
		
	}
	
	private static String wordsToSentence(String[] words)
	{
		String result = words[0];
		for (int i = 1; i < words.length; i++) {
			result += " " + words[i]; 
		}
		result += punct;
		return result;
	}

	private static String[] sentenceToWords(String sentence)
	{
		punct = sentence.substring(sentence.length() - 1);
		sentence = sentence.substring(0, sentence.length() - 1);
		return sentence.split(" ");
	}

	public static void mergeSort(int[] a)
	{
		if (a.length > 1)
		{
			int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			mergeSort(left);
			mergeSort(right);
			
			merge(a, left, right);
		}
	}
	
	public static void merge(int[] result, int[] a1, int[] a2) {
		int i1 = 0;
		int i2 = 0;
		for (int i = 0; i < result.length; i++) {
			if (i2 >= a2.length || (i1 < a1.length && a1[i1] <= a2[i2])) {
				result[i] = a1[i1];
				i1++;
			}
			else {
				result[i] = a2[i2];
				i2++;
			}
		}
	}
	public static void mergeSort(String[] a)
	{
		if (a.length > 1)
		{
			String[] left = Arrays.copyOfRange(a, 0, a.length / 2);
			String[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			mergeSort(left);
			mergeSort(right);
			
			merge(a, left, right);
		}
	}
	
	public static void merge(String[] result, String[] a1, String[] a2) {
		int i1 = 0;
		int i2 = 0;
		for (int i = 0; i < result.length; i++) {
			if (i2 >= a2.length || (i1 < a1.length && a1[i1].compareToIgnoreCase(a2[i2]) <= 0)) {
				result[i] = a1[i1];
				i1++;
			}
			else {
				result[i] = a2[i2];
				i2++;
			}
		}
	}
	
	public static String mostFrequentWord(String[] words) {
		int max = 0;
		String prev = words[0];
		String result = prev;
		int count = 1;
		for (int i = 1; i < words.length; i++) {
			String current = words[i];
			if (prev.equalsIgnoreCase(current)) {
				// one more word
				count++;
			}
			else {
				// new word
				if (count > max)
				{
					result = prev;
					max = count;
				}
				count = 1;
				prev = current;
			}
		}
		return result;
	}
	
}
