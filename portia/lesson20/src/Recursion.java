
public class Recursion {

	
	public static int base=2;
	public static int exp=5;
	
	public static int sum(int max)
	{
		if (max==1) return 1;//base case
		return 1+sum(max-1);//recursion
		
		
		
	}
	public static void main(String[] args)
	{
		System.out.println("sum of 5:"+sum(5));
		System.out.println("factorial of 5:"+factorial(5));
		System.out.println("power of 5:"+power(2,5));
		System.out.println("fibanache of 5:"+fibanache(6));
	}
	public static int factorial(int max)
    {
	  if (max==0) return 1;
	  return max*factorial(max-1) ;


     }
	public static int power(int base, int exp)
	{
		if (exp==0) return 1;
		
		return base*power(base,exp-1);
		
		
	}
	public static int fibanache(int n)
	{
		if(n<1) return -1;
		//base base
		if (n==1||n==2) return 1;
		
		
     return fibanache(n-1)+fibanache(n-2);
	
		
		
		
	}
}
