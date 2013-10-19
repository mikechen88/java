
import java.util.ArrayList;

import acm.program.ConsoleProgram;
public class LearningArrayLists extends ConsoleProgram
{
			public void run()
			{
				/*ArrayList students=new ArrayList();
				students.add("portia");
				students.add("jack");
				students.add("bob");
				students.add("sara");
				
				for(Object  student:students)
				{
					String studentName=(String)student;
					println(studentName);
					
				}*/
				
				ArrayList<String> students=new ArrayList<String>();
				students.add("portia");
				students.add("jack");
				students.add("bob");
				students.add("sara");
				
				for(String  student:students)
				{
					
			println(student);
					
				}
				
				
			}

}
