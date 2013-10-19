import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;




public class HelloClick extends GraphicsProgram
{
     public void init()
     {
    	 addMouseListeners();
    	 
    	 
    	 
    	 
    	 
    	 
     }
     
	public void mouseClicked(MouseEvent e)
	{
		//make a hello label appear on the screen
		
		GLabel glabel = new GLabel("hello world", e.getX(),e.getY()); 
		add(glabel);
		
		
	}
	
	
	
	
	
	
}
