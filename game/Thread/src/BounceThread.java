import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;
import java.util.*;

import javax.swing.*;

// show an animated bouncing ball
public class BounceThread {
	public static void main ( String [] argw){
		JFrame frame=new BounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

// a runnable that animates a bouncing ball.

class BallRunnable implements Runnable{
	//constructs the runnable, 
	// aBall       the ball to bounce 
	// aPanel       the component in which the ball bounces
	
	public BallRunnable ( Ball1 aBall,  Component aComponent){
		ball=aBall;
		component = aComponent;
	}
	
	public void run (){
		try{
			for ( int i=1; i<=STEPS;i++){
				ball.move( component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		}catch( InterruptedException e){
			
		}
	}
	
	private Ball1 ball;
	private Component component;
	public static final int STEPS=1000;
	public static final int DELAY=5;
}
	
//A BALL THAT move and bounces off the edges of a rectangle
class Ball1{
	//move the ball to the next position , reversing direction if it hits one of the edges
	
	public void move (Rectangle2D bounds){
		x+=dx;
		y+=dy;
		if ( x<bounds.getMinX()){
			x=bounds.getMinX();
			dx=-dx;
		}
		
		if ( x+XSIZE>= bounds.getMaxX()){
			x=bounds.getMaxX()-XSIZE;
			dx= -dx;
		}
		
		if ( y<bounds.getMinY()){
			y=bounds.getMinY();
			dy=-dy;
		}
		
		if ( y+YSIZE>= bounds.getMaxY()){
			y=bounds.getMaxY()-YSIZE;
			dy=-dy;
		}		
	}
		
	//gets the shape of the ball at its current position
	public Ellipse2D getShape(){
		return new Ellipse2D.Double( x,y,XSIZE,YSIZE);	
	}
	
	private static   final int 	XSIZE=15;
	private static final int YSIZE=15;
	private double x=0;
	private double y=0;
	private double dx=1;
	private double dy=1;
	}
		
//THE panel that draws the balls
class BallPanel extends JPanel{
	// add a ball to the panel
	// b      the ball to add
	
	public void add ( Ball1 b){
		balls.add( b);		
	}
	
	public void paintComponent ( Graphics g ){
		super .paintComponent ( g);
		Graphics2D g2=(Graphics2D)g;
		
		for ( Ball1 b:balls ){
			g2.fill( b.getShape());
		}
	}
	
	private ArrayList<Ball1>    balls   = new ArrayList<Ball1>();
}

//the frame with panel JFrame 
class BounceFrame extends JFrame{
	//construct the frame with the panel for showing the bouncing ball and start and close buttons
	
	public BounceFrame (){
		setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
		setTitle ("Bounce");
		
		panel =new BallPanel();
		add( panel,BorderLayout.CENTER);
		
		JPanel buttonPanel=new JPanel();
		
		addButton(buttonPanel, "Start",
				new ActionListener(){
			public void actionPerformed ( ActionEvent event){
				addBall();
			}
		});
		
		
		addButton(buttonPanel, "Close",
				new ActionListener(){
			public void actionPerformed ( ActionEvent event){
				System.exit(0);
			}
		});
		
		add ( buttonPanel, BorderLayout.SOUTH);
	}
	
	// add a button to a containeer
	// c      the container
	// title      the button title
	// listener     the action listener for the button
	
	public void addButton(  Container c, String title, ActionListener  listener){
		JButton button =new JButton ( title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	//adds a bouncing ball to the canvas and starts a thread to make it bounce
	
	public void addBall(){
		Ball1 b = new Ball1();
		panel.add(b);
		Runnable r= new BallRunnable ( b,panel);
		Thread t = new Thread( r);
		t.start();
	}
	private BallPanel   panel;
	 public static final int DEFAULT_WIDTH=450;
	 public static final int DEFAULT_HEIGHT=350;
	 public static final int  STEPS=1000;
	 public static final int   DELAY=3;
	
	
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
