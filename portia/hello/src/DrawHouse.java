import acm.program.GraphicsProgram;
import acm.graphics.*;

public class DrawHouse extends GraphicsProgram
{	
	public void run()
	{
		setSize(500,500);
		drawHouse(50,200, 400, 360);
		
	}
	
	/*
	 * draws a house width the given width and height
	 * x is the left most coordinate of the house
	 * y is the top coordinate of the main part of the house
	 */
	public void drawHouse(double x, double y, double width, double height)
	{
		/*
		 * This method will call drawOutline,
		 * drawDoor, and drawWindow twice... 
		 * the variable it calls these methods with will be derived from the parameters
		 */
		drawOutline( x,  y,  width,  height);
		drawWindow(x+(0.1*width), y+(0.1*height), 0.25*height,0.25*height);
		drawWindow(x+(0.7*width), y+(0.1*height), 0.25*height,0.25*height);
		
	}
	
	/*
	 * Draws the outline of the house with the given width and height
	 * x is the left most coordinate of the house
	 * y is the top coordinate of the main part of the house
	 */
	public void drawOutline(double x, double y, double width, double height)
	{
		double mainHouseHeight = height * 3/4;
		double atticHeight = height - mainHouseHeight;
		
		GRect mainHouse = new GRect(x, y, width, mainHouseHeight);
		GRect attic = new GRect(x, y - atticHeight, width, atticHeight);
		drawLine(x,y,x+(0.5*width),y- atticHeight);
		drawLine(x+(0.5*width),y- atticHeight,x+width,y);
		add(mainHouse);
		add(attic);
	}
	
	/*
	 * Draws a door with the upper left hand corner x, y
	 * with the given door width and height
	 */
	public void drawDoor(double x, double y, double width, double height)
	{
		GRect temp = new GRect(x, y, width, height);
		add(temp);
	}
	
	/*
	 * Draws a window with the upper left hand corner x, y
	 * with the given width and height 
	 */
	public void drawWindow(double x, double y, double width, double height)
	{
		GRect temp = new GRect(x,y, width, height);
		add(temp);
				
		drawLine( x, y+(0.5*height),x+width, y+(0.5*height));	
		drawLine( x+(0.5*height), y,x+(0.5*height),y+height);
		
		
	}
	public void drawLine(double x, double y, double z, double k)
	{
		GLine gline = new GLine(x, y, z, k); 
		add(gline);
	}

	
}
