import java.awt.Color;

import acm.graphics.*;

import acm.program.GraphicsProgram;

/**
 * draw a ball ,paddle and colorful bricks
 * 
 * @author feng
 * 
 */
public class Breakout extends GraphicsProgram {
	// define for screen
	static final int APP_WIDTH = 400;
	static final int APP_HEIGHT = 600;
	// define fot paddle
	static final int P_OFFSET = 30;
	static final int P_WIDTH = 60;
	static final int P_HEIGHT = 10;
	static final int P_X = APP_WIDTH / 2; // 200
	static final int P_Y = APP_HEIGHT - P_OFFSET; // 570
	// define for brick
	static final int B_OFFSET = 70;
	static final int B_ROW = 10;
	static final int B_COLUMNS = 10;
	static final int B_SPERATION = 4;
	static final int B_WIDTH = (APP_WIDTH - B_SPERATION * B_COLUMNS)
			/ B_COLUMNS;
	static final int B_HEIGHT = 8;
	// define for ball
	static final int START_X = APP_WIDTH / 2; // 200
	static final int START_Y = APP_HEIGHT / 2; // 300
	static final int RADIUS = 10;

	Color[] color = { Color.red, Color.orange, Color.yellow, Color.green,
			Color.blue };

	public void run() {

		setSize(APP_WIDTH, APP_HEIGHT);

		// draw brick
		DrawBrick();

		// draw ball
		DrawBall();

		// draw paddle
		DrawPaddle();
	}

	// draw brick
	public void DrawBrick() {
		int x = 0;

		for (int i = 0; i < B_ROW; i++) {
			int y = B_OFFSET;
			for (int j = 0; j < B_COLUMNS; j++) {
				GRect grect = new GRect(x, y, B_WIDTH, B_HEIGHT);
				grect.setColor(color[j / 2]);
				grect.setFilled(true);
				add(grect);
				y += B_HEIGHT + B_SPERATION;
			}
			x += B_WIDTH + B_SPERATION;
		}

	}

	// draw ball
	public void DrawBall() {
		GOval oval = new GOval(START_X - RADIUS, START_Y - RADIUS, 2 * RADIUS,
				2 * RADIUS);
		oval.setColor(Color.black);
		oval.setFilled(true);
		add(oval);
	}

	// draw paddle
	public void DrawPaddle() {
		GRect paddle = new GRect(P_X - P_WIDTH / 2, P_Y - P_HEIGHT, P_WIDTH,
				P_HEIGHT);
		paddle.setColor(Color.black);
		paddle.setFilled(true);
		add(paddle);
	}

}
