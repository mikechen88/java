import uwcse.graphics.*;
import java.awt.Color;

/**
 * A smiling face in a graphics window This is the solution of last week's lab.
 * Adapt it to add two methods: public void moveTo(int x, int y) to move the
 * face to (x,y) public void moveBy(int dx, int dy) to move the face by dx and
 * dy
 */

public class SmilingFace {

	// The graphics window
	private GWindow window;

	// Location of the face
	private int x, y;

	// Scale used to draw the face
	private double scale;
	
	
	
	private Oval head,blackCircle, yellowCircle;
	
	private Oval rightPupil, leftPupil, rightEye,leftEye;

	private Triangle nose;
	
	//for the rotation of the nose
	private double angle;
	
	
	/**
	 * Draws a smiling face in a graphics window
	 * 
	 * @param x
	 *            the x coordinate of the center of the face
	 * @param y
	 *            the y coordinate of the center of the face
	 * @param scale
	 *            the multiplication factor for all default dimensions
	 * @param window
	 *            the graphics window where to draw
	 */
	public SmilingFace(int x, int y, double scale, GWindow window) {
		// Initialize the instance fields
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;

		// Draw the face in the graphics window
		this.drawFace();
	}
	
	/**
	 * redraws the face with a new scale
	 * @param s the new scale
	 */
	public void changeScale(double  s){
		//remove the previous drawing of the face
				erase();
				//update the the scale of the face		
				scale=s;				
				//redraw
				drawFace();
	}
	
	/**
	 * rotates the nosse of the face
	 */
	public void rotateNose(){
		angle +=10;//same as angle =angle+10
		if (angle>360){
			angle-=360;
		}
		nose.rotateAround(x, y, angle);
	}
	
	
	
	
	/**
	 * Erases the face from the window
	 */
	private void erase (){
		//remove the previous drawing of the face 
				this.window.remove(head);		
				this.window .remove(nose);
				this.window.remove(blackCircle);
				this.window.remove(yellowCircle);
				this.window.remove(leftPupil);
				this.window.remove(rightPupil);
				this.window.remove(leftEye);
				this.window.remove(rightEye);
	}
	/**
	 * Draw a face in the graphics window
	 */
	private void drawFace() {
		// The face (a circle)
		int faceRadius = (int) (50 * this.scale);
		head = new Oval(this.x - faceRadius, this.y - faceRadius,
				2 * faceRadius, 2 * faceRadius, Color.yellow, true);
		this.window.add(head);

		// The mouth
		this.drawMouth(this.x, this.y + 9 * faceRadius / 10);

		// The eyes
		// left
		this.drawEye(this.x - faceRadius / 2, this.y);
		// right
		this.drawEye(this.x + faceRadius / 2, this.y);

		// The nose
		this.drawNose(this.x, this.y);

		// Show the face
		this.window.doRepaint();
	}

	/**
	 * Draws an eye
	 * 
	 * @param eyex
	 *            the x coordinate of the center of the eye
	 * @param eyey
	 *            the y coordinate of the center of the eye
	 */
	private void drawEye(int eyex, int eyey) {
		// A black circle in a white oval
		int eyeHalfWidth = (int) (15 * this.scale);
		int eyeHalfHeight = (int) (8 * this.scale);
		Oval eye = new Oval(eyex - eyeHalfWidth, eyey - eyeHalfHeight,
				2 * eyeHalfWidth, 2 * eyeHalfHeight, Color.white, true);
		this.window.add(eye);
		int pupilRadius = eyeHalfHeight;
		Oval pupil = new Oval(eyex - pupilRadius, eyey - pupilRadius,
				2 * pupilRadius, 2 * pupilRadius, Color.black, true);
		this.window.add(pupil);
		
		if ( eyex<x){
			leftEye=eye;
			leftPupil=pupil;
		}else{
			rightEye=eye;
			rightPupil=pupil;
		}
	}

	/**
	 * Draws a nose
	 * 
	 * @param nosex
	 *            the x coordinate of the top point of the nose
	 * @param nosey
	 *            the y coordinate of the top point of the nose
	 */
	private void drawNose(int nosex, int nosey) {
		// A nose is a triangle
		int noseHeight = (int) (20 * this.scale);
		int noseWidth = (int) (20 * this.scale);
		nose = new Triangle(nosex, nosey, nosex + noseWidth / 2, nosey
				+ noseHeight, nosex - noseWidth / 2, nosey + noseHeight,
				Color.black, true);
		this.window.add(nose);
	}

	/**
	 * Draws a mouth
	 * 
	 * @param mouthx
	 *            the x coordinate of the middle bottom point of the mouth
	 * @param mouthy
	 *            the y coordinate of the middle bottom point of the mouth
	 */
	private void drawMouth(int mouthx, int mouthy) {
		// Draw two circles (one black and one yellow)
		// The yellow circle is on top of the black circle and slightly shifted
		// up
		int mouthRadius = (int) (30 * this.scale);
		int mouthThickness = (int) (6 * this.scale);
		blackCircle = new Oval(mouthx - mouthRadius, mouthy - 2
				* mouthRadius, 2 * mouthRadius, 2 * mouthRadius, Color.black,
				true);
		this.window.add(blackCircle);
		 yellowCircle = new Oval(mouthx - mouthRadius, mouthy - 2
				* mouthRadius - mouthThickness, 2 * mouthRadius,
				2 * mouthRadius, Color.yellow, true);
		this.window.add(yellowCircle);
	}
	
	/**
	 * moves the face by a given displacement
	 * @param dx    the displacement along x
	 * @param dy     the displacement along y
	 */
	public void moveBy(int dx, int dy){
		
		//remove the previous drawing of the face
		erase();
		//update the location of the face		
		x+=dx;
		y+=dy;
		
		//redraw
		drawFace();
	}
}
