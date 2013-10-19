import acm.program.ConsoleProgram;

public class HelloWord extends ConsoleProgram{
	public void run(){
		println("hello world");
		
		String firstName=readLine("Enter First name: ");
		String lastName=readLine("Enter Last name: ");
		println("welcome    :"+firstName+"  "+ lastName);
		
		
	}
}