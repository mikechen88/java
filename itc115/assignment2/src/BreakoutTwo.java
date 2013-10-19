import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.program.GraphicsProgram;
import acm.graphics.*;
import acm.util.RandomGenerator;


public class BreakoutTwo extends GraphicsProgram
{
	private static final int APP_WIDTH = 400;
	private static final int APP_HEIGHT = 600;
	private static final int PAD_Y_OFFSET = 30;	
	private static final int PAD_WIDTH = 60;
	private static final int PAD_HEIGHT = 10;
	private static final int BRICK_Y_OFFSET = 70;	
	private static final int BRICK_ROWS = 10;
	private static final int BRICK_COLUMNS = 10;
	private static final int BRICK_SEPARATION = 4;	
	private static final int BRICK_HEIGHT = 8;
	private static final int BALL_RADIUS = 10;
	
	private static final int BRICK_TOTAL = BRICK_ROWS * BRICK_COLUMNS;		
	private static final int BRICK_WIDTH = (int) (APP_WIDTH - (BRICK_COLUMNS * BRICK_SEPARATION))/ BRICK_COLUMNS;
	private static final int BALL_START_X = (int) APP_WIDTH/2;	
	private static final int BALL_START_Y = (int) APP_HEIGHT/2;
	private static final int PADDLE_START_X = (int) APP_WIDTH/2;	
	private static final int PADDLE_START_Y = APP_HEIGHT - PAD_Y_OFFSET;
	
	private GOval gameBall;
	private GRect paddle;
	
	private static final Color brickColor[] = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private double vx = rgen.nextDouble(1, 3);//x displacement
	private double vy = rgen.nextDouble(4, 6); //y displacement
	
	private int score = 0; //make score and numTurns instance fields so they can be utilized in youWin() and youLose()
	private int numTurns = 0;
	private boolean playing = true;
	
	private GObject collider;

	
	public void init() //initialize game
	{
		this.setSize(APP_WIDTH, APP_HEIGHT);
		addKeyListeners();
		addMouseListeners();
	}
	
	public void run() 
	{
		do
		{
			runGame();
		}
		while(playAgain());		
	}
	
	public void runGame() //attempting to avoid recursive use of run(), but with waitForClick() method being void only, I can't figure out how to ever set playing = false;
	{
		setupGame();
		waitForClick();
		playGame();			
	}
	
	
	private boolean playAgain() //
	{	
		GLabel clicktoContinue = new GLabel("Click anywhere to Play Again or Escape key to exit");	
		clicktoContinue.setColor(Color.BLUE);
		clicktoContinue.setFont(new Font("Serif", Font.BOLD, 14));
		
		double a = getWidth()/2 - clicktoContinue.getWidth()/2;
		double b = 3* getHeight()/4;
		clicktoContinue.setLocation(a, b);
		add(clicktoContinue);
		waitForClick();
		removeAll();
		runGame(); //I shouldn't have to call this again, but I do...why? 
		return true;
	}
	
	public void keyPressed(KeyEvent movePaddle) //move the paddle right or left with right and left arrow keys
	{
			
			switch(movePaddle.getKeyCode())
			{
				case KeyEvent.VK_RIGHT:
					if(paddle.getX() + PAD_WIDTH <= getWidth())
					{
						paddle.move(30, 0);					 					 
					}
					 break;	
				case KeyEvent.VK_LEFT:
					if(paddle.getX() >= 0 )
					paddle.move(-30, 0);
					 break;						
					 
				case KeyEvent.VK_ESCAPE:
					 playing = false;
					 System.exit(0); //closes app window with escape key
					 break;					 		 
			}		

	}	
	
	
	public void setupGame() //draw the static game board
	{
		drawBricks( BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT, BRICK_ROWS, BRICK_COLUMNS, BRICK_SEPARATION);
		drawBall(BALL_RADIUS, BALL_START_X, BALL_START_Y);
		drawPaddle(PADDLE_START_X, PADDLE_START_Y, PAD_WIDTH, PAD_HEIGHT);
	}
	
	
	public void playGame() //add dynamic play
	{
		score=0; //re-initialize score to zero upon repeat play
		numTurns = 0; //re-initialize number of turns to zero upon repeat play
		
		int numBricks = BRICK_TOTAL; //re-initialize the number of bricks remaining upon repeat play
	
		
		if(rgen.nextBoolean()) //randomize the initial ball direction
		{
			vx = -vx;
		}
		
		while(playing) //since I an using the waitForClick() method for game replay, I am having a hard time trying to figure out how to ever set playing to false
		{			
			
			if(gameBall.getX() + BALL_RADIUS >=getWidth()) vx = -Math.abs(vx);	
			if(gameBall.getX() - BALL_RADIUS <=0) vx = Math.abs(vx);						
			if(gameBall.getY() - BALL_RADIUS <=0) vy = Math.abs(vy);
			if(gameBall.getY() + BALL_RADIUS >=getHeight()) 
			{
				removeAll();
				youLose(score, numTurns);
				playAgain(); //I'd like this to be a boolean method, but I haven't quite figured out how yet 
			}			
			
			gameBall.move(vx, vy);			

			gameBall.pause(20);
			
			collider = getCollidingObject(); 

			if (collider == null) continue;
			else if(collider == paddle) 
			{
				vy = -vy;
				numTurns++;	//increment the number of turns based upin collisions with the paddle			
				
			}
			else 
			{
				vy = -vy;
				remove(collider);
				numBricks--; //decrement the number of bricks remaining based upon removed bricks
				score++;
			}
				
			if(numTurns >= BRICK_TOTAL + 1) 
			{
				removeAll();
				youLose(score, numTurns); //if the number of turns exceed one more than the initial number of bricks, the player loses based upin too many turns
				playAgain();			
			}				
			
			if(numBricks == 0) 
			{
				removeAll();
				youWin(); //player wins if they remove all the bricks in less than the allowed numer of turns
				playAgain();			
			}	
			
		}
	
	}	
	
	
	
