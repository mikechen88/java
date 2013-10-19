import acm.program.ConsoleProgram;;
public class WhileLoop extends ConsoleProgram{

	public void run(){
		
		String name="";
		while(true)
		{
			 name=readLine("enter your name:");
			
			 if(!name.equals("")){
				 //success:  they entered something
				 break;
			 }
			 println("you need to enter something");
		}
			
		println("welcome "+name);
	}

}
