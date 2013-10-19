import acm.program.ConsoleProgram;
public class Name extends ConsoleProgram{

	public void run(){
		String firstName=readLine ("input your name:").toLowerCase().trim();
		String lastName=readLine ("input your name:").toLowerCase().trim();
		String result=((firstName. equals ("feng")&& lastName.equals("chen"))||lastName.equals("chen"))?"welcome  "+ firstName+"  "+lastName: "go away";
		
		print(result);
		
	}

}
