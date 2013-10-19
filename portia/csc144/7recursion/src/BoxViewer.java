import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class BoxViewer extends JPanel {

	// The box viewed by this viewer
	private Box box;

	/**
	 * Creates a box viewer within a frame
	 */
	public BoxViewer() {
		// Create a frame for this panel
		JFrame frame = new JFrame("Box viewer");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);

		// the box
		int h=getHeight();
		int w=getWidth();
		box = new Box(w/2,h/2,h,w,Color.RED);
		//repaint();
		//a CLICK ON the frame adds an inner box to the box
		//use an anonymous class
		
		MouseListener listener2=new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				box.addInnerBox();
				repaint();
			}
		};
		addMouseListener(listener2);
		
		
		
		
		
		/*public void mouseClicked(MouseEvent e) {
		MouseListener listener= new MouseListener(){
		
				
		
			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		*/
		
	
		
		

		/*
		 * setBackground(Color.WHITE); // Create a frame for this panel JFrame
		 * frame = new JFrame("Box viewer");
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.setSize(500, 500); frame.add(this); frame.setVisible(true); //
		 * Resize the frame so that the panel is exactly 501 x 501 Insets insets
		 * = frame.getInsets(); frame.setSize(501 + insets.left + insets.right,
		 * 501 + insets.top + insets.bottom); frame.validate(); // the box box =
		 * new Box(250, 250, 500, 500, Color.RED); repaint();
		 * 
		 * // uncomment the listener that you want to use // If both are
		 * uncommented, two inner boxes are added // for every mouse click.
		 * 
		 * // BoxController bc = new BoxController(box, this); //
		 * this.addMouseListener(bc);
		 * 
		 * // or with an inner anonymous class // A click on the frame adds an
		 * inner box to the box this.addMouseListener(new MouseAdapter() {
		 * public void mousePressed(MouseEvent e) { box.addInnerBox();
		 * System.out.println(box); repaint(); } });
		 */
	}

	/**
	 * Paints the contents of this viewer
	 * 
	 * @param g
	 *            the graphics context to use.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (box != null) {
			box.draw(g);
		}
	}

	/**
	 * Starts the application.
	 * 
	 * @param args
	 *            the list of the command line parameters.
	 */
	public static void main(String[] args) {
		new BoxViewer();
	}
}
