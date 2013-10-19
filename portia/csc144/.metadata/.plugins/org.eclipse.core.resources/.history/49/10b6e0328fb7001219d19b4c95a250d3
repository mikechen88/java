import java.util.Scanner;

/*
 * File: TeachingMachine.java: based on the handout by Eric Roberts
 * --------------------------
 * This program executes a programmed instruction course. All
 * the work is done by the TMCourse class.
 */
public class TeachingMachine {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a course name");
		System.out.print("> ");
		String filename = scan.nextLine();
		try {
			TMCourse course = TMCourse.readFromFile(filename);
			System.out.println(course);
			course.run(scan);
		} catch (Exception e) {
			System.out.println("Problem reading the course file.");
		}
	}
}
