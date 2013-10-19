public class Recursion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(factorial(4));
	}

	public static int factorial(int n) {
		if (n > 1) {
			return n * factorial(n - 1);
		} else {
			return 1;
		}
	}
}
