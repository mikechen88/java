import java.awt.Color;
import uwcse.graphics.*;

/**
 * A class to draw a pine tree
 */

public class PineTree {

	// The graphics window this Tree belongs to
	private GWindow window;

	/**
	 * Creates a PineTree
	 * 
	 * @param x
	 *            the x coordinate of the tree location (lower left corner of
	 *            the tree trunk)
	 * @param y
	 *            the y coordinate of the tree location
	 * @param window
	 *            the graphics window this Tree belongs to
	 */
	public PineTree(int x, int y, GWindow window) {
	}

	/**
	 * Draws a pine tree at the location x,y
	 * 
	 * @param x
	 *            the x coordinate of the lower left corner of the tree trunk
	 * @param y
	 *            the y coordinate of the lower left corner of the tree trunk
	 */
	private void drawPineTree(int x, int y) {
		// a pine tree is a brown rectangle (the trunk) and 3 green triangles
		// (the foliage)
		// the code below has the positioning already done
		
		 // trunk new Rectangle(x,y,20,60,Color.black,true); // foliage 
		 //new Triangle(x-30,y+30,x+10,y-10,x+50,y+30,this.currentFoliageColor,true); 
		 //new Triangle (x-25,y+30,x+10,y-10,x+45,y+30,this.currentFoliageColor,true); //
		 //* then do a moveBy(0,-20);
		 //new Triangle(x-20,y+30,x+10,y-10,x+40,y+30,this .currentFoliageColor,true); // then do a moveBy(0,-40);	 
	}

	// add a method below to switch the color of the foliage
	// between green and red
	
	/**
	 * flips the colors of the tree
	 */
	public void flipColor(){
		
	}

}