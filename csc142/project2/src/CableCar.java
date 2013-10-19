import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>
 * Create a cable car in a graphics window
 * </p>
 * 
 * @author feng
 */

public class CableCar {

	// Instance fields
	// The graphics window this tree belongs to
	private GWindow window;
	// location of the car
	// (x,y)is the left upper corner of the car
	private int x;
	private int y;

	// width, height is for the cable car
	private int width = 300;
	private int height = 150;
	// The scale used to draw this tree
	private double scale;

	// the size of the window
	private int size = 35;

	// define window
	private Rectangle win1, win2, win3;

	// define cable
	private Triangle tri;

	// define line
	private Line line;

	// define a car
	private Rectangle rec;

	private boolean moveRight;

	private double edge;

	// private double point=(this.x+this.width/2)*scale;

	/**
	 * Create a cable car at location (x,y) in the GWindow window.
	 * 
	 * @param x
	 *            the x coordinate of the center of the cable car
	 * @param y
	 *            the y coordinate of the center of the cable car
	 * @scale the factor that multiplies all default dimensions for this cable
	 *        car (e.g. if the default size is 80, the size of this cable car is
	 *        scale * 80)
	 * @window the graphics window this cable car belongs to
	 */
	public CableCar(int x, int y, double scale, GWindow window) {
		// initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		moveRight = true;
		this.edge = this.window.getWindowWidth();

		// The details of the drawing are in a private method
		this.draw();
	}

	/** Draw a cable car at location (x,y) */
	private void draw() {
		// draw a car
		rec = new Rectangle((int) (this.x * scale), (int) (this.y * scale),
				(int) (this.width * scale), (int) (this.height * scale),
				Color.blue, true);
		this.window.add(rec);

		// draw three windows
		win2 = new Rectangle((int) (this.x + (this.width - this.size) * scale
				/ 2), (int) (this.y + (this.height - this.size) * scale / 2),
				(int) (this.size * scale), (int) (this.size * scale),
				Color.white, true);
		win1 = new Rectangle((int) (this.x + this.width * scale / 4),
				(int) (this.y + (this.height - this.size) * scale / 2),
				(int) (this.size * scale), (int) (this.size * scale),
				Color.white, true);
		win3 = new Rectangle(
				(int) (this.x + this.width * scale * 3 / 4 - this.size),
				(int) (this.y + (this.height - this.size) * scale / 2),
				(int) (this.size * scale), (int) (this.size * scale),
				Color.white, true);

		this.window.add(win1);
		this.window.add(win2);
		this.window.add(win3);

		// draw the cable
		tri = new Triangle((int) ((this.x + this.width / 2 - 57) * scale),
				this.y, (int) ((this.x + this.width / 2 + 57) * scale), this.y,
				(int) ((this.x + this.width / 2) * scale),
				(int) ((this.y - 57) * scale));
		this.window.add(tri);

		line = new Line(0, (int) ((this.y - 57) * scale),
				window.getWindowWidth(), (int) ((this.y - 57) * scale));
		this.window.add(line);

	}

	/**
	 * moves the car by a given displacement
	 * 
	 * @param dx
	 *            the displacement along x
	 */
	public void move() {
		// remove the previous drawing of the car
		erase();

		// update the location of the car
		if (moveRight) {
			x = x + 1;
			if ((this.x + this.width / 2) * scale > edge)
				moveRight = false;
		} else {
			x = x - 1;
			if ((this.x + this.width / 2) * scale < 0) {
				moveRight = true;
			}
		}
		// redraw
		draw();
	}

	// erase everything
	public void erase() {
		this.window.remove(rec);
		this.window.remove(win1);
		this.window.remove(win2);
		this.window.remove(win3);
		this.window.remove(tri);
		this.window.remove(line);
	}
}
