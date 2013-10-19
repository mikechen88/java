import java.awt.*;

/**
 * A small ball object that wanders randomly around a bounded area.
 */
public class Ball implements SimThing {
	// instance variables
	private int x, y; // current coordinates of this Ball

	private int dx, dy; // current motion direction in x and y

	// (sign gives direction, magnitude
	// gives speed)
	private Color color; // color of this ball

	private int diameter; // size of this ball

	private int maxx, maxy; // max x and y coordiantes

	/**
	 * Construct a new Ball with the given x and y coordinates, color, and
	 * diameter, with direction and speed specified by dx, dy, and max possible
	 * x and y coordinates.
	 */
	public Ball(int x, int y, int dx, int dy, Color c, int diameter, int maxx,
			int maxy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.color = c;
		this.diameter = diameter;
		this.maxx = maxx;
		this.maxy = maxy;
	}

	/**
	 * On each cycle of the simulation, in the current direction. If this motion
	 * moves off the edge, bounce by reversing the x and y directions.
	 */
	public void action() {
		x = x + dx;
		if (x < 0 || x > maxx) {
			dx = -dx;
		}
		y = y + dy;
		if (y < 0 || y > maxy) {
			dy = -dy;
		}
	}

	/**
	 * Display this ball on the given graphics context
	 */
	public void paintMe(Graphics g) {
		g.setColor(color);
		g.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
	}
}
