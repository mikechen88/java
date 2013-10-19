import acm.program.ConsoleProgram;

public class DaysLate extends ConsoleProgram{
	
	public void run(){
		int day=readInt(" how many day late? ");
		if ( day>0)
			println("no good !");
		else
			println( "great");
		
	}	
}
