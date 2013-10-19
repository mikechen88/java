import acm.program.ConsoleProgram;
public class ForLoop extends ConsoleProgram{
	static final int LAST_NUM=15;
	public void run(){
		
		for (int i=3;i<=LAST_NUM;i+=3){
			print(i);
			if (i!=LAST_NUM) print(", ");
		}
	}
}
