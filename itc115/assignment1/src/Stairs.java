/**
 * assignment 1 
 * build a colorful stair to java empire
 * 
 */
import acm.graphics.*;
import acm.io.IODialog;
import acm.program.DialogProgram;
import acm.program.GraphicsProgram;
import java.awt.Color;

public class Stairs extends GraphicsProgram {

	// forbidden area
	private static final int APP_WIDTH = 300;
	private static final int APP_HEIGHT = 300;
	private static final int ST_WIDTH = 50;
	private static final int ST_HEIGHT = 20;
	private static final int LOWER = 5;
	private static final int HEIGHT = 10;

	public void run() {
		
		// setup enivorment
		setSize(APP_WIDTH, APP_HEIGHT);
		IODialog dialog = new IODialog();
		int level = 0;
		
		// budget for the level of stair
		while (true) {
			level = dialog.readInt("	Number of Stairs:between 5 and 10	",
					LOWER, HEIGHT);
			if (level <= HEIGHT && level >= LOWER)
				break;
		}
		
		// building stairs
		for (int i = 0; i < level; i++) {
			GRect grect = new GRect(ST_WIDTH * i / 2, ST_HEIGHT * i, ST_WIDTH,
					ST_HEIGHT);
			if (i < level / 2) {
				grect.setColor(Color.blue);
			} else {
				grect.setColor(Color.red);
			}

			//add to the screen
			grect.setFilled(true);
			add(grect);
		}

	}
}
