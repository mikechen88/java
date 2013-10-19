import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BreakOutBeginningWithArray extends GraphicsProgram {
	// width and height of the playing field
	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;

	// paddle coordinates
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;
	private static final int PADDLE_Y_OFFSET = 30; // offset of the paddle up
													// from the bottom

	// brick parameters
	private static final int NBRICKS_PER_ROW = 10;
	private static final int NBRICK_ROWS = 10;
	private static final int BRICK_SEP = 4; // seperation between bricks
	private static final int BRICK_WIDTH = WIDTH / NBRICKS_PER_ROW - BRICK_SEP;
	private static final int BRICK_HEIGHT = 8;
	private static final int BALL_RADIUS = 10;
	private static final int BRICK_Y_OFFSET = 70; // offset of the bricks down
													// from the top

	// initial position of the ball
	private static final int BALL_START_X = WIDTH / 2 - BALL_RADIUS;
	private static final int BALL_START_Y = HEIGHT / 2 - BALL_RADIUS;

	private static final Color[] BRICK_COLORS = { Color.RED, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.CYAN };

	private GOval ball;
	private GRect paddle;

	private double vx;
	private double vy;

	private int no_bricks;

	private GObject collider;

	private GRect brick;

	private int speed;

	private int scores, bounce;

	private GLabel glabel, over, go, win;

	/** Runs the Breakout program. */
	public void run() {
		setupGame();
		ready();
		playGame();
	}

	// setup the game
	public void setupGame() {
		setSize(WIDTH, HEIGHT);
		scores = 0;
		speed = 20;
		createBlocks();
		createBall();
		createPaddle();
		addKeyListeners();
	}

	// print ready label
	public void ready() {
		for (int time = 5; time > 0; time--) {
			go = new GLabel("Ready ! " + time, WIDTH / 10, HEIGHT / 2);
			go.setFont(new Font("Serif", Font.BOLD, 80));
			go.setColor(Color.cyan);
			add(go);
			pause(1000);
			remove(go);
		}

	}

	// show scores
	public void createScores() {
		glabel = new GLabel("your scores are :   " + scores, 100, 50);
		glabel.setFont(new Font("Serif", Font.BOLD, 18));
		glabel.setColor(Color.blue);
		add(glabel);
	}

	// play game
	public void playGame() {
		createScores();
		vx = Math.random() * 2 + 1;
		vy = 2;
		while (no_bricks > 0) {
			moveBall();
			pause(10);
		}
		if (no_bricks<0)youWin();
	}
	public void youWin(){
		win = new GLabel("Bye-bye", WIDTH / 10, HEIGHT / 2);
		win.setFont(new Font("Serif", Font.BOLD, 100));
		win.setColor(Color.red);
		add(win);
	}
	// move ball
	public void moveBall() {
		ball.move(vx, vy);
		if (ball.getX() + BALL_RADIUS * 2 >= WIDTH || ball.getX() <= 0) {

			vx = -vx;
		} else if (ball.getY() < 0) {
			vy = -vy;
		} else if (ball.getY() > HEIGHT || bounce > 500) {
			gameover();
		}

		collider = colide();
		if (collider == null)
			return;
		else if (collider == paddle) {
			vy = -Math.abs(vy);
		} else {
			vy = -vy;
			remove(collider);
			no_bricks--;
			scores += 100;
			remove(glabel);
			createScores();
		}
	}

	// print gameover label
	public void gameover() {
		over = new GLabel("Bye-bye", WIDTH / 10, HEIGHT / 2);
		over.setFont(new Font("Serif", Font.BOLD, 100));
		over.setColor(Color.red);
		add(over);
	}

	// return colide object
	public GObject colide() {
		double x = ball.getX();
		double y = ball.getY();
		if (getElementAt(x, y) != null)
			return getElementAt(x, y);
		else if (getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS) != null)
			return getElementAt(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
		else if (getElementAt(x + 2 * BALL_RADIUS, y) != null)
			return getElementAt(x + 2 * BALL_RADIUS, y);
		else if (getElementAt(x, y + 2 * BALL_RADIUS) != null)
			return getElementAt(x, y + 2 * BALL_RADIUS);
		else
			return null;

	}

	// add the set of blocks to the top of the program
	public void createBlocks() {
		for (int row = 0; row < NBRICK_ROWS; row++) // loop for number of rows
		{
			int ycoord = BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP);

			for (int col = 0; col < NBRICKS_PER_ROW; col++) // loop for columns
			{
				int xcoord = getWidth() - NBRICKS_PER_ROW / 2
						* (BRICK_WIDTH + BRICK_SEP) + col
						* (BRICK_WIDTH + BRICK_SEP);

				brick = new GRect(xcoord, ycoord, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFillColor(BRICK_COLORS[row / 2]);
				brick.setFilled(true);
				add(brick);
				no_bricks++;
			}
		}
	}

	// create the ball and place it in the middle of the screen
	public void createBall() {
		ball = new GOval(BALL_START_X, BALL_START_Y, BALL_RADIUS * 2,
				BALL_RADIUS * 2);
		ball.setFilled(true);
		add(ball);
	}

	// creates the paddle and places it near the bottom of the screen
	public void createPaddle() {
		paddle = new GRect(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT
				- PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}

	// add key enent
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		// right key move right
		case KeyEvent.VK_RIGHT:
			speed++;
			if (paddle.getX() + PADDLE_WIDTH + speed >= WIDTH) {
				paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT
						- PADDLE_Y_OFFSET);
				break;
			}
			paddle.move(Math.abs(speed), 0);
			break;

		// left key move left
		case KeyEvent.VK_LEFT:

			speed--;
			if (paddle.getX() - speed <= 0) {
				paddle.setLocation(0, HEIGHT - PADDLE_Y_OFFSET);
				break;
			}
			paddle.move(-Math.abs(speed), 0);
			break;
		default:
			return;
		}
	}
}