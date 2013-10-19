import java.awt.event.KeyEvent;

import acm.graphics.GTurtle;
import acm.program.*;
import acm.graphics.*;

public class turtleKeyMove extends GraphicsProgram {
	private GTurtle turtle;
	private int vx=1;
	private boolean playing=true;
	
	int vy=0;
	
	public void init()
	{
		addKeyListeners();		
		
	}
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
			vx++;
			break;
		case KeyEvent.VK_LEFT:
			vx--;
			break;
		case KeyEvent.VK_UP:
			vy--;
			break;
		case KeyEvent.VK_DOWN:
			vy++;
			break;
		case KeyEvent.VK_ESCAPE:
			playing=false;
			return;
			
		}
		
		
		
		
	}
	
    public void run() {
        
        GTurtle turtle = new GTurtle(50, 50);
         add(turtle);
         waitForClick();
         

        
        while (playing) {
        	if(((turtle.getX()+30>=getWidth())&& vx>0) || ((turtle.getX()+30<=0)&&vx<0))
        		vx=-vx;
        	
        	if(((turtle.getY()+30>=getHeight())&&vy>0)||((turtle.getY()+30>=0)&&vy<0))
        		vy=-vy;
            turtle.move(vx,vy);
            
        
            
            
            
            }
     
        }

    }
 

