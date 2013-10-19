import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>
 * A FaceScene displays smiling faces in a graphics window
 * </p>
 * 
 * @author Your name here
 */

public class FaceScene {

	/**
	 * Creates a picture
	 */
	public FaceScene() {
		// The graphics window
		// The window is by default 500 wide and 400 high

			GWindow  window=new GWindow();
		// Exit when closing the window.
			window.setExitOnClose();

		// Background (e.g. cyan)
			Rectangle bg=new Rectangle ( 0,0,500,400,Color.white,true);
			window.add( bg);
		// Create the scene elements
		// e.g. a face in the lower area 1.5 times the normal size
	}

	/**
	 * Starts the application
	 */
	public static void main(String[] args) {
		FaceScene bb=	new FaceScene();
		
	}
}