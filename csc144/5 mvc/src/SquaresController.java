import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A SquaresController listens to the keyboard and the mouse and notifies the
 * model
 * 
 * @author CSC 143
 * 
 */
public class SquaresController implements MouseListener, KeyListener {

	// the model holding the data
	private ModelOfSquares model;

	/**
	 * Creates a controller connected to the given model
	 */
	public SquaresController(ModelOfSquares model) {
		this.model = model;
	}

	/**
	 * Tells the model to add a square
	 */
	public void keyPressed(KeyEvent e) {
		JFrame frame = (JFrame) e.getSource();
		Container container = frame.getContentPane();
		int width = container.getWidth();
		int height = container.getHeight();
		model.addSquare(width, height);
	}

	/**
	 * Deletes a square if left click, or displays an information message box if
	 * right click.
	 */
	public void mousePressed(MouseEvent e) {
		// If a right click, display the number of squares
		if (e.isMetaDown()) {
			JOptionPane.showMessageDialog((Component) e.getSource(),
					"number of squares = " + model.getSquares().size(),
					"Statistics", JOptionPane.INFORMATION_MESSAGE);
		} else {
			// delete the square at the location of the click (if
			// there is one).
			model.deleteSquare(e.getX(), e.getY());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
