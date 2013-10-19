import acm.program.ConsoleProgram;

public class ForLoop extends ConsoleProgram
{
	
	private static final int MAX=10;
	
	public void run()
	{
		for (int i=1;i<=MAX;i++)
		{
			//println(i);
			//print(i+",");
			
			
			for (int j=1;j<=MAX;j++)
			{
			
				print(j*i+" ");
				
				
				
			}
			println();
			
			
		}
		
	}

}
