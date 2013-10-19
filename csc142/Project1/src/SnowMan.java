import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

/**
 * <p>Create a snow man in a graphics window</p>
 * @author your name here
 */  

public class SnowMan {


	  // Instance fields
	  // The graphics window this tree belongs to
	  private GWindow window;
	  // The location of this snowman
	  // (precisely (as done in the draw method), (x,y) is
	  // tthe center of the head of the snow man
	  private int x;
	  private int y;
	  // The scale used to draw this tree
	  private double scale;
	  //h_radius     is the head radius
	  //b_radius     is the body radius
	  //e_radius     is the eye and button radius
	  
	  private int h_radius=30 ;
	  private int b_radius=60;
	  private int e_radius=5;
	  
	  //n_width is the length of the nose
	  private int n_width=7;
	  
	  //m_width is the width of the mouth
	  //m_height is the height of the mouth
	  private int m_width=20;
	  private int m_height=6;
	  
	  //h_width is the width of the brim
	  //h_height is the height of the brim
	  private int b_width=50;
	  private int b_height=5;
	  
	  //t_width is the width of the brim
	  //t_height is the height of the brim
	  private int t_width=30;
	  private int t_height=50;
	  
	  //h_length  is the length of the hand
	  private int h_length=70;
	 
  /**
   * Create a snow man in at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the head of the snow man
   * @param y the y coordinate of the center of the head of the snow man
   * @scale the factor that multiplies all default dimensions for this snow man
   * (e.g. if the default head radius is 20, the head radius of this snow man is
   * scale * 20)
   * @window the graphics window this snow man belongs to
   */
  public SnowMan(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	  this .x=x;
	  this.y=y;
	  this.scale=scale;
	  this.window=window;
	  

    // Put the details of the drawing in a private method
    this.draw();
  }

  /** Draw in the graphics window a snow man at location (x,y) */
  private void draw()
  {
	  //draw head
	  Oval head=new Oval((int)((this.x-h_radius)*scale),(int )((this.y-h_radius)*scale),(int)(2*h_radius*scale),(int)(2*h_radius*scale),Color.white,true);
	  this.window.add(head);
	  
	  //draw body
	  Oval body=new Oval((int)((this.x-b_radius)*scale),(int )((this.y+20)*scale),(int)(2*b_radius*scale),(int)(2*b_radius*scale),Color.white,true);
	  this.window.add(body);
	  
	  //draw nose
	  Triangle nose=new Triangle((int)(this.x*scale) ,(int)(this.y*scale),  (int)((this.x-n_width)*scale),(int)((this.y+n_width*2)*scale),(int)((this.x+n_width)*scale),(int)((this.y+n_width*2)*scale),Color.orange,true);
	  this.window.add(nose);
	  
	  //draw left eyes
	  Oval l_eye=new Oval((int )((this.x-3*e_radius)*scale), (int )((this.y-n_width*2)*scale),(int)(2*e_radius*scale),(int)(2*e_radius*scale),Color.black,true);
	  this.window.add(l_eye);
	  
	//draw right eyes
	  Oval r_eye=new Oval((int )((this.x+e_radius)*scale), (int)((this.y-n_width*2)*scale),(int)(2*e_radius*scale),(int)(2*e_radius*scale),Color.black,true);
	  this.window.add(r_eye);
	  
	  //draw the mouth
	  Rectangle mouth=new Rectangle ( (int )((this.x-m_width/2)*scale),(int)((this.y+16)*scale),(int)(m_width*scale),(int)(m_height*scale),Color.black,true);
	  this.window.add(mouth);
	  
	  //draw the brim
	  Rectangle brim=new Rectangle ( (int )((this.x-b_width/2)*scale),(int)((this.y-27)*scale),(int)(b_width*scale),(int)(b_height*scale),Color.yellow,true);
	  this.window.add(brim);
	  
	//draw the hat
	  Rectangle hat=new Rectangle ( (int )((this.x-t_width/2)*scale),(int)((this.y-27-t_height)*scale),(int)(t_width*scale),(int)(t_height*scale),Color.red,true);
	  this.window.add(hat);
	  
	  //draw button 
	  Oval button2=new Oval( (int)((this.x-e_radius)*scale),(int)((this.y+20+b_radius-e_radius)*scale),(int)((2*e_radius)*scale),(int)((2*e_radius)*scale),Color.black,true);
	  this.window.add(button2);
	  Oval button1=new Oval( (int)((this.x-e_radius)*scale),(int)((this.y+20+b_radius/2-e_radius)*scale),(int)((2*e_radius)*scale),(int)((2*e_radius)*scale),Color.black,true);
	  this.window.add(button1);
	  Oval button3=new Oval( (int)((this.x-e_radius)*scale),(int)((this.y+20+b_radius*3/2)*scale),(int)((2*e_radius)*scale),(int)((2*e_radius)*scale),Color.black,true);
	  this.window.add(button3);
	  
	  //draw left hands
	  Line l_hand=new Line((int)((this.x-h_radius)*scale) ,(int)((this.y+20+b_radius/2-e_radius)*scale),(int)((this.x-h_radius-h_length)*scale),(int)((this.y+20+b_radius/2-e_radius)*scale));
	  this.window.add(l_hand);
	  
	//draw right hands
	  Line r_hand=new Line((int)((this.x+h_radius)*scale) ,(int)((this.y+20+b_radius/2-e_radius)*scale),(int)((this.x+h_radius+h_length)*scale),(int)((this.y+20+b_radius/2-e_radius)*scale));
	  this.window.add(r_hand);
	  
	//draw left finger
	  Line l_finger=new Line((int)((this.x-h_radius-h_length+20)*scale) ,(int)((this.y+20+b_radius/2-e_radius)*scale),(int)((this.x-h_radius-h_length)*scale),(int)((this.y+20+b_radius/2-e_radius-10)*scale));
	  this.window.add(l_finger);
	  
	//draw right finger
	  Line r_finger=new Line((int)((this.x+h_radius+h_length-20)*scale) ,(int)((this.y+20+b_radius/2-e_radius)*scale),(int)((this.x+h_radius+h_length)*scale),(int)((this.y+20+b_radius/2-e_radius-10)*scale));
	  this.window.add(r_finger);
	  
  }
}
