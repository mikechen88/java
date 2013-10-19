import java.util.Scanner;

public class Two_compliment_string_version {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Enter an integer (or q to quit): ");
			String line = scan.nextLine().trim().toLowerCase();
			if (line.startsWith("q")) {
				break;
			}
			try {
				System.out.println(line + " in binary = "
						+ twosComplement(line));
			} catch (RuntimeException e) {
				System.out.println("Invalid input");
			}
		} while (true);
	}

	/**
	 * Returns the 2's complement binary representation of the given integer
	 */
	public static String twosComplement(String n) {
		StringBuffer s = new StringBuffer("");
		boolean neg = (n.charAt(0) == '-');
		if (neg) {
			n = n.substring(1);
		}
		while (!n.equals("0")) {
			int d = mod2(n);
			n = divideBy2(n);
			s.insert(0, d);
		}
		// only one zero is required
		// use a loop to test the removal of the 1's or 0's below
		for (int i = 1; i <= 10; i++) {
			s.insert(0, 0); // positive number
		}

		if (neg) {// 2's complement
			// 1's complement: 0 -> 1, 1 -> 0
			for (int i = 0; i < s.length(); i++) {
				int d = 1 - (s.charAt(i) - '0');
				s.setCharAt(i, (char) (d + '0'));
			}
			// 2's complement: add 1
			int carry = 1;
			for (int i = s.length() - 1; i >= 0; i--) {
				int d = (s.charAt(i) - '0') + carry;
				carry = d / 2;
				d %= 2;
				s.setCharAt(i, (char) (d + '0'));
			}
			// remove all of the leading 1's except the last one
			while (s.length() > 1 && s.charAt(1) == '1') {
				s.deleteCharAt(0);
			}
		}

		if (!neg) { // though not needed here
			// remove all of the leading 0's except the last one
			while (s.length() > 1 && s.charAt(1) == '0') {
				s.deleteCharAt(0);
			}
		}
		return s.toString();
	}

	/**
	 * Return s % 2 where s is the string representation of a decimal number
	 */
	public static int mod2(String s) {
		return (s.charAt(s.length() - 1) - '0') % 2;
	}

	/**
	 * Returns s/2 where s is the string representation of a decimal number
	 */
	public static String divideBy2(String s) {
		String r = "";
		int carry = 0;
		for (int i = 0; i < s.length(); i++) {
			int d = (s.charAt(i) - '0') + 10 * carry;
			r += (d / 2);
			carry = d % 2;
		}
		// remove the leading 0's
		return r;
	}
}
