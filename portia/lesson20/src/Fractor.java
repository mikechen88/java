
public class Fractor {
    public static int sum (int max)
     {
	  if (max==1) return 1;
	  return max*sum(max-1) ;


      }
    public static void main(String[] args)
	{
		System.out.println("sum of 5:"+sum(5));
		
	}
	
}
