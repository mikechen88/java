import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

/*
 * describes the data structure to store  one question
 * 
 * 
 * 
 */
public class TMQuestion implements Comparable<TMQuestion> {
	private int number;
	private String text;
	private Map<String, Integer> answers;
	public static  String SEPARATOR = "-----";
	public static  String ANSWER_SEP = ":";

	// client must call the static method to create a question
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
			while (scan.hasNextLine()
					&& !(line = scan.nextLine().trim()).equals("")) {
				String[] parts = line.split(ANSWER_SEP);
				q.answers.put(parts[0], Integer.parseInt(parts[1].trim()));

			}

		}
		return q;
	}

	/*
	 * returns the full question( number +text+answers)
	 */
	public String toString() {
		String fullQuestion = "";
		fullQuestion += number + "\n";
		fullQuestion += text;
		fullQuestion += SEPARATOR + "\n";

		Set<String> keys = answers.keySet();
		for (String key : keys) {
			fullQuestion += key + ANSWER_SEP + " " + answers.get(key) + "\n";
		}
		return fullQuestion;
	}

	/*
	 * return the number of the question
	 */
	public Integer getNumber() {

		return number;
	}
/////////////////////////////////////////////////////////////////////////////////////
	/*
	 * returns <0 if this<q, 0 if this is equal to q , >0 if this>q
	 */
	public int compareTo(TMQuestion q) {

		return number - q.number;
	}

	/*
	 * returns the text of the question
	 */

	public String getText() {
		return text;
	}
	
	/*
	 * given an answer, returns the number of the next question
	 * or -1  if there is no question for that answer. 
	 */
	public Integer getNextQuestionNumber(String answer){
		answer=answer.trim();
		if ( answers.containsKey(answer)){
			return answers.get(answer);
		}else{
			return -1;
		}
	}

}
