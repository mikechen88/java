/**
 * a first graphics example: a window with a circle
 * 
 * @author csc142
 *
 */
import java.awt.Color;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
public class WindowWithCircle {
	//use classes from the uw graphics library
	private GWindow window;
	private Oval circle;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Color c;
	private boolean fill;
	
	/**
	 * create a window with a circle
	 */
	public WindowWithCircle(){
		this.window=new GWindow();
		//this.circle=new Oval();
		circle=new Oval(200,150,100,100,Color.red,true);
		window.add( circle);
	}
	
	
	public static void main(String [] args){
		//WindowWithCircle w=new WindowWithCircle();
		
		WindowWithCircle w=new WindowWithCircle();
	}
}
