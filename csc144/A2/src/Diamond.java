import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * A small ball object that wanders randomly around a bounded area.
 */
public class Diamond extends AbstractShape implements Cloneable {
	// instance variables
	// private int x, y; // current coordinates of this Ball

	private int dx = 6, dy = 4; // current motion direction in x and y

	// (sign gives direction, magnitude
	// gives speed)
	private Color color = Color.RED; // color of this ball

	private int diameter; // size of this ball

	private int maxx, maxy; // max x and y coordiantes

	/**
	 * Construct a new Ball with the given x and y coordinates, color, and
	 * diameter, with direction and speed specified by dx, dy, and max possible
	 * x and y coordinates.
	 */
	
	
	public Diamond(int xx, int yy, int height, int x, int y) {
		super(xx, yy, height, x, y);
	}

	public Diamond(AbstractShape diamond) {
		super(diamond.xx, diamond.yy, diamond.height, diamond.x, diamond.y);
	}

	/**
	 * On each cycle of the simulation, in the current direction. If this motion
	 * moves off the edge, bounce by reversing the x and y directions.
	 */
	public void action() {
		xx = xx + dx;
		if (xx < 0 || xx > 290) {
			dx = -dx;
		}
		yy = yy + dy;
		if (yy < 0 || yy > 290) {
			dy = -dy;
		}
	}

	/**
	 * Display this ball on the given graphics context
	 */
	public void paintMe(Graphics g) {

		Polygon poly = new Polygon();
		poly.addPoint(xx, yy - height);
		poly.addPoint(xx + height, yy);
		poly.addPoint(xx, yy + height);
		poly.addPoint(xx - height, yy);
		g.setColor(color);
		g.fillPolygon(poly);
		g.drawPolygon(poly);

	}

	public AbstractShape clone() {
		try {
			Diamond copy = (Diamond) super.clone();
			return copy;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
