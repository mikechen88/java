
import java.awt.Color;

import acm.program.GraphicsProgram;
import acm.graphics.*;



public class goval  extends GraphicsProgram
{
	
	
	private static final int EGG_WIDTH=50;
	private static final int SPACING=20;
	public void run()
	{
		setSize(500,100);
		GOval[] eggs=new GOval[5];
		
		
		int eggX=0;
		Color[] eggColors={Color.RED ,Color.BLUE,Color.PINK,Color.ORANGE,Color.GREEN};
		
		
		for (int i=0; i<eggs.length; i++)
		{
			eggs[i]=new GOval (eggX,0,EGG_WIDTH,EGG_WIDTH);
			eggs[i].setColor(eggColors[i]);
			add(eggs[i]);
			
			eggX +=EGG_WIDTH+SPACING;
		
			
		}
		
		
		
	}
	
	
	
}
