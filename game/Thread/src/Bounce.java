import  java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

//show an animated bouncing ball

public class Bounce {
	public static void main ( String [] agrs){
		JFrame frame= new BounceFrame ();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

// a ball that moves and bounces off the edges of a rectangle 

class Ball{
	//moves the ball to the next position, reversing direction if it hits one of the edges
	
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
	
	public Ellipse2D getShape(){
		return new Ellipse2D.Double(x,y  ,   XSIZE, YSIZE);
	}
	
	private static   final int 	XSIZE=15;
	private static final int YSIZE=15;
	private double x=0;
	private double y=0;
	private double dx=1;
	private double dy=1;
}

//the panel that draws the balls
class BallPanel extends JPanel{
	//add a ball to the panel
	//b       the ball to add
	
	public void add( Ball b ){
		balls.add(b);
	}
	
	public void paintComponent ( Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D )g;
		
		for ( Ball b:balls ){
			g2.fill( b.getShape());
		}
	}
	
	private ArrayList<Ball>    balls   = new ArrayList<Ball>();
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
	
	// add a bouncing ball to the panel and makes it bounce 1000 times
	public void addBall(){
		try{
			Ball ball=new Ball();
			panel.add(ball);
			
			for (int i=1;i<=STEPS;i++){
				ball.move( panel.getBounds());
				panel.paint(panel.getGraphics());
				Thread.sleep(DELAY);
			}
		}catch(  InterruptedException e){
			
		}
	}
	
	private BallPanel   panel;
	 public static final int DEFAULT_WIDTH=450;
	 public static final int DEFAULT_HEIGHT=350;
	 public static final int  STEPS=1000;
	 public static final int   DELAY=3;
		
	
	
}




























