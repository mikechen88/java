import java.awt.*;
import javax.swing.*;

/**
 * Demonstration program for simple simulation framework. Create a bunch of
 * balls and let them wander around the screen. Demonstrates model-view (without
 * controller) design.
 */
public class BallSim {

	/** Run simulation */
	public static void main(String[] args) {
		// Create a graphics view and put it in a window
		JFrame frame = new JFrame("Ball demo");
		BallGraphicsView view = new BallGraphicsView();
		frame.setBackground(Color.white);
		frame.getContentPane().add(view);
		frame.setSize(304, 325); // a bit of extra space for insets
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);

		// Create the simulation model and populate it
		SimModel balls = new SimModel();
		balls.add(new Ball(50, 60, 3, -3, Color.red, 12, 300, 300));
		balls.add(new Ball(150, 150, -5, -5, Color.green, 15, 300, 300));

		// Connect the view to the simulation and let it run
		balls.addView(view);
		balls.go(200);

	}
}
