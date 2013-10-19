import uwcse.graphics.*;
import java.awt.Color;

/**
 * A picture (to illustrate the use of several classes)
 */

public class FunnyPicture extends GWindowEventAdapter {

	// The graphics window to display the picture
	private GWindow window;

	// Two smiling faces
	// (make them instance variables since they are accessed in
	// several methods)
	private SmilingFace face1;

	private SmilingFace face2;

	private PineTree tree1;

	private PineTree tree2;
	
	private Diamond d1,d2;

	// for the animation
	private int animationCounter;
	
	//scale of face1
	private double scale1;
	
	

	/**
	 * Creates the picture
	 */
	public FunnyPicture() {
		// Create the graphics window
		this.window = new GWindow("Funny picture", 300, 300);
		// Terminate the application when the user closes the window
		this.window.setExitOnClose();

		// Background
		Rectangle bng = new Rectangle(0, 0, this.window.getWindowWidth(),
				this.window.getWindowHeight(), Color.cyan, true);
		this.window.add(bng);

		// Draw the picture
		scale1=0.5;
		this.face1 = new SmilingFace(100, 100, 0.5, this.window);
		this.face2 = new SmilingFace(200, 200, 1.5, this.window);

		// The trees
		this.tree1 = new PineTree(50, 150, this.window);
		this.tree2 = new PineTree(250, 150, this.window);
		
		//the diamonds
		this.d1=new Diamond(200,100,0.5,window);
		this.d2=new Diamond( 300,200,1.5,window);
		// Code to do the animation
		this.window.addEventHandler(this);
		this.window.startTimerEvents(150); // redraw every 150 ms
	}
	


	// To do the animation
	public void timerExpired(GWindowEvent event) {
		this.window.suspendRepaints();
		
		if (animationCounter <= 100) {
			this.face1.moveBy(1, 1);
			scale1 *=1.02; 
			this.face1 .changeScale(scale1);
			
			this.face2.moveBy(1, 1);			
			this.face2 .changeScale(scale1);
			this.face2.rotateNose();
			this.tree1.flipColor();
		} else {
			this.face1.moveBy(-1, -1);
			scale1 /=1.02; 
			this.face1 .changeScale(scale1);
			this.face2.moveBy(-1, -1);
			this.face2 .changeScale(scale1);
			this.tree2.flipColor();
		}
		this.d1.rotate();
		this.d2.rotate();
		// this.myElement.doAction();
		this.window.resumeRepaints();

		// Run the animation 200 times (about 30 s)
		this.animationCounter++;
		if (this.animationCounter >= 200)
			this.window.stopTimerEvents();
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		new FunnyPicture();
	}

}