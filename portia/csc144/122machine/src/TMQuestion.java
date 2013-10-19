import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Describes the data structure to store one question
 * 
 * @author CSC 143
 * 
 */
public class TMQuestion implements Comparable<TMQuestion> {
	private int number;
	private String text;
	private Map<String, Integer> answers;
	public static String SEPARATOR = "-----";
	public static String ANSWER_SEP = ":";
	
	// The client must call the static method to create 
	// a question.
	private TMQuestion() {
		text = "";
		answers = new HashMap<String, Integer>();
	}
	
	public static TMQuestion readQuestion(Scanner scan) {
		TMQuestion q = null;
		if (scan.hasNextInt()) {
			q = new TMQuestion();
			// number
			q.number = scan.nextInt();
			// text of the question
			String line;
			while (!(line = scan.nextLine().trim()).equals(SEPARATOR)) {
				q.text += line + "\n";
			}
			// answers
			while (scan.hasNextLine() && 
					!(line = scan.nextLine().trim()).equals("")) {
				String[] parts = line.split(ANSWER_SEP);
				q.answers.put
		(parts[0].trim(), Integer.parseInt(parts[1].trim()));
			}
		}
		return q;
	}
	
	/**
	 * Returns the full question (number + text + answers)
	 */
	public String toString() {
		String fullQuestion = "";
		fullQuestion += number;
		fullQuestion += text;
		fullQuestion += SEPARATOR + "\n";
		Set<String> keys = answers.keySet();
		for (String key: keys) {
			fullQuestion += key + ANSWER_SEP + " " + 
		                    answers.get(key) + "\n";
		}
		return fullQuestion;
	}

	/**
	 * Returns the number of the question
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * returns < 0 if this < q, 0 if this is equal to q, and 
	 * > 0 if this > q.
	 */
	public int compareTo(TMQuestion q) {
		return number - q.number;
	}
	
	/**
	 * Returns the text of the question
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Given an answer, returns the number of the next question
	 * or -1 if there is no question for that answer.
	 */
	public Integer getNextQuestionNumber(String answer) {
		answer = answer.trim();
		if (answers.containsKey(answer)) {
			return answers.get(answer);
		} else {
			return -1;
		}
	}
}











