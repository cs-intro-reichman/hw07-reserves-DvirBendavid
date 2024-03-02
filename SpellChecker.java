
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);


	}

	public static String tail(String str) {
		if(str.length() == 1)
			return  "";

		String tail ="";
		for (int i = 1; i < str.length(); i++) {
			tail = tail + str.charAt(i);
		}
		return tail;
	}

	public static char head(String str){

		return str.charAt(0);
	}


	public static int levenshtein(String word1, String word2) {
		if(word1.isEmpty())
			return word2.length();
		if(word2.isEmpty())
			return word1.length();
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if(head(word1) == head(word2))
			return levenshtein(tail(word1), tail(word2));

		return 1+ Math.min(levenshtein(tail(word1) , word2) , Math.min(
				levenshtein(word1 ,tail(word2)), levenshtein(tail(word1) , tail(word2))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {

			if(!in.isEmpty())
				dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = 10;
		String similar ="";
		for (int i = 0; i < dictionary.length; i++) {
			if(min > levenshtein(word , dictionary[i]) ){
				min = levenshtein(word , dictionary[i]);
				similar = dictionary[i];
			}
		}
		similar = min <= threshold ? similar:"";
		return similar;
	}

}
