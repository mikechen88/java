import  java.util.Scanner;

public class Calculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner  scanner=new  Scanner( System.in);
		double  fnum,snum,answer;
		System.out.println("enter first num:");
		fnum=scanner.nextDouble();
		
		System.out.println("enter second num:");
		snum=scanner.nextDouble();
		
		answer=fnum+snum;
		System.out.println(answer);
	}

}
