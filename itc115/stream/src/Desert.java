import java.util.Scanner;

public class Desert {
	Scanner scan = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Desert p = new Desert();
		p.run();

	}

	public void run() {

		String in = getString("enter your desert:");
		String cookieMessage=getCookieMessage(in);
		System.out.println(cookieMessage);
		
		
		/*if (in.indexOf("cookies") != -1)
			System.out.println("you like cookies");
		else
			System.out.println("no good choice");
*/
		
	}

	public String getString(String question) {
		System.out.print(question + ": ");
		return scan.nextLine().toLowerCase().trim();
	}
	
	public String getCookieMessage(String in){
		if (in.contains("cookies"))
			return ("you like cookies");
		else
			return ("no good choice");
	}

}
