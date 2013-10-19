import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Polygon;

//to illustrate the use of the polygonclass
/**
 * a drawing of a diamond
 * @author open
 *
 */
public class Diamond {
	private int x,y;
	private double scale;
	private GWindow window;
	
	//the polygon to represent the diamond
	private Polygon p;
	
	/*
	 * 
	 * creates a diamond at (x,y) whit the given scale 
	 * @param x    the x-coordinate of the diamond
	 * @param y    the y-coordinate of the diamond
	 * @param scale    the scale of the drawing
	 * @param window     the window where to display the diamond
	 */
	
	public Diamond ( int x, int y, double scale, GWindow window){
		this.x=x;
		this.y=y;
		this.scale=scale;
		this.window=window;
		this.draw();
	}
	
	/**
	 * drasw the diamond
	 */
	private void draw(){
		 p=new Polygon(Color.red, true);
		 int h=(int )(50*scale);
		 int w=(int )(30*scale);
		 p.addPoint(x,y-h);
		 p.addPoint(x+w,y);
		 p.addPoint(x,y+h);
		 p.addPoint(x-w,y);
		 
		 window.add(p);
	}
	
	
	/**
	 * rotates the diamond
	 */
	public void rotate(){
		p.rotateAround(x,y,10);
	}
	
}
