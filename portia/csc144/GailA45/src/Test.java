import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import junit.framework.TestCase;


public class Test extends TestCase implements MouseListener  {
	
	DrawModel shapes;
 public void testHeight(){
	  shapes=new DrawModel();
	
		
			shapes.add(new Diamond(100,50,20,20));
		//	shapes.add(new Plus(x,100,20,20));
		
		 
 }

@Override
public void mouseClicked(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
	//System.out.println("x  =  " + x + "      y=" + y);
	
	if (!shapes.getShapes().get(0).getBound().contains(x, y)) {
		shapes.getShapes().get(0).addLevel(1);
		
		int height=shapes.getShapes().get(0).getHeight();
		
		assertTrue(height>1);
	}


	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
