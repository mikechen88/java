import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class defines the data structure for a course for use with the
 * TeachingMachine program as described in Eric Roberts' handout
 */

public class TMCourse {

	// A course has a title and a list of questions
	private String title;

	private TreeMap<Integer, TMQuestion> questions = new TreeMap<Integer, TMQuestion>();

	// private so that a course is always created from a file
	private TMCourse() {
	}

	/**
	 * Creates course given its input file
	 * 
	 * @param filename
	 *            the file that contains the course title and Q&A
	 * @return the TMCourse object based on the file data or null if the file
	 *         could not be read
	 * 
	 */
	public static TMCourse readFromFile(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			if (scanner.hasNextLine()) {
				TMCourse course = new TMCourse();
				course.title = scanner.nextLine().trim();
				TMQuestion question;
				while ((question = TMQuestion.readFromFile(scanner)) != null) {
					course.questions
							.put(question.getQuestionNumber(), question);
				}
				return course;
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Processes the questions and answers for the course
	 * 
	 * @param scanner
	 *            the Scanner that does the input
	 */
	public void run(Scanner scanner) {
		System.out.println(title);
		int next = questions.firstKey();
		TMQuestion current = questions.get(next);
		while (next != 0) {
			if (next != -1) {
				for (String s : current.getText()) {
					System.out.println(s);
				}
			}
			System.out.print("> ");
			String line = scanner.nextLine().toUpperCase();
			next = current.nextQuestion(line);
			if (next == -1) {
				System.out.println("I don't understand");
			} else if (next != 0) {
				current = questions.get(next);
			}
		}
		System.out.println("Done!");
	}

	/**
	 * Returns a string representation of this course
	 * 
	 * @return a string representation of this course
	 */
	public String toString() {
		String s = title + "\n";
		Set<Integer> keys = questions.keySet();
		for (Integer k : keys) {
			s += questions.get(k) + "\n";
		}
		return s;
	}

}
