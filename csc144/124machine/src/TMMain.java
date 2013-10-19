import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TMMain {

	public static void main(String[] args) {
		System.out.print("Enter the name of the course file: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		try {
			File file = new File(fileName);
			if (fileName.contains("Java")) {
				TMQuestion.ANSWER_SEP = ":";
			} else {
				TMQuestion.ANSWER_SEP = "[\\s/]";
			}
			Scanner scan = new Scanner(file);
			TMCourse course = TMCourse.readFromFile(scan);
			// System.out.println(course);
			course.run(in);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find " + fileName);
			return;
		}
	}

}
