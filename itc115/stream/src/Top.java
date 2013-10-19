import java.util.Scanner;

public class Top {
	Scanner scan = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Top top = new Top();
		top.run();

	}

	public String getString(String question) {
		System.out.print(question + ": ");
		return scan.nextLine().toLowerCase().trim();
	}

	public void run() {
		String in = getString("input your top");

		if (getCookieMessage(in))
		 in.replaceAll("cookies", "ice cream");
		System.out.println(in);
		
		/*int bb= in.indexOf("cookies");
		
		if( bb!=-1){
			in=in.substring(bb);
			System.out.println(in);
		}*/
		in=getBetterDessert(in);
		System.out.println(in);
		
		
	}

	public boolean getCookieMessage(String in) {
		return in.contains("cookies");

	}
	
	public String getBetterDessert( String dessert){
		dessert=dessert.replace("sprinkles", "a cherry on top");
		int cupIndex=dessert.indexOf("cupcake");
		if ( cupIndex>=0){
			dessert=dessert.substring(cupIndex);
		}
		return dessert;
	}
	
	
	

}
