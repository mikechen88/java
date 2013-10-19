


import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.util.RandomGenerator;


public class turtleMove extends GraphicsProgram
{
	

	 private RandomGenerator rgen = RandomGenerator.getInstance();
public void init()
{
addKeyListeners();	


}
	public void keyPressed(KeyEvent e)
	{ 
		double y = rgen.nextDouble(0.0, 500.0); 
		double x = rgen.nextDouble(0.0, 500.0); 

		GLabel helloLabel=new GLabel("hello",x,y);
		
		Color color = rgen.nextColor() ;
		helloLabel.setColor(color);
		add(helloLabel);
		
	}
	
	
}
