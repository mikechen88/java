import java.util.Scanner;

public class ExceptionEx {

	Scanner scan = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExceptionEx ex = new ExceptionEx();
		ex.dance();
	}

	public void dance() {
		System.out.println(" Welcom to dance:");

		int leader = getInput("num of leader: ");

		int follow = getInput("num of follow :");

		try {// what we are going to attempt to do
			
			if (leader==0 && follow==0){
				throw new Exception("no one show up");
			}else if ( leader==0){
				throw new Exception(" no leader show up");
			}else if ( follow==0){
				throw new Exception(" no follow show up");
			}
			
			System.out.println("dance party");
		
		} catch (Exception e) {// code will run that if fail it.
			System.out.println("sorry, no dance party to dance");
			System.out.println( e.getMessage());
			System.exit(-1); // leaving code in an error state
		}

		System.out.println("get your dance shoes on. ");

	}

	public int getInput(String s) {
		System.out.print(s);
		int num = scan.nextInt();
		scan.nextLine();
		return num;
	}

}
