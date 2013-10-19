/**
 * The solution of the towers of Hanoi puzzle using recursion
 * 
 * @author CSC 143
 * 
 */
public class TowersOfHanoi {
	public static void main(String[] args) {
		moveDisks(4, 1, 2, 3); // left = 1, middle = 2, right = 3
	}

	/**
	 * Moves ndisks from the left peg to the right peg, 
	 * using the middle peg as a temporary holding peg.
	 * 
	 * to move n disks from the left peg to the right peg
	 * 
	 * -> move n-1 disks from the left peg to the middle peg
	 * 
	 * -> move 1 disk from the left peg to the right peg
	 * 
	 * -> move n-1 disks from the middle peg to the right peg
	 */
	public static void moveDisks(int disks, int left, 
			int middle, int right) {
		if (disks == 1) { // base case
			System.out.println("Move one disk from " + left + 
					" to " + right);
		} else {
			moveDisks(disks - 1, left, right, middle);
			moveDisks(1, left, middle, right);
			moveDisks(disks - 1, middle, left, right);
		}
	}
}





