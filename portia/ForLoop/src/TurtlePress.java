import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.graphics.GTurtle;


public class TurtlePress extends GraphicsProgram

{
	private GTurtle turtle ;
	
public void init() 
{
	turtle=new GTurtle(getWidth()/2,getHeight()/2);
	//turtle.setSize(500);
	add(turtle);
	
addKeyListeners();	

}
					public void keyPressed(KeyEvent e)
					
					{
					switch(e.getKeyCode())
					{
					case KeyEvent.VK_UP:
						turtle.setDirection(90);
					if(turtle.getY()<=0)
return;
						break;
					case KeyEvent.VK_DOWN:
						turtle.setDirection(-90);
						if(turtle.getY()>getHeight())
							return;
											
						break;
					
					
					case KeyEvent.VK_RIGHT:
						turtle.setDirection(0);
						if(turtle.getY()>getWidth())
							return;
			
						break;
					case KeyEvent.VK_LEFT:
						turtle.setDirection(180);
						if(turtle.getX()<=0)
							return;
						break;
						default:
							return;
					}

					turtle.forward(10);

}


}
