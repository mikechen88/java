import acm.program.ConsoleProgram;

public class MealPrice extends ConsoleProgram{
	public void run(){
		println("welcome to restaurant");
		double price=readDouble("input meal price");
		double tax=price*0.08;
		double tip=price*0.2;
		double total=price +tip+tax;
		
		println("tax: $"+tax);
		println("tip: $"+tip);
		println("your total is :"+ total);		
	}	
}
