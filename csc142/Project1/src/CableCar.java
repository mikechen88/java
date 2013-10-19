import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class


/**
 * <p>Create a cable car in a graphics window</p>  
 * @author your name here
 */

public class CableCar {

	// Instance fields
	  // The graphics window this tree belongs to
	  private GWindow window;
	  //location of the car
	  //(x,y)is the left upper corner of the car
	private int x;
	private int y;
	
	//width, height is for the cable car
	private int width=300;
	private int height=150;
	// The scale used to draw this tree
    private double scale;
	
    //the size of the window
    private int size=35;

  /**
   * Create a cable car at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the cable car
   * @param y the y coordinate of the center of the cable car
   * @scale the factor that multiplies all default dimensions for this cable car
   * (e.g. if the default size is 80, the size of this cable car is
   * scale * 80)
   * @window the graphics window this cable car belongs to
   */
  public CableCar(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	  this.x=x;
	  this.y=y;
	  this.scale=scale;
	  this.window=window;

    // The details of the drawing are in a private method
    this.draw();
  }

  /** Draw a cable car at location (x,y) */
  private void draw()
  {
	  //draw a car
	  Rectangle rec=new Rectangle((int )(this. x*scale),(int)(this.y*scale),(int)(this.width*scale),(int)(this.height*scale),Color.blue,true);
	  this.window.add(rec);
	  
	  //draw three windows
	  Rectangle win2=new Rectangle ((int)(this. x+(this.width-this.size)*scale/2),(int)( this.y+(this.height-this.size)*scale/2),(int)(this.size*scale),(int)(this.size*scale),Color.white,true);
	  Rectangle win1=new Rectangle ((int)(this. x+this.width*scale/4),(int)( this.y+(this.height-this.size)*scale/2),(int)(this.size*scale),(int)(this.size*scale),Color.white,true);
	  Rectangle win3=new Rectangle ((int)(this. x+this.width*scale*3/4-this.size),(int)( this.y+(this.height-this.size)*scale/2),(int)(this.size*scale),(int)(this.size*scale),Color.white,true);
		
	  
	  this.window.add(win1);
	  this.window.add(win2);
	  this.window.add(win3);
	  
	  //draw the cable
	  Triangle tri=new Triangle( (int)((this.x+this.width/2-57)*scale),this.y, (int)((this.x+this.width/2+57)*scale),this.y,(int)((this.x+this.width/2)*scale),(int)((this.y-57)*scale));
	  this.window.add(tri);
	  
	  Line line=new Line( 0,(int)((this.y-57)*scale),window.getWindowWidth(),(int)((this.y-57)*scale));
	  this.window.add(line);
	  
  }
}
