import java.awt.*;

/**
 * Basic interface for objects in the simple simulation world. Any object that
 * implements this can be added to the simulation.
 */

public interface SimThing {
	/**
	 * Perform desired action on each simulation cycle.
	 */
	public void action();

	/**
	 * Draw graphical representation of this SimThing.
	 * 
	 * @param g
	 *            Graphics context for the drawing
	 */
	public void paintMe(Graphics g);
}
