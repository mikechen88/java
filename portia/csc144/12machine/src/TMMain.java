import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TMMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print("Enter the name of the course file: ");
		Scanner in = new Scanner(System.in);

		String fileName = in.nextLine();

		try {
			File file = new File(fileName);
			boolean hasTitle;
			if ( fileName.contains("java")){
				hasTitle=true;
				TMQuestion.ANSWER_SEP=":";
			}else{
				hasTitle=false;
				TMQuestion.ANSWER_SEP=" |/";
			}
			Scanner scan = new Scanner(file);
			TMCourse course=TMCourse.readCourse(scan,hasTitle);
			//System.out.println(course);
			
			course.run(in);

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find    " + fileName);
			return ;
		}
	}

}
