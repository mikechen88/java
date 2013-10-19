import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JPanel;

/**
 * A graphical display of the data of the model
 * @author open
 *
 */

public class PanelOfSquares extends JPanel implements View {
	private ModelOfSquares model;

	public void update(ModelOfSquares m) {
		model = m;
		repaint();

	}
	
	public PanelOfSquares(){
		setBackground(Color.WHITE);
	}

	/**
	 * paint the window
	 */
	protected void paintComponent(Graphics g) {
		// Needed to paint in the right order
		super.paintComponent(g);

		// Display the squares
		if (model != null) {   
			Graphics2D g2D = (Graphics2D) g;
			
			List<MyRectangle> list = model.getSquares();
			for (int i = 0; i < list.size(); i++) {
				Rectangle r = list.get(i).getRectangle();
				Color c = list.get(i).getColor();
				g2D.setColor(c);
				g2D.fill(r);
			}
		}
	}

}