	public void drawBricks(int top, int width, int height, int rows, int cols, int space)
	{
		GRect bricks[][] = new GRect[rows][cols];		
		int y = top;
		for(int i=0; i<rows; i++)
		{
			int x = 0;			
			for(int j=0; j<cols; j++)
			{				
				bricks[i][j] = new GRect(x, y, width, height);				
				bricks[i][j].setFilled(true);				
				bricks[i][j].setFillColor(brickColor[i/2%brickColor.length]);
				x += width + space;
				add(bricks[i][j]);
			}			
			
			y += height + space;
		}		

	}
	
	public GOval drawBall(int radius, int x, int y)
	{
		int width = radius * 2;
		gameBall = new GOval(x - radius, y - radius, width , width );
		gameBall.setFilled(true);
		add(gameBall);
		return gameBall;
	}
	
	public GRect drawPaddle(int x, int y, int width, int height)
	{
		paddle = new GRect(x - width/2, y - height, width, height);
		paddle.setFilled(true);
		add(paddle);
		return paddle;
	}	

	
	private GObject getCollidingObject() //method to check each corner of the ball and see if it collides with any object...return that object if so, or null if not
	{
	    double x = gameBall.getX();
	    double y = gameBall.getY();
	    if (getElementAt(x, y) != null) 
	    {
	        return getElementAt(x, y);
	    }
	    if (getElementAt(x + BALL_RADIUS * 2, y) != null) 
	    {
	        return getElementAt(x + BALL_RADIUS * 2, y);
	    }
	    if (getElementAt(x, y + BALL_RADIUS * 2) != null) 
	    {
	        return getElementAt(x, y + BALL_RADIUS * 2);
	    }
	    if (getElementAt(x + BALL_RADIUS * 2, y + BALL_RADIUS * 2) != null) 
	    {
	        return getElementAt(x + BALL_RADIUS * 2, y + BALL_RADIUS * 2);
	    }
	    return null;
	}
	
	
	private void youLose(int score, int numTurns) //method to create You Lose screen
	{
		GLabel yourTurns = new GLabel("");
		
		GLabel gameOver = new GLabel("Game Over!");
		gameOver.setColor(Color.BLUE);
		gameOver.setFont(new Font("Serif", Font.BOLD, 36));
		
		double a = getWidth()/2 - gameOver.getWidth()/2;
		double b = getHeight()/4;
		gameOver.setLocation(a, b);
		add(gameOver);
		
		GLabel youLose = new GLabel("Sorry, you lose!");
		youLose.setColor(Color.RED);
		youLose.setFont(new Font("Serif", Font.BOLD, 24));
		
		double c = getWidth()/2 - youLose.getWidth()/2;
		double d = getHeight()/4 + gameOver.getHeight();
		youLose.setLocation(c, d);
		add(youLose);
		
		if (numTurns >= BRICK_TOTAL + 1) //this label is only created if player is losing due to excess turns
		{
			yourTurns = new GLabel("You took " + numTurns + " (too many) turns");
			yourTurns.setColor(Color.RED);
			yourTurns.setFont(new Font("Serif", Font.BOLD, 24));
			
			double e = getWidth()/2 - yourTurns.getWidth()/2;
			double f = getHeight()/4 + gameOver.getHeight() + youLose.getHeight();
			yourTurns.setLocation(e, f);
			add(yourTurns);			
		}
		
		GLabel yourScore = new GLabel("Your score is: " + score);
		yourScore.setColor(Color.GREEN);
		yourScore.setFont(new Font("Serif", Font.BOLD, 24));
		
		double g = getWidth()/2 - yourScore.getWidth()/2;
		double h = getHeight()/4 + gameOver.getHeight() + youLose.getHeight() + yourTurns.getHeight();
		yourScore.setLocation(g, h);
		add(yourScore);	
		
	}
	
	
	private void youWin() //method to create You Win Screen
	{
		GLabel gameOver = new GLabel("Game Over!");
		gameOver.setColor(Color.BLUE);
		gameOver.setFont(new Font("Serif", Font.BOLD, 36));
		
		double a = getWidth()/2 - gameOver.getWidth()/2;
		double b = getHeight()/4;
		gameOver.setLocation(a, b);
		add(gameOver);
		
		GLabel youWin = new GLabel("Congratulations, you win!!!");
		youWin.setColor(Color.GREEN);
		youWin.setFont(new Font("Serif", Font.BOLD, 30));
		
		double c = getWidth()/2 - youWin.getWidth()/2;
		double d = getHeight()/4 + gameOver.getHeight();
		youWin.setLocation(c, d);
		add(youWin);	

	}	
		
	
}