import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * A small ball object that wanders randomly around a bounded area.
 */
public class Star extends AbstractShape implements Cloneable {
	// instance variables
	//private int x, y; // current coordinates of this Ball

	private int dx=6,dy=4; // current motion direction in x and y

	// (sign gives direction, magnitude
	// gives speed)
	private Color color=Color.GREEN; // color of this ball

	private int diameter; // size of this ball

	private int maxx, maxy; // max x and y coordiantes

	/**
	 * Construct a new Ball with the given x and y coordinates, color, and
	 * diameter, with direction and speed specified by dx, dy, and max possible
	 * x and y coordinates.
	 */
	public Star(int xx,int yy, int height, int x, int y) {
	super (xx,yy,height, x,y);
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

		

		g.setColor(color);
		g.drawLine(xx-height, yy, xx+height, yy);
		g.drawLine(xx, yy-height, xx, yy+height);
		
		

	}
	
	public Object clone(){
		try {
			Diamond copy=(Diamond)super.clone();
			return copy;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
