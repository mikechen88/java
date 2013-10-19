import java.awt.Color;


import acm.program.GraphicsProgram;
import acm.graphics.*;

public class HelloGraphical extends GraphicsProgram{
	public void run(){
		GLabel helloLabel=new GLabel("Hello World",20,30);
		GOval left=new GOval(10,30, 20,20);
		
		left.setFillColor(Color.red);
		//setFillColor         only change the content inside
		//setColor              set inside and outside color    
		left.setFilled(true);
		
		GOval right=new GOval(40,30, 20,20);
		right.setFillColor(Color.red);
		right.setFilled(true);
		
		GRect nose=new GRect( 30,60,10,10);
		nose.setFillColor(Color.blue);
		nose.setFilled(true);
		
		GRect mouth=new GRect( 10,80,50,20);
		mouth.setFillColor( Color.black);
		mouth.setFilled(true);
		
		add(left);
		add(helloLabel);
		add(right);
		add(nose);
		add(mouth);
	}
	

}
