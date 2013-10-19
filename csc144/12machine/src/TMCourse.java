import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * describes the data structure to store a full course
 * (= a list of questions) 
 * 
 * 
 */
public class TMCourse {
	private String title;
	private SortedMap<Integer, TMQuestion> questions;

	private TMCourse() {
		title = "";
		questions = new TreeMap<Integer, TMQuestion>();

	}

	public static TMCourse readCourse(Scanner scan,boolean hasTitle) {

		TMCourse course = null;
		if (scan.hasNextLine()) {
			course = new TMCourse();
			if ( hasTitle){
			course.title = scan.nextLine();
			}
			while( scan.hasNextInt()){
				TMQuestion q= TMQuestion.readQuestion(scan);
				course.questions.put(q.getNumber(),q);
			}
			
		}
		return course;
	}
	
	
	
	/*
	 * 
	 * returns the full description of the course as a string 
	 */
	public String toString (){
		String fullCourse= title+"\n";
	Set<Integer> keys = questions.keySet();
		for ( Integer key:keys){
			fullCourse+=questions.get(key)+"\n";
		}
		return fullCourse;
	}
	
	
	
	
	/*
	 * runs the course
	 */
	public void run(Scanner in){
		//current question ( the First question)
		TMQuestion current = questions.get(questions.firstKey());
		
		do {
			System.out.println( current.getText());
			String answer = in.nextLine();
			int next = current .getNextQuestionNumber(answer);
			if ( next==-1){
				System.out.println(" I don't understand ");
			}else if ( next==0 ){
				break;
			}else{
				current = questions. get(next);
			}
			
		}while(true );
		
		
	}
	
}
