import java.awt.Color;

import acm.program.GraphicsProgram;
import acm.graphics.GOval;
public class NBA extends GraphicsProgram
{

	public void run()
	{
		GBasketBall basketBall=new GBasketBall(50,50,50,Color.ORANGE);
		basketBall.setColor(Color.ORANGE);
		add(basketBall);
		
	
	}
	
}
