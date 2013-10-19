import java.awt.Color;
import java.awt.Graphics;


public class Plus extends AbstractShape implements Cloneable {

	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	

	public Plus(int x, int y, int height, Color color){
		super(x, y, height, color);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = height;
		this.color = color;		 
	}
	
	// draws plus +
	public void drawShape(Graphics g) {		
		int x1 =  x + (width/2);
		int x2 = x - (width/2); 
		int y1 = y + (height/2);
	    int y2 = y - (height/2);
	    g.setColor(color);
		g.drawLine(x2, y, x1, y);
	    g.drawLine(x, y1, x, y2);	    
	}
	
	// Used code from Car example
	// overrides copy method to create a deep copy
	public Object clone() {
			try {
				Plus copy = (Plus) super.clone();
				return copy;
			} catch (CloneNotSupportedException e) {
				return null; 
			}
		}

	
}

	
	

