import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class HashMapExample {
	/**
	 * Reads and prints the list of synonyms from assignment 4
	 */
	public static void main(String[] args) throws IOException {
		Map<String, String> s = new HashMap<String, String>();
		Scanner scan = new Scanner(new File("CrowtherSynonyms.txt"));
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			int index = line.indexOf("=");
			if (index != -1) {
				String key = line.substring(0, index);
				String value = line.substring(index + 1);
				s.put(key, value);
			}
		}

		// print it all
		Set<String> keys = s.keySet();
		for (String k : keys) {
			System.out.println("key = " + k + ", value = " + s.get(k));
		}
	}
}
