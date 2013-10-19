import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;


	// Graphical viewer for Ball simulation. Should be added to a top-level
	// container for viewing, and should be added as a SimView to an an appropriate
	// model

public class View extends JPanel implements DrawView {

	private DrawModel model = null;
	
	public void notify(DrawModel dm) {
		model = dm;
		repaint();
		
	}
	// not working for me
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// clear the view
		if (model == null) {
			return;
		} 
		Graphics2D g2=(Graphics2D)g;
		
		java.util.List shapes = model.getShapes();
		Iterator it = (Iterator) shapes.iterator();
		while (it.hasNext()) {
			AbstractShape s = (AbstractShape) it.next();
			s.drawShape(g2);
		}
	
	}
	
}
