import acm.graphics.*;
import acm.program.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class BreakOutBeginningWithArray extends GraphicsProgram implements
		Runnable {
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
	private GRect paddle, bullet;

	private double vx;
	private double vy;

	private int no_bricks;

	private GObject collider, b_collider;

	private GRect  brick,missle;

	private int speed;

	private int scores, bounce;

	private GLabel glabel, over, go, win;

	private Vector<Bullet> bullets = new Vector<Bullet>();
	
	private Vector<GRect>  bricks=new Vector<GRect>();
	
	private Vector<Bomb>  bombs=new Vector<Bomb>();

	private Bullet bb, each_b;
	
	private Bomb b;
	
	private GImage  img1,img2,img3;

	/** Runs the Breakout program. */
	public void run() {
		BreakOutBeginningWithArray aa=new BreakOutBeginningWithArray();
		Thread tt=new Thread(aa);
		tt.start();
			setupGame();
			ready();
			playGame();
			//removeAll();
		
		/*
		 * while (true) { try { Thread.sleep(100); } catch (Exception e) {
		 * e.printStackTrace(); } repaint();
		 * 
		 * }
		 */

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
			shot();
			if (ball.getY() > HEIGHT || bounce > 500) {
				gameover();
				pause(5000);
				break;

			}

		}
		if (no_bricks < 0)
			youWin();
	}

	public void createBullet() {
		
		bb = new Bullet(paddle.getX(), paddle.getY());
		bullets.add(bb);
		// start bullet thread
		Thread t = new Thread(bb);
		t.start();

		
	
	}

	public void shot(){
		for (int i = 0; i < bullets.size(); i++) {
			 each_b = bullets.get(i);
			// when each_b is not null and bullet is live
			if (each_b != null && each_b.live) {
				missle = new GRect(each_b.x, each_b.y, 1, 1);
				missle.setColor(Color.red);
				add(missle);
				
				// ??????????????????????????????????????????????????????????????????????????????????????????
				//doesn't work for remove 
				//b_collider = getElementAt(missle.getX(), missle.getY());
				//if (b_collider==null)remove(missle);
				missle.setLocation(missle.getX(),each_b.locat);
				b_collider = getElementAt(missle.getX(), missle.getY()-1);
				
				if (b_collider==null||b_collider==ball)return;
				
				else if (isBrick(b_collider)) {
					each_b.live=false;
					bullets.remove(each_b);
					
					//create a bomb
					b=new Bomb( missle.getX(),missle.getY());
					bombs.add(b);
					explore();
					remove(b_collider);
					remove(missle);
			
					
				
					no_bricks--;
					scores += 100;
					
					remove(glabel);
					createScores();
					break;//never  break  ??????????????????????????????
				}}}
	}
	
	public void explore(){
		for (int i=0;i<bombs.size(); i++){
			Bomb b=bombs.get(i);
			
			if(b.life>6){
				img1=new GImage("bomb_1.gif",b.x,b.y);
				add(img1);
				println("  image    1");
				pause(50);
			}else if (b.life>3){
				img2=new GImage("bomb_2.gif",b.x,b.y);
				add(img2);
				println("  image   2");
				pause(50);
			}else{
				img3=new GImage("bomb_2.gif",b.x,b.y);
				add(img3);
				println("  image    3");
				pause(50);
			}
			b.lifeDown();
			
			if (b.life==0)
				bombs.remove(b);
		}
	}
	
	
	
	public void youWin() {
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
		}
		collider = colide();
		if (collider == null||collider==missle)
			return;
		else if (collider == paddle) {
			vy = -Math.abs(vy);
		} else if (isBrick(collider)) {
			vy = -vy;
			remove(collider);
			no_bricks--;
			scores += 100;
			remove(glabel);
			createScores();
		}
		
	

	}
	
	public boolean isBrick(GObject coli){
		for ( GRect brick:bricks){
			if (brick==coli)return true;			
		}
	 return false;
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
				bricks.add(brick);
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

		case KeyEvent.VK_J:

			createBullet();

			break;

		default:
			return;
		}
	}
}
