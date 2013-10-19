import java.util.*;

public class AnagramSolver {

	List<String> dictionary;
	Map<String, LetterInventory> wordMap = new HashMap<String, LetterInventory>();

	// This method constructs an anagram solver that will use the
	// given list as its dictionary.
	public AnagramSolver(List<String> list) {
		dictionary = list;
		for (int i = 0; i < dictionary.size(); i++) {
			wordMap.put(dictionary.get(i),
					new LetterInventory(dictionary.get(i)));
		}
	}

	// This method finds all the combination of words
	// (according to the given max) that contain the same
	// letters as the given phrase and prints them. This method
	// throws an IllegalArgumentException if the given max
	// is less than zero.
	public void print(String s, int max) {
		if (max < 0) {
			throw new IllegalArgumentException();
		}
		LetterInventory letters = new LetterInventory(s);
		List<String> prunedDictionary = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		if (max == 0){
			max = letters.size();
		System.out.println( "max =   "+max);
		System.out.println( "letters    "+letters);
			
		}
		for (int i = 0; i < dictionary.size(); i++) {
			String temp = dictionary.get(i);
			if (letters.subtract(wordMap.get(temp)) != null) {
				prunedDictionary.add(temp);
			}
		}
		realPrint(letters, results, max, prunedDictionary);
	}

	// This helper method searches through the
	// given dictionary to find all the combinations
	// of words that have the same letters as the given
	// phrase.
	private void realPrint(LetterInventory letters, List<String> results,
			int max, List<String> dictionary) {
		LetterInventory newLetters;
		if (letters.isEmpty()) {
			System.out.println(results);
		} else if (results.size() >= max) {
			return;
		} else {
			for (int i = 0; i < dictionary.size(); i++) {
				String word = dictionary.get(i);
				newLetters = letters.subtract(wordMap.get(word));
				if (newLetters != null) {
					results.add(word);
					realPrint(newLetters, results, max, dictionary);
					results.remove(results.size() - 1);
				}
			}
		}

	}
}