import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Diamond extends AbstractShape implements Cloneable{
	
	public Diamond(int x, int y, int height, Color color){
		super(x, y, height, color);
	}
	
	// draws diamond
	public void drawShape(Graphics g) {
		
		int x1 = x + (width/2);
		int y1 = y + (height/2);
		int x2 = x - (width/2);
	    int y2 = y - (height/2);
	    Polygon poly = new Polygon();	    
	    poly.addPoint(x2, y);
	    poly.addPoint(x, y1);
	    poly.addPoint(x1, y);
	    poly.addPoint(x, y2);
	    g.setColor(color);
		g.fillPolygon(poly);
		g.drawPolygon(poly);
		
	}
	
	// Used code from Car example
	// overrides copy method to create a deep copy
	public AbstractShape clone() {
		try {
			Diamond copy = (Diamond) super.clone();
			return copy;
		} catch (CloneNotSupportedException e) {
			return null; 
		}
	}
		

}
	
