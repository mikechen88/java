import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * In a graphics window, place at random location a square when a key is pressed
 * on the keyboard. Delete that square if it is clicked with the mouse. Keep
 * track of the squares with an ArrayList
 */

public class ArrayListExampleWithSwing extends JPanel implements KeyListener,
		MouseListener {

	// Keep the list of squares in an ArrayList
	private ArrayList<Rectangle> squareList;
	// The list of the colors of the squares
	private ArrayList<Color> colorList;
	// The window our panel belongs to
	private JFrame window;
	// length of the side of the squares
	private int side = 20;
	// to create random coordinates
	private Random rand = new Random();

	/**
	 * Initialize the graphics window and the list of squares
	 */
	public ArrayListExampleWithSwing() {
		// Create the graphics window
		window = new JFrame("Array list example with swing");
		window.setSize(500, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Our panel goes in this window
		window.add(this);
		// Background color of the panel
		this.setBackground(Color.WHITE);
		// Show the window
		window.setVisible(true);
		// Send all of the events on the window to this
		// ArrayListExampleWithSwing
		window.addKeyListener(this);
		this.addMouseListener(this);
		// Create the square list (initially empty)
		squareList = new ArrayList<Rectangle>();
		// and the list of colors
		colorList = new ArrayList<Color>();
	}

	/**
	 * A key has been pressed. Create a square at a random location.
	 */
	public void keyPressed(KeyEvent e) {
		// upper left corner of the square
		int x = rand.nextInt(getWidth() - side);
		int y = rand.nextInt(getHeight() - side);
		// Color of the square (random)
		Color c = new Color(rand.nextInt(), true);
		// Create the square
		squareList.add(new Rectangle(x, y, side, side));
		colorList.add(c);
		// Show it
		repaint();
	}

	/**
	 * Mouse click on the window. If it is on a square, erase that square
	 */
	public void mousePressed(MouseEvent e) {
		// If a right click, display the number of squares
		if (e.isMetaDown()) {
			JOptionPane.showMessageDialog(this, "number of squares = "
					+ squareList.size(), "Statistics",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// Iterate over the squares
		// (from the last one to the first one)
		// Use a ListIterator (not easier, just for the sake
		// of the example)
		ListIterator<Rectangle> itr = squareList
				.listIterator(squareList.size());
		ListIterator<Color> itc = colorList.listIterator(colorList.size());
		while (itr.hasPrevious()) {
			Rectangle r = itr.previous();
			Color c = itc.previous();
			if (r.contains(e.getPoint())) {
				itr.remove();
				itc.remove();
				repaint();
				break;
			}
		}

	}

	/**
	 * paint the window
	 */
	protected void paintComponent(Graphics g) {
		// Needed to paint in the right order
		super.paintComponent(g);

		// Display the squares
		Graphics2D g2D = (Graphics2D) g;
		for (int i = 0; i < squareList.size(); i++) {
			Rectangle r = squareList.get(i);
			Color c = colorList.get(i);
			g2D.setColor(c);
			g2D.fill(r);
		}
	}

	// Other methods needed to implement the interfaces
	// from MouseListener
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	// from KeyListener
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new ArrayListExampleWithSwing();
	}
}
