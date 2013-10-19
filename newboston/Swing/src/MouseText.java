import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.*;

public class MouseText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MouseFrame frame= new MouseFrame ();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setVisible (true);
	}
}
//a frame containing a panel for testing mouse operations
class MouseFrame extends JFrame{
	public MouseFrame(){
		setTitle( "Mouse Test");
		setSize (DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add panel to frame
		MousePanel   panel= new MousePanel();
		add (panel);
	}
	
	public static final int DEFAULT_WIDTH=300;
		public static final int 	DEFAULT_HEIGHT=200;
}
// A PANEL with mouse operations for adding and removing squares
class MousePanel extends JPanel{
	public MousePanel(){
		squares=new ArrayList<Rectangle2D>(); 
		current=null;
		addMouseListener( new MouseHandler());
		addMouseMotionListener (new MouseMotionHandler());
	}
	public void paintComponent ( Graphics g ){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D) g;
		
		//draw all squares
		for (Rectangle2D r:squares)
			g2.draw(r);
	}
	
	//find the first square containing a point .
	//  p   is a point
	//return      the first square that contains p
	public Rectangle2D find (Point2D p){
		for ( Rectangle2D  r: squares){
			if (r.contains(p) )  return  r;
		}
		return null;
	}
	
	//adds a square to the collection
	//p   is the center of the square
	public void add( Point2D p){
		double x=p.getX();
		double y=p.getY();
		
		current= new Rectangle2D.Double(
				x-SIDELENGTH/2,
				y-SIDELENGTH/2,
				SIDELENGTH,
				SIDELENGTH
				);
			squares.add ( current);
			repaint();
	}
	
	//removes a square from the collection
	//s    is the square to remove
	public void remove ( Rectangle2D s){
		if ( s==null) return;
		if ( s==current) current=null;
		squares.remove(s);
		repaint();
	}
	
	private static final int SIDELENGTH=10;
	private ArrayList<Rectangle2D> squares;
	private Rectangle2D current;
	
	//the square containing the mouse cursor
	
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent event){
			//add a new square if the cursor isn't inside a square
			current =find (event.getPoint());
			if ( current==null)
				add(event.getPoint());
		}
		
		public void mouseClicked (MouseEvent event){
			//remove the current square if double clicked
			current = find ( event.getPoint());
			if ( current !=null&& event.getClickCount()>=2)
				remove (current);
		}
	}
	
	
	private class MouseMotionHandler implements MouseMotionListener{
		public void mouseMoved ( MouseEvent event){
			//set the mouse cursor to cross hairs if it is inside a rectangle
			
			if ( find (event.getPoint())==null)
				setCursor( Cursor.getDefaultCursor());
			else
				setCursor( Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO Auto-generated method stub
			if (current !=null){
				int x=event.getX();
				int y=event.getY();
				
				//drag the current rectangle to center it at (x, y)
				current.setFrame(
						x-SIDELENGTH/2,
						y-SIDELENGTH/2,
						SIDELENGTH,
						SIDELENGTH						
						);
				repaint();
			}
		}
		
	
	}
	
	
	
	
	
}











