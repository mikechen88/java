import java.awt.*;
import javax.swing.*;

/**
 * Demonstration program for simple simulation framework. Create a bunch of
 * balls and let them wander around the screen. Demonstrates model-view (without
 * controller) design.
 */
public class MainClass {

	/** Run simulation */
	public static void main(String[] args) {
		// Create a graphics view and put it in a window
		JFrame frame = new JFrame("Ball demo");
		View view = new View();
		frame.setBackground(Color.white);
		frame.getContentPane().add(view);
		frame.setSize(300, 300); // a bit of extra space for insets
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);

		// Create the simulation model and populate it
		DrawingModle balls = new DrawingModle();
		balls.add(new Diamond(50,50,20, 160,200));
		

		// Connect the view to the simulation and let it run
		balls.addView(view);
		balls.go(200);

	}
}
