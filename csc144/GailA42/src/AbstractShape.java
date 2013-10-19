import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class AbstractShape {
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected Color color = Color.RED;

	Graphics g;

	int level;

	int subLevel = 0;// temporary value for recursive

	public int getLevel() {
		return level;
	}

	// set the image to original state
	public void setLevelZero() {
		this.level = 0;
	}

	// change the image states
	public void addLevel(int increase) {

		this.level += increase;
	}

	// create for four direction of the sub shape
	public enum Corner {
		east, south, west, north
	}

	// constructor
	public AbstractShape(int x, int y, int height, int level) {
		this.x = x;
		this.y = y;
		// this.color = color;
		this.height = height;
		this.width = height;
	
		this.level = level;
	}

	// returns width
	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
		// return this.height;
	}

	// gets color
	public void setColor(Color color) {
		this.color = color;
	}

	// gets color
	public Color getColor() {
		return color;
	}

	// gets height
	public int getHeight() {
		return (int )(height/Math.pow(2, level));
	}

	// sets location of x
	public void setX(int x) {
		this.x = x;

	}

	// returns X
	public int getX() {
		return x;
	}

	// sets location of Y
	public void setY(int y) {
		this.y = y;

	}

	// returns Y
	public int getY() {
		return y;
	}

	public void sentPen(Graphics g) {

		this.g = g;
	}

	public void ready() {
		if (level <= 0) {
			realPaint(x, y, width, height, Color.red);
			g.drawString("Current level is : "+  level, x-this.getHeight(),y+this.getHeight());
		} else if (level >= 1) {
			realPaint(x, y, width, height, Color.red);
			drawChild(x, y - height / 2, height / 2, width / 2, subLevel + 1,
					Corner.north);
			drawChild(x + height / 2, y, height / 2, width / 2, subLevel + 1,
					Corner.east);
			drawChild(x, y + height / 2, height / 2, width / 2, subLevel + 1,
					Corner.south);
			drawChild(x - height / 2, y, height / 2, height / 2, subLevel + 1,
					Corner.west);
			//g.drawString("Current level is : "+  level, x-(int) (height * (Math.pow(3, level)/Math.pow(2, level+1))),y+(int) (height * (Math.pow(3, level)/Math.pow(2, level+1))));
			g.drawString("Current level is : "+  level, x-height,y+height);
		}
	}

	// get bounding of this total shape
	public Rectangle getBound() {
		return new Rectangle((int) (x - height *Math.pow(3, level)/Math.pow(2, level+1)),
				(int) (y - height *Math.pow(3, level)/Math.pow(2, level+1)),
				(int) (height *Math.pow(3, level)/Math.pow(2, level+1)*2),
				(int) (height *Math.pow(3, level)/Math.pow(2, level+1)*2));
		

	}

	public void drawChild(int x, int y, int w, int h, int subLevel,
			Corner parent) {

		// if sublevel==level , draw diamond
		if (subLevel < level + 1) {
			realPaint(x, y, w, h, (subLevel % 2 == 0) ? Color.red : Color.BLUE);

			switch (parent) {
			// if parent locate at east, draw north, east,south-----rest of case
			// same meaning
			case east:
				drawChild(x, y - h / 2, h / 2, w / 2, subLevel + 1,
						Corner.north);
				drawChild(x + h / 2, y, h / 2, w / 2, subLevel + 1, Corner.east);
				drawChild(x, y + h / 2, h / 2, w / 2, subLevel + 1,
						Corner.south);
				break;

			case north:
				drawChild(x, y - h / 2, h / 2, w / 2, subLevel + 1,
						Corner.north);
				drawChild(x + h / 2, y, h / 2, w / 2, subLevel + 1, Corner.east);
				drawChild(x - h / 2, y, h / 2, w / 2, subLevel + 1, Corner.west);
				break;

			case west:
				drawChild(x, y - h / 2, h / 2, w / 2, subLevel + 1,
						Corner.north);
				drawChild(x, y + h / 2, h / 2, w / 2, subLevel + 1,
						Corner.south);
				drawChild(x - h / 2, y, h / 2, w / 2, subLevel + 1, Corner.west);
				break;

			case south:
				drawChild(x + h / 2, y, h / 2, w / 2, subLevel + 1, Corner.east);
				drawChild(x, y + h / 2, h / 2, w / 2, subLevel + 1,
						Corner.south);
				drawChild(x - h / 2, y, h / 2, w / 2, subLevel + 1, Corner.west);
				break;

			default:
				break;
			}

		}

	}

	public abstract void realPaint(int x, int y, int width, int height, Color color);
}