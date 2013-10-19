import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Diamond extends AbstractShape implements Cloneable {

	public Diamond(int x, int y, int height, int level) {
		super(x, y, height, level);
		// realPaint(x, y, width, height, Color.red);
	}

	// real draw diamond
	public void realPaint(int x, int y, int width, int height, Color color) {
		int x1 = x + (width / 2);
		int y1 = y + (height / 2);
		int x2 = x - (width / 2);
		int y2 = y - (height / 2);

		Polygon poly = new Polygon();
		poly.addPoint(x2, y);
		poly.addPoint(x, y1);
		poly.addPoint(x1, y);
		poly.addPoint(x, y2);
		g.setColor(color);
		g.fillPolygon(poly);
		g.drawPolygon(poly);

	}

}
