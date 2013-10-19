public class PracticeItWriteSquares {
	public static void main(String[] args) {
		writeSquares(5);
		System.out.println();
		writeSquares(6);
		System.out.println();
		writeSquares(1);
		System.out.println();
	}

	public static void writeSquares(int n) {
		if (n < 1) {
			throw new IllegalArgumentException();
		} else if (n == 1) {
			System.out.print(1);
		} else if (n % 2 == 1) {
			System.out.print(n * n + ", ");
			writeSquares(n - 1);
		} else {
			writeSquares(n - 1);
			System.out.print(", " + n * n);
		}
	}

}