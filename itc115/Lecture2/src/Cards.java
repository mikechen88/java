import acm.program.ConsoleProgram;
public class Cards extends ConsoleProgram{

	public void run(){
		int card=readInt("Enter number:");
		String end;
		
		switch(card){
		case 11:case 12:case 13:
			  end="That's a face card";
			  break;
		case 1:case 2: case 3:case 4:case 5:case 6:case 7:case 8: case 9: case 10:
			end="that's not a face card";
			break;
		default:
			end="invalid input";
		}
		println(end);
	}
}
