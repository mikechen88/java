import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Graphical viewer for Ball simulation. Should be added to a top-level
 * container for viewing, and should be added as a SimView to an an appropriate
 * model.
 */

public class BallGraphicsView extends JPanel implements SimView {
	// instance variables
	private SimModel model = null; // the SimModel we are watching

	// (null until we've been
	// notified at least once)

	/**
	 * React when notified by our SimModel. Request that the panel be repainted,
	 * and store a reference to the model so we can paint the Balls when
	 * requested.
	 */
	public void notify(SimModel m) {
		model = m;
		repaint();
	}

	/**
	 * Repaint the world by asking each Ball to repaint itself
	 */
	public void paintComponent(Graphics g) {
		// clear the view
		Rectangle bounds = getBounds();
		g.clearRect(0, 0, bounds.width, bounds.height);

		if (model == null) {
			return;
		}

		java.util.List things = model.getThings();
		Iterator it = things.iterator();
		while (it.hasNext()) {
			SimThing thing = (SimThing) it.next();
			thing.paintMe(g);
		}
	}

}
