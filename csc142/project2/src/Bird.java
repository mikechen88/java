import uwcse.graphics.*; // access the graphics utilities in the uw library

import java.awt.Color; // access the Color class

public class Bird {
	// Instance fields
	// The graphics window this tree belongs to
	private GWindow window;
	// location of the bird
	// (x,y)is the center of the bird
	private int x;
	private int y;
	private double scale;

	// bird 's head radius
	private int h_radious = 50;

	// bird's eye radius
	private int e_width = 10;

	// bird's eye ball width
	private int b_width = 5;

	// define upside nose
	private Triangle u_nose;

	// define downside nose
	private Triangle d_nose;

	// define middle nose line
	private Line m_nose;

	// define a boolean;
	private boolean bool;

	// nose 's angle
	private double angle;

	// define eye ball
	private Oval l_ball;
	private Oval r_ball;

	// define eyebrow
	private Line l_eyebrow1;
	private Line l_eyebrow2;
	private Line l_eyebrow3;

	private Line r_eyebrow1;
	private Line r_eyebrow2;
	private Line r_eyebrow3;

	// constructor
	public Bird(int x, int y, double scale, GWindow window) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.window = window;
		bool = true;

		this.draw();
	}

	// draw the bird
	public void draw() {

		// draw head
		Oval head = new Oval((int) ((this.x - h_radious) * scale),
				(int) ((this.y - h_radious) * scale), (int) (2.5 * h_radious),
				2 * h_radious, Color.red, true);
		this.window.add(head);

		// draw left eye and ball
		Oval l_eye = new Oval((int) ((this.x - 15) * scale),
				(int) ((this.y - 20) * scale), (int) (e_width * 2.5 * scale),
				(int) (e_width * 2 * scale), Color.white, true);
		this.window.add(l_eye);

		// draw right eye and eye ball
		Oval r_eye = new Oval((int) ((this.x + 15) * scale),
				(int) ((this.y - 20) * scale), (int) (e_width * 2.5 * scale),
				(int) (e_width * 2 * scale), Color.white, true);
		this.window.add(r_eye);
		stupid();

		// draw nose
		u_nose = new Triangle((int) ((this.x + 14) * scale),
				(int) ((this.y + 5) * scale), (int) ((this.x - 5) * scale),
				(int) ((this.y + 14) * scale), (int) ((this.x + 33) * scale),
				(int) ((this.y + 14) * scale), Color.yellow, true);
		this.window.add(u_nose);
		d_nose = new Triangle((int) ((this.x + 14) * scale),
				(int) ((this.y + 50) * scale), (int) ((this.x - 5) * scale),
				(int) ((this.y + 14) * scale), (int) ((this.x + 33) * scale),
				(int) ((this.y + 14) * scale), Color.yellow, true);
		this.window.add(d_nose);
		m_nose = new Line((int) ((this.x + 14) * scale),
				(int) ((this.y + 5) * scale), (int) ((this.x + 14) * scale),
				(int) ((this.y + 50) * scale), Color.orange);
		this.window.add(m_nose);

	}

/*	// draw animation when bird are angry and stupid
	public void move() {
		erase();

		if (bool) {

			stupid();

			bool = false;
		} else {

			angry();
			bool = true;
		}

	}*/

	// angry bird
	public void angry() {
	
		l_ball = new Oval((int) ((this.x - 0.2) * scale),
				(int) ((this.y - 9) * scale), (int) (b_width * 2 * scale),
				(int) (b_width * 2 * scale), Color.black, true);
		this.window.add(l_ball);
		r_ball = new Oval((int) ((this.x + 16) * scale),
				(int) ((this.y - 9) * scale), (int) (b_width * 2 * scale),
				(int) (b_width * 2 * scale), Color.black, true);
		this.window.add(r_ball);

		r_eyebrow1 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x + 34) * scale),
				(int) ((this.y - 30) * scale), Color.black);
		this.window.add(r_eyebrow1);
		r_eyebrow2 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x + 38) * scale),
				(int) ((this.y - 40) * scale), Color.black);
		this.window.add(r_eyebrow2);
		r_eyebrow3 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x + 39) * scale),
				(int) ((this.y - 60) * scale), Color.black);
		this.window.add(r_eyebrow3);

		// draw left eye brow
		l_eyebrow1 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x - 8) * scale),
				(int) ((this.y - 30) * scale), Color.black);
		this.window.add(l_eyebrow1);
		l_eyebrow2 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x - 11) * scale),
				(int) ((this.y - 40) * scale), Color.black);
		this.window.add(l_eyebrow2);
		l_eyebrow3 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 20) * scale), (int) ((this.x - 13) * scale),
				(int) ((this.y - 60) * scale), Color.black);
		this.window.add(l_eyebrow3);
	}

	//call angry () 
	public void angryy(){
		erase();
		angry();
	}
	//call stupidd()
	public void stupidd(){
		erase();
		stupid();
	}
	// stupid bird
	public void stupid() {
	
		l_ball = new Oval((int) ((this.x - 0.2) * scale),
				(int) ((this.y - 19) * scale), (int) (b_width * 2 * scale),
				(int) (b_width * 2 * scale), Color.black, true);
		this.window.add(l_ball);
		r_ball = new Oval((int) ((this.x + 16) * scale),
				(int) ((this.y - 19) * scale), (int) (b_width * 2 * scale),
				(int) (b_width * 2 * scale), Color.black, true);
		this.window.add(r_ball);

		r_eyebrow1 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x + 34) * scale),
				(int) ((this.y) * scale), Color.black);
		this.window.add(r_eyebrow1);
		r_eyebrow2 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x + 38) * scale),
				(int) ((this.y - 10) * scale), Color.black);
		this.window.add(r_eyebrow2);
		r_eyebrow3 = new Line((int) ((this.x + 16) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x + 39) * scale),
				(int) ((this.y - 20) * scale), Color.black);
		this.window.add(r_eyebrow3);

		// draw left eye brow
		l_eyebrow1 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x - 10) * scale),
				(int) ((this.y) * scale), Color.black);
		this.window.add(l_eyebrow1);
		l_eyebrow2 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x - 13) * scale),
				(int) ((this.y - 10) * scale), Color.black);
		this.window.add(l_eyebrow2);
		l_eyebrow3 = new Line((int) ((this.x + 10) * scale),
				(int) ((this.y - 40) * scale), (int) ((this.x - 15) * scale),
				(int) ((this.y - 20) * scale), Color.black);
		this.window.add(l_eyebrow3);
	}

	// erase everything
	public void erase() {
		this.window.remove(l_ball);
		this.window.remove(r_ball);
		this.window.remove(r_eyebrow1);
		this.window.remove(r_eyebrow2);
		this.window.remove(r_eyebrow3);
		this.window.remove(l_eyebrow1);
		this.window.remove(l_eyebrow2);
		this.window.remove(l_eyebrow3);

	}

	// rotate the nose
	public void rotateNose() {
		angle += 10;// same as angle =angle+10
		if (angle > 360) {
			angle -= 360;
		}
		u_nose.rotateAround((int) ((this.x + 14) * scale),
				(int) ((this.y + 20) * scale), angle);
		d_nose.rotateAround((int) ((this.x + 14) * scale),
				(int) ((this.y + 20) * scale), angle);
		m_nose.rotateAround((int) ((this.x + 14) * scale),
				(int) ((this.y + 20) * scale), angle);

	}

}
