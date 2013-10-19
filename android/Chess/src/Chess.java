import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Chess extends JFrame implements MouseListener, Runnable {

	// get screen size;
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// background image
	BufferedImage bgImage = null;

	// keep for chess cordination
	int x = 0;
	int y = 0;
	// save in two diamention array
	int[][] allChess = new int[19][19];

	// diff black and white
	boolean isBlack = true;

	// check game over?
	boolean canPlay = true;

	// save information
	String message = "black  first";

	// save maxium time ( second)
	int maxTime = 0;

	// make a Thread class
	Thread t = new Thread(this);

	// save black and white left time
	int blackTime = 0;
	int whiteTime = 0;

	// save time message
	String blackMessage = "no limit";
	String whiteMessage = "no limit";

	public Chess() {
		// set title
		this.setTitle("five  Chess");
		// set window
		this.setSize(500, 500);
		// set location
		this.setLocation((width - 500) / 2, (height - 500) / 2);
		this.setResizable(false);

		this.addMouseListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		t.start();
		t.suspend();
		
		//invoke this.repaint()     to prevent black screen at the begin
		repaint();
		try {
			bgImage = ImageIO.read(new File("c:/image/bb.jpg"));
			// bgImage =ImageIO.read( new File( "bb.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		// super.paint(g);

		// double cache prevent screen flash
		// draw the picture in the memory first
		// then draw the picture on the screen
		BufferedImage bi = new BufferedImage(500, 500,
				BufferedImage.TYPE_INT_ARGB);

		// create a pen for draw on the picture that is in the memory
		Graphics g2 = bi.createGraphics();

		// background
		g2.drawImage(bgImage, 1, 20, this);
		// title
		g2.setFont(new Font("Serial", Font.BOLD, 20));
		g2.drawString("game information   " + message, 120, 60);
		// time info
		g2.drawString("Black time:  " + blackMessage, 40, 470);
		g2.drawString("White time:  " + whiteMessage, 270, 470);

		// draw chess board
		for (int i = 0; i < 19; i++) {
			g2.drawLine(10, 70 + 20 * i, 370, 70 + 20 * i);
			g2.drawLine(10 + 20 * i, 70, 10 + 20 * i, 430);
		}

		// draw points
		g2.fillOval(68, 128, 4, 4);
		g2.fillOval(308, 128, 4, 4);
		g2.fillOval(308, 368, 4, 4);
		g2.fillOval(68, 368, 4, 4);

		g2.fillOval(308, 248, 4, 4);
		g2.fillOval(188, 128, 4, 4);
		g2.fillOval(68, 248, 4, 4);
		g2.fillOval(188, 368, 4, 4);
		g2.fillOval(188, 248, 4, 4);

		// draw chess
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (allChess[i][j] == 1) {
					// black
					int tempx = i * 20 + 10;
					int tempy = j * 20 + 70;
					g2.fillOval(tempx - 7, tempy - 7, 14, 14);
				}
				if (allChess[i][j] == 2) {
					// white
					int tempx = i * 20 + 10;
					int tempy = j * 20 + 70;
					g2.setColor(Color.WHITE);
					g2.fillOval(tempx - 7, tempy - 7, 14, 14);
					g2.setColor(Color.BLACK);
					g2.drawOval(tempx - 7, tempy - 7, 14, 14);
				}
			}
		}

		g2.drawString("new    game", 400, 70);
		g2.drawString("setting game", 400, 120);
		g2.drawString(" game description", 400, 170);
		g2.drawString(" give   up the game", 400, 270);
		g2.drawString("about  the game", 400, 320);
		g2.drawString("quite   game", 400, 370);

		g.drawImage(bi, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (canPlay) {
			x = e.getX();
			y = e.getY();

			if (x >= 10 && x <= 370 && y >= 70 && y <= 430) {
				x = (x - 10) / 20;
				y = (y - 70) / 20;
				if (allChess[x][y] == 0) {
					if (isBlack == true) {
						allChess[x][y] = 1;
						isBlack = false;
						message = " time for white";
					} else {
						allChess[x][y] = 2;
						isBlack = true;
						message = " time for black";
					}
					// check this chess have been the fifth
					boolean winFlag = this.checkWin();
					if (winFlag == true) {
						JOptionPane.showMessageDialog(this, "game over"
								+ (allChess[x][y] == 1 ? "black" : "white")
								+ "   win.");
						canPlay = false;
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"already have, pick a new place");
				}

				repaint();
			}
		}

		// start a new game
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 70
				&& e.getY() <= 100) {
			int result = JOptionPane.showConfirmDialog(this,
					"Are you sure that you want to play the game?");
			if (result == 0) {
				// restart the game
				// 1. clean the chess board----allChess [][] set to 0
				for (int i = 0; i < 19; i++) {
					for (int j = 0; j < 19; j++) {
						allChess[i][j] = 0;
					}
				}
				// second way :
				// allChess=new int [19] [19]

				// 2. game info change back to original state.
				message = "black first";

				// 3.change the next man to white ( because black first this
				// time)
				isBlack = true;
				blackTime = maxTime;
				whiteTime = maxTime;

				if (maxTime > 0) {
					blackMessage = maxTime / 3600 + " : "
							+ (maxTime / 60 - maxTime / 3600 * 60) + ":"
							+ (maxTime - maxTime / 60 * 60);
					whiteMessage = maxTime / 3600 + " : "
							+ (maxTime / 60 - maxTime / 3600 * 60) + ":"
							+ (maxTime - maxTime / 60 * 60);
					t.resume();
				} else {
					blackMessage = "no limit";
					whiteMessage = "no limit";
				}
				repaint();
			}
		}

		// setting a new game
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 120
				&& e.getY() <= 150) {
			String input = JOptionPane
					.showInputDialog("please game max time (minutes and 0 is unlimit) :");
			try {
				maxTime = Integer.parseInt(input) * 60;
				if (maxTime < 0) {
					JOptionPane.showMessageDialog(this,
							"Please input number that is greater than 0");
				} else if (maxTime == 0) {
					int result = JOptionPane.showConfirmDialog(this,
							"finish setting, do you want to play now");
					if (result == 0) {
						for (int i = 0; i < 19; i++) {
							for (int j = 0; j < 19; j++) {
								allChess[i][j] = 0;
							}
						}
						// second way :
						// allChess=new int [19] [19]

						// 2. game info change back to original state.
						message = "black first";

						// 3.change the next man to white ( because black first
						// this time)
						isBlack = true;
						blackTime = maxTime;
						whiteTime = maxTime;
						blackMessage=" no limit";
						whiteMessage=" no limit ";

						repaint();

					}
				} else if (maxTime > 0) {
					int result = JOptionPane.showConfirmDialog(this,
							"finish setting, do you want to play now");
					if (result == 0) {
						for (int i = 0; i < 19; i++) {
							for (int j = 0; j < 19; j++) {
								allChess[i][j] = 0;
							}
						}

						// 2. game info change back to original state.
						message = "black first";

						// 3.change the next man to white ( because black first
						// this time)
						isBlack = true;

						blackTime = maxTime;
						whiteTime = maxTime;
						blackMessage = maxTime / 3600 + " : "
								+ (maxTime / 60 - maxTime / 3600 * 60) + ":"
								+ (maxTime - maxTime / 60 * 60);
						whiteMessage = maxTime / 3600 + " : "
								+ (maxTime / 60 - maxTime / 3600 * 60) + ":"
								+ (maxTime - maxTime / 60 * 60);
						t.resume();
						repaint();
					}
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "please input number");
			}
		}

		//
		// description the game
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 170
				&& e.getY() <= 200) {
			JOptionPane.showMessageDialog(this,
					"chess game.both sides take turn to play ");
		}

		// give up
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 270
				&& e.getY() <= 300) {
			int result = JOptionPane.showConfirmDialog(this,
					"Are you sure that you want to give up");
			if (result == 0) {
				if (isBlack) {

					JOptionPane.showMessageDialog(this,
							" black has give up the game");
				} else {
					JOptionPane.showMessageDialog(this,
							" white has give up the game");
				}
				canPlay = false;
			}
		}

		// about
		// start a new game
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 320
				&& e.getY() <= 350) {
			JOptionPane
					.showMessageDialog(this,
							"the game is design by Feng Chen ,you can connect my email : feng ");
		}

		// quite game
		// start a new game
		if (e.getX() >= 400 && e.getX() <= 470 && e.getY() >= 370
				&& e.getY() <= 400) {
			JOptionPane.showMessageDialog(this, "quite the  game");
			System.exit(0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	private boolean checkWin() {
		boolean flag = false;
		int color = allChess[x][y];
		/*
		 * // save total how many same color chess int count = 1; // check
		 * horizontal has 5 same chess ( all y is same ====== // allChess[x][y]
		 * --------y is all same ) int color = allChess[x][y];
		 * 
		 * // loop to check chess int i = 1; while (color == allChess[x + i][y])
		 * { count++; i++; } i = 1; while (color == allChess[x - i][y]) {
		 * count++; i++; } if (count >= 5) { flag = true; }
		 * 
		 * // check vertical direction int i2 = 1; int count2 = 1; while (color
		 * == allChess[x][y + i2]) { count2++; i2++; } i2 = 1; while (color ==
		 * allChess[x][y - i2]) { count2++; i2++; } if (count2 >= 5) { flag =
		 * true; }
		 * 
		 * // xie direction ( right up -------- left down ) int i3 = 1; int
		 * count3 = 1; while (color == allChess[x - i3][y + i3]) { count3++;
		 * i3++; } i3 = 1; while (color == allChess[x + i3][y - i3]) { count3++;
		 * i3++; } if (count3 >= 5) { flag = true; }
		 * 
		 * // xie direction ( right down -------- left up ) int i4 = 1; int
		 * count4 = 1; while (color == allChess[x + i4][y + i4]) { count4++;
		 * i4++; } i4 = 1; while (color == allChess[x - i4][y - i4]) { count4++;
		 * i4++; } if (count4 >= 5) { flag = true; }
		 */

		// horizontal
		int count = checkCount(1, 0, color);
		if (count >= 5) {
			flag = true;
		} else {
			// vertical
			count = checkCount(0, 1, color);
			if (count >= 5) {
				flag = true;
			} else {
				// right up --------left down
				count = checkCount(1, -1, color);
				if (count >= 5) {
					flag = true;
				} else {
					// left up-------right down
					count = checkCount(1, 1, color);
					if (count >= 5) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	// check chess connect number
	private int checkCount(int xChange, int yChange, int color) {
		int count = 1;
		int tempX = xChange;
		int tempY = yChange;

		while (x+xChange>=0&&x+xChange<=18&&y+yChange>=0&&y+yChange<=18&&color == allChess[x + xChange][y + yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}

			}

		}

		xChange = tempX;
		yChange = tempY;
		while (x-xChange>=0&&x-xChange<=18&&y-yChange>=0&&y-yChange<=18&&color == allChess[x - xChange][y - yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}

			}

		}

		return count;
	}

	public void run() {
		// check is time limit
		if (maxTime > 0) {
			while (true) {
				if (isBlack) {
					blackTime--;
					if ( blackTime==0){
						JOptionPane.showMessageDialog(this, "black time out ,loos");
					}
				} else {
					whiteTime--;
					if ( whiteTime==0){
						JOptionPane.showMessageDialog(this, "white time out ,loos");
					}
				}
				blackMessage = blackTime / 3600 + " : "
						+ (blackTime / 60 - blackTime / 3600 * 60) + ":"
						+ (blackTime - blackTime / 60 * 60);
				whiteMessage = whiteTime / 3600 + " : "
						+ (whiteTime / 60 - whiteTime / 3600 * 60) + ":"
						+ (whiteTime - whiteTime / 60 * 60);
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}

	}

}
