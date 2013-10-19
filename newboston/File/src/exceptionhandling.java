import java.util.*;

public class exceptionhandling {
	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		
		int x=1;
		do{
				try{
				System.out.println("enter first num: ");
				int n1=input.nextInt();
				System.out.println(" enter second num");
				int n2=input.nextInt();
				int sum=n1/n2;
				System.out.println(sum);
				x=2;
				}catch(Exception e){
					System.out.println("you can't do that");
				}
		}while(x==1);
	}
	
}
