import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntroToCollections {
	public static void main(String[] args) {
		Map<String, Integer> areaCodes = new HashMap<String, Integer>();
		String[] cities = { "Seattle", "Bellevue", "New York City" };
		Integer[] codes = { 206, 425, 212 };
		for (int i = 0; i < cities.length; i++) {
			areaCodes.put(cities[i], codes[i]);
		}

		// print the map
		printMap(areaCodes);

		// change an item
		System.out.println();
		areaCodes.put("Seattle", 360);
		printMap(areaCodes);

		// remove an item
		System.out.println();
		areaCodes.remove("New York City");
		printMap(areaCodes);
	}

	public static void printMap(Map<String, Integer> map) {
		// print the map
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println("city = " + key + ", area code = "
					+ map.get(key));
		}

	}
}
