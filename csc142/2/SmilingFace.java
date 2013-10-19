import uwcse.graphics.*;
import java.awt.Color;

/**
 * A smiling face in a graphics window
 */

public class SmilingFace {

	// The graphics window
	private GWindow window;

	// Location of the face
	private int x, y;

	// Scale used to draw the face
	private double scale;

	/**
	 * Draw a smiling face in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the center of the face
	 * @param y
	 *            the y coordinate of the center of the face
	 * @param scale
	 *            the multiplication factor for all default sizes
	 * @param window
	 *            the graphics window where to draw
	 */
	public SmilingFace(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields

		// Draw the face in the window
	}

	/**
	 * Draw the face in the graphics window
	 */
	private void drawFace() {
		// The face (a circle: default radius = 50)

		// The mouth (use drawMouth)

		// The eyes (use drawEye)
		// left

		// right

		// The nose (use drawNose)

		// Show the face

	}

	/**
	 * Draw an eye
	 * 
	 * @param eyex
	 *            the x coordinate of the center of the eye
	 * @param eyey
	 *            the y coordinate of the center of the eye
	 */
	private void drawEye(int eyex, int eyey) {
		// A black circle in a white oval
	}

	/**
	 * Draw a nose
	 * 
	 * @param nosex
	 *            the x coordinate of the top point of the nose
	 * @param nosey
	 *            the y coordinate of the top point of the nose
	 */
	private void drawNose(int nosex, int nosey) {
		// A nose is a triangle
	}

	/**
	 * Draw a mouth
	 * 
	 * @param mouthx
	 *            the x coordinate of the middle bottom point of the mouth
	 * @param mouthy
	 *            the y coordinate of the middle bottom point of the mouth
	 */
	private void drawMouth(int mouthx, int mouthy) {
		// Draw two circles (one black and one yellow)
		// The yellow circle is on top of the black circle and slightly shifted
		// up
	}
}
