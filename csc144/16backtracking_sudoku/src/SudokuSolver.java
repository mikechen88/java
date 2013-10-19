import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

/**
 * A Soduku solver
 * 
 * @author CSC 143
 */
public class SudokuSolver {

	public static void main(String[] args) {
		// Idea: for each blank in the grid, list all of the possibilities. Then
		// select
		// the blank with the least number of possibilities and fill it with the
		// first
		// possible number. Then repeat the above steps.
		// If the algorithm ends on a deadend, backtrack. If it doesn't a
		// solution has
		// been found.

		// Enter the sudoku strings here
		String[] b = { "706400000", "058000000", "100700500", "000100890",
				"030090060", "089004000", "004002003", "000000780", "000006109" };
		b = new String[] { "007008300", "004000907", "090010520", "060009002",
				"000601000", "500300070", "075020030", "408000700", "006800200" }; // Seattle
		// times
		// 3/3/2013
		int[][] grid = new int[9][9];
		int i = 0;
		for (String st : b) {
			int j = 0;
			for (char c : st.toCharArray()) {
				grid[i][j] = c - '0';
				j++;
			}
			i++;
		}
		sudoku(grid);
		if (!checkGrid(grid)) {
			System.out.println("Sudoku couldn't be solved!");
			return;
		}

		// Show the solution
		graphicalDisplay(b, grid);
		display(grid);
		System.out.println();
	}

	/**
	 * Solves a soduku grid
	 * 
	 * @param grid
	 *            the grid to solve
	 * @return true if a solution has been found
	 */
	public static boolean sudoku(int[][] grid) {
		// Get the sorted list of squares
		List<SudokuSquare> list = getList(grid);
		// Base case: the grid is filled (no squares in the list)
		if (list.isEmpty()) {
			return true;
		}
		// start with the first square
		SudokuSquare sq = list.get(0);
		// try all of the possible solutions for that square
		while (!sq.digits.isEmpty()) {
			// place a solution on the grid at i,j
			grid[sq.i][sq.j] = sq.digits.get(0);
			sq.digits.remove(0);
			// move on to the other squares
			boolean success = sudoku(grid);
			// if it didn't work backtrack
			if (!success) {
				// Backtrack
				grid[sq.i][sq.j] = 0;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays the sudoku grid in the console window
	 * 
	 * @param grid
	 *            the soduku grid
	 */
	public static void display(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.printf("%d ", grid[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Displays the sudoku grid in a graphics window
	 * 
	 * @param b
	 *            the initial grid
	 * 
	 * @param grid
	 *            the soduku grid
	 */
	public static void graphicalDisplay(String[] b, int[][] grid) {
		JFrame f = new JFrame("Sudoku");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		f.setVisible(true);

		display(grid);
		f.add(new SudokuPanel(b, grid));
		f.validate();
		System.out.println();
	}

	/**
	 * Returns the sorted list of the empty squares in the sudoku grid
	 * 
	 * @param grid
	 *            the sudoku grid
	 * @return the list of the empty squares with their possible solutions
	 */
	public static List<SudokuSquare> getList(int[][] grid) {
		List<SudokuSquare> list = new ArrayList<SudokuSquare>();

		// Find the empty squares and their possible solutions
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {// empty square
					// Get its possible solutions by elimination
					ArrayList<Integer> digits = fillDigits(); // 1,2,3,...,9
					// eliminate
					// row i:
					for (int col = 0; col < grid[i].length; col++) {
						digits.remove(new Integer(grid[i][col]));

					}
					// column j:
					for (int row = 0; row < grid.length; row++) {
						digits.remove(new Integer(grid[row][j]));

					}
					// 3x3 square that contains (i,j)
					int i0 = i / 3 * 3;
					int j0 = j / 3 * 3;
					for (int row = i0; row <= i0 + 2; row++) {
						for (int col = j0; col <= j0 + 2; col++) {
							digits.remove(new Integer(grid[row][col]));
						}
					}
					// new empty square
					list.add(new SudokuSquare(i, j, digits));
				}
			}
		}
		// sort is the method in collection
		Collections.sort(list);
		return list;
	}

	/**
	 * Returns true if the given grid is a valid sudoku grid.
	 * 
	 * @param grid
	 *            the sudoku grid to check
	 * @return true if the grid is valid
	 */
	public static boolean checkGrid(int[][] grid) {
		// rows
		for (int i = 0; i < grid.length; i++) {
			ArrayList<Integer> digits = fillDigits();
			for (int j = 0; j < grid[i].length; j++) {
				digits.remove(new Integer(grid[i][j]));
			}
			if (digits.size() > 0) {
				return false;
			}
		}
		// columns
		for (int j = 0; j < grid[0].length; j++) {
			ArrayList<Integer> digits = fillDigits();
			for (int i = 0; i < grid.length; i++) {
				digits.remove(new Integer(grid[i][j]));
			}
			if (digits.size() > 0) {
				return false;
			}
		}
		// squares
		for (int i = 0; i < grid.length; i += 3) {
			for (int j = 0; j < grid[i].length; j += 3) {
				ArrayList<Integer> digits = fillDigits();
				for (int ki = i; ki <= i + 2; ki++) {
					for (int kj = j; kj <= j + 2; kj++) {
						digits.remove(new Integer(grid[ki][kj]));
					}
				}
				if (digits.size() > 0) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * A helper method that returns the list of the integers 1 through 9
	 */
	public static ArrayList<Integer> fillDigits() {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		int[] dgts = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int value : dgts) {
			digits.add(value);
		}
		return digits;
	}

}
