import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

public class Bird {
	// Instance fields
		  // The graphics window this tree belongs to
		  private GWindow window;
		  //location of the bird
		  //(x,y)is the center of the bird
		private int x;
		private int y;
		private double scale;
		
		//bird 's head radius
		private int h_radious=50;
		
		//bird's eye radius
		private int e_width=10;
		
		//bird's eye ball width
		private int b_width=5;
	
		public Bird(int x, int y, double scale, GWindow window){
			this.x=x;
			this.y=y;
			this.scale=scale;
			this.window=window;
			
			this.draw();
		}
		
		public void draw(){
			
			//draw head
			Oval head=new  Oval((int)((this.x-h_radious)*scale),(int)((this.y-h_radious)*scale),(int)(2.5*h_radious),2*h_radious,Color.red,true);
			this.window.add(head);
			
			//draw left eye and ball
			Oval l_eye=new  Oval((int)((this.x-15)*scale),(int)((this.y-20)*scale),(int)(e_width*2.5*scale),(int)(e_width*2*scale),Color.white,true);
			this.window.add(l_eye);
			Oval l_ball=new  Oval((int)((this.x-0.2)*scale),(int)((this.y-19)*scale),(int)(b_width*2*scale),(int)(b_width*2*scale),Color.black,true);
			this.window.add(l_ball);
			
			//draw right eye and eye ball
			Oval r_eye=new  Oval((int)((this.x+15)*scale),(int)((this.y-20)*scale),(int)(e_width*2.5*scale),(int)(e_width*2*scale),Color.white,true);
			this.window.add(r_eye);
			Oval r_ball=new  Oval((int)((this.x+16)*scale),(int)((this.y-19)*scale),(int)(b_width*2*scale),(int)(b_width*2*scale),Color.black,true);
			this.window.add(r_ball);
			
			//draw left eye brow
			Line l_eyebrow1=new Line( (int)((this.x+10)*scale),(int)((this.y-20)),(int)((this.x-8)*scale),(int)((this.y-30)*scale),Color.black);
			this.window.add(l_eyebrow1);
			Line l_eyebrow2=new Line( (int)((this.x+10)*scale),(int)((this.y-20)),(int)((this.x-11)*scale),(int)((this.y-40)*scale),Color.black);
			this.window.add(l_eyebrow2);			
			Line l_eyebrow3=new Line( (int)((this.x+10)*scale),(int)((this.y-20)),(int)((this.x-13)*scale),(int)((this.y-60)*scale),Color.black);
			this.window.add(l_eyebrow3);
			
			//draw right eye brow
			Line r_eyebrow1=new Line( (int)((this.x+16)*scale),(int)((this.y-20)),(int)((this.x+34)*scale),(int)((this.y-30)*scale),Color.black);
			this.window.add(r_eyebrow1);
			Line r_eyebrow2=new Line( (int)((this.x+16)*scale),(int)((this.y-20)),(int)((this.x+38)*scale),(int)((this.y-40)*scale),Color.black);
			this.window.add(r_eyebrow2);			
			Line r_eyebrow3=new Line( (int)((this.x+16)*scale),(int)((this.y-20)),(int)((this.x+39)*scale),(int)((this.y-60)*scale),Color.black);
			this.window.add(r_eyebrow3);
			
			//draw nose
			Triangle u_nose=new Triangle(  (int)((this.x+14)*scale),(int)((this.y+5)*scale),(int)((this.x-5)*scale),(int)((this.y+14)*scale),(int)((this.x+33)*scale),(int)((this.y+14)*scale),Color.yellow,true);
			this.window.add(u_nose);
			Triangle d_nose=new Triangle(  (int)((this.x+14)*scale),(int)((this.y+50)*scale),(int)((this.x-5)*scale),(int)((this.y+14)*scale),(int)((this.x+33)*scale),(int)((this.y+14)*scale),Color.yellow,true);
			this.window.add(d_nose);
			Line m_nose=new Line ((int)((this.x+14)*scale),(int)((this.y+5)*scale),(int)((this.x+14)*scale),(int)((this.y+50)*scale),Color.orange);
			this.window.add(m_nose);
		}
}
