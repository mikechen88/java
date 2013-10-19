import java.awt.Color;
import java.awt.Rectangle;



/**
 * a class to associate a Rectangle to its color.
 * @author csc143
 *
 */
public class MyRectangle {
	private Rectangle r;
	private Color c;
	
	
	public MyRectangle ( Rectangle r , Color c){
		this.r=r;
		this.c=c;
	}
	
	public Color getColor() {
		return c;
	}
	
	public Rectangle getRectangle() {
		return r;
	}
	
}
