
import acm.program.GraphicsProgram;
import acm.graphics.*;



public class circleArray  extends GraphicsProgram
{
	
	
	public void run()
	{
		Color[] grades=new Color[4];
		grades[0]="BLACK";
		grades[1]="RED";
		grades[2]="BLUE";
		grades[3]="BROWN";
		
		int x=50;
		for (int i=0;i<grades.length;i++)
		
		{
		GOval goval = new GOval(x*2*i, x, 20, 20); 
		goval.setFillColor(grades[i]);
		add(goval);
		
		}
	}
	
	
	
}
