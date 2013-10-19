import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

/**
 * A panel to display a sudoku grid
 * 
 * @author CSC 143
 * 
 */
public class SudokuPanel extends JPanel {
	private int[][] grid;
	private String[] b;

	public SudokuPanel(String[] b, int[][] grid) {
		setBackground(Color.WHITE);
		this.grid = grid;
		this.b = b;
	}

	protected void paintComponent(Graphics g0) {
		super.paintComponent(g0);
		Graphics2D g = (Graphics2D) g0;
		g.setFont(new Font("Courier", Font.BOLD, 20));
		if (grid != null) {
			// Draw the grid
			int w = getWidth() / grid.length;
			int h = getHeight() / grid[0].length;
			for (int i = 0; i < grid.length; i++) {
				int y = i * h - h / 2;
				for (int j = 0; j < grid[i].length; j++) {
					int x = j * w + w / 2;
					g.drawRect(x - w / 2, y + h / 2, w, h);
					if (grid[i][j] != 0) {
						if (b[i].charAt(j) != '0') {
							g.setColor(Color.BLUE);
						}
						g.drawString((char) (grid[i][j] + '0') + "", x, y + h);
						g.setColor(Color.BLACK);
					}
					if (i % 3 == 0 && j % 3 == 0) {
						Stroke st = g.getStroke();
						g.setStroke(new BasicStroke(3.0f));
						g.drawRect(x - w / 2, y + h / 2, 3 * w, 3 * h);
						g.setStroke(st);
					}
				}
			}
		}
	}
}
