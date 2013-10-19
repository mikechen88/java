import acm.program.ConsoleProgram;
public class studentArray extends ConsoleProgram
{
	
	
	
	
	

			public void run()
			{
			
			
			
				//double[] grades={9,8.5,3,7};
				double[] grades=new double[4];
				grades[0]=9;
				grades[1]=8.5;
				grades[2]=3;
				grades[3]=7;
				
				double total=0;
				double average=0;
				println("student id, grades");
				
				for (int i = 0; i < grades.length; i++)
					{
					println (i+","+grades[i] );
					 total +=grades[i];
					
					average=total/i;
					}
				println ("total     "+total);
				println("average      "+average);
			}
	}

