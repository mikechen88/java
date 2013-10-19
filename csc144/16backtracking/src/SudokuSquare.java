/**
 * A class to represent a square on a Sudoku board.
 * @author CSC 143
 */

import java.util.List;

public class SudokuSquare implements Comparable<SudokuSquare> {
	public int i, j; // indices
	public int n; // number of possible digits
	// list of possible digits in this square
	public List<Integer> digits;

	public SudokuSquare(int i, int j, List<Integer> digits) {
		this.i = i;
		this.j = j;
		this.digits = digits;
		n = digits.size();
	}

	/**
	 * a square is less than another square if it has less possible solutions.
	 */
	public int compareTo(SudokuSquare s) {
		return n - s.n;
	}

	public String toString() {
		return "(i,j) = (" + i + "," + j + ")" + " digits = " + digits + "\n";
	}
}
