import java.util.Scanner;

public class TwosComplement {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("Enter an integer ( or q to quit):");
			String line = scan.nextLine().trim().toLowerCase();
			if (line.startsWith("q")) {
				break;
			}
			try {
				int n = Integer.parseInt(line);
				System.out.println(n + "  in binary = " + twosComplement(n));
				//System.out.println(line + "  in binary = " + twosComplement(n));
			} catch (NumberFormatException e) {
				System.out.println("invalid   input");
			}
		} while (true);
	}

	/**
	 * return the 2's complement binary representation of the given number
	 * 
	 * @param n
	 * @return
	 */
	public static String twosComplement(int n) {
		StringBuffer s = new StringBuffer();
		boolean neg = (n < 0);
		if (neg) {
			n = -n;
		}
		//change to binary
		while (n > 0) {
			int d = n % 2;
			n /= 2;
			s.insert(0, d);

		}
		// only one zero is required
		// use a loop to test the removal of the 1's or 0's below
		// for ( int )
		s.insert(0, 0); // positive number

		if (neg) {// 2's complement

			// first do : 1's complement 0 <-> 1
			for (int i = 0; i < s.length(); i++) {
				int d = 1 - (s.charAt(i) - '0');
				s.setCharAt(i, (char) (d + '0'));
			}
			// 2's complement : add 1
			int carry = 1;
			for (int i = s.length() - 1; i >= 0; i--) {
				int d = s.charAt(i) - '0';
				d=d+carry;
				carry = d / 2;
				d %= 2;
				s.setCharAt(i, (char) (d + '0'));
			}

			// remove all of the leading 1's except last one
			while (s.length() > 1 && s.charAt(1) == '1') {
				s.deleteCharAt(0);
			}

		}
		if (!neg) {// though not needed here

			// remove all of the leading 0's except last one
			while (s.length() > 1 && s.charAt(1) == '0') {
				s.deleteCharAt(0);
			}

		}
		return s.toString();
	}

	/**
	 * return s%2 where s is the string representation of a decimal number
	 * 
	 * @param s
	 * @return
	 */
	public static int mod2(String s) {
		return (s.charAt(s.length() - 1) - '0') % 2;

	}

	/**
	 * returns s/2 where where s is the string representation of a decimal
	 * number
	 */

	public static String divideBy2(String s) {
		String r = "";
		int carry = 0;
		for (int i = 0; i <= s.length() - 1; i--) {
			int d = (s.charAt(i) - '0')+10*carry;
			r+=(d/2);
			carry=d%2;
		}
		//remove   the leading  0's;
		return r;
	}

}
