import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;
public class RemovingCircles extends GraphicsProgram
{
    public void run()
    {
    	GOval oval=new GOval(75,75,50,50);
    	oval.setFilled(true);
    	oval.setColor(Color.red);
    	add(oval);
    
    	addMouseListeners();
    }
	
	public void mouseClicked(MouseEvent e)
	{
		GObject maybeItem=getElementAt(e.getX(),e.getY());
		if (maybeItem==null) return;
		
		remove(maybeItem);
		
	}
	
	
	
	
}
