import java.util.Scanner;

public class BigInt implements Comparable {

	/**
	 * @param args
	 */
	private String operator = null;
	private String command;

	long value;

	BigInt bigInt_value, second_value;

	// initiate string
	String s_value;
	int n1;
	String binary_value;

	// init node
	InnerNode head = new InnerNode(null);
	InnerNode tail = new InnerNode(null);

	InnerNode head2 = new InnerNode(null);
	InnerNode tail2 = new InnerNode(null);

	boolean negative = false;
	boolean neg;

	boolean isNeg;

	public void addNode(InnerNode node) {
		if (null == head) {
			// tail.prev = node;
			head = node;
			tail = node;
			head.next = tail;
			tail.prev = head;
		} else {
			tail.next = node;
			node.prev = tail;

		}
		tail = node;
	}

	public BigInt(BigInt val) {
		InnerNode p = val.head;
		head = new InnerNode(p.value);
		InnerNode q = head;
		while (p.next != null) {
			p = p.next;
			q.next = new InnerNode(p.value);
			q.next.prev = q;
			q = q.next;
		}
		tail = q;
	}

	public BigInt(long val) {

		this(String.valueOf(val));
	}

	public String toString() {
		InnerNode node = head;
		StringBuffer s = new StringBuffer();
		while (null != head.next) {
			System.out.print(node.next);
			node = node.next;
			s.insert(0, node.value);
		}

		return s.toString();
	}

	public BigInt(String val) {
		s_value = val;
		binary_value = twosComplement(s_value);

		String[] string = binary_value.split("");

		for (int i = 0; i < string.length; i++) {
			InnerNode node = new InnerNode(string[i]);

			addNode(node);

		}

		System.out.println("this  to string : " + this.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("negToPos = " + negToPos("11011"));

		Character[] opers = new Character[] { '+', '-', '*', '!' };
		Scanner console = new Scanner(System.in);
		String first;
		String second;
		do {
			System.out.print("Please enter your numbers:");
			String line = console.nextLine().trim();
			char[] inputs = line.toCharArray();
			if (line.startsWith("q")) {
				break;
			}
			try {

				for (int i = 0; i < inputs.length; i++) {
					for (int j = 0; j < opers.length; j++)
						if (i - 1 >= 0 && inputs[i] == opers[j]
								&& Character.isDigit(inputs[i - 1])) {
							first = line.substring(0, i).trim();

							BigInt bigInt = new BigInt(first);
							char oper = opers[j];
							if (oper == '!' && line.endsWith("!")) {
								if (first.startsWith("-")) {
									System.out.println("invalid input");
									return;
								}
								System.out.println("factorial   ");
								bigInt.factorial();
							}
							second = line.substring(i + 1);

							BigInt bigInt2 = new BigInt(second);
							switch (oper) {

							case '+':
								/*
								 * System.out.println("+++++++++++"); if
								 * (first.startsWith("-") &&
								 * !second.startsWith("-") &&
								 * bigInt.compareTo(bigInt2) <= -1) { String
								 * first11 = line.substring(1, i); BigInt
								 * first111 = new BigInt(first11);
								 * bigInt2.subtract(first111); System.out
								 * .println("change to    substract"); break;
								 * 
								 * } else if (!first.startsWith("-") &&
								 * second.startsWith("-") &&
								 * bigInt.compareTo(bigInt2) < 0) {
								 * 
								 * bigInt2.add(bigInt); break; } else if
								 * (!first.startsWith("-") &&
								 * second.startsWith("-") &&
								 * bigInt.compareTo(bigInt2) > 0) {
								 * 
								 * String second8 = line.substring(i + 2);
								 * BigInt second81 = new BigInt(second8);
								 * bigInt.subtract(second81); break; } else if
								 * (first.startsWith("-") &&
								 * second.startsWith("-") &&
								 * bigInt.compareTo(bigInt2) <= -1) {
								 * System.out.println("-3+ -8 "); String first12
								 * = line.substring(1, i); BigInt first121 = new
								 * BigInt(first12); bigInt2.subtract(first121);
								 * break; }
								 */
								bigInt.add(bigInt2);
								break;
							case '-':
								System.out.println("------------");

								if (first.startsWith("-")
										&& second.startsWith("-")
										&& bigInt.compareTo(bigInt2) < 0) {
									System.out.println("944");
									String third = line.substring(i + 2);
									System.out.println("  " + third);
									BigInt bigInt3 = new BigInt(third);
									String third3 = line.substring(1, i);
									BigInt third33 = new BigInt(third3);

									bigInt3.subtract(third33);
									break;
								}
								if (!first.startsWith("-")
										&& !second.startsWith("-")
										&& bigInt.compareTo(bigInt2) < 0) {
									System.out.println("911");
									String four = line.substring(i);
									BigInt bigInt4 = new BigInt(four);
									System.out.println(bigInt2.n1);
									bigInt4.add(bigInt);
									break;
								}
								if (second.startsWith("-")
										&& first.startsWith("-")
										&& bigInt.compareTo(bigInt2) > 0) {
									System.out.println("922");
									String fourth = line.substring(i + 2);
									System.out.println("    " + fourth);
									BigInt bigInt3 = new BigInt(fourth);
									System.out.println("  " + first);

									bigInt.add(bigInt3);
									break;
								}
								if (second.startsWith("-")
										&& first.startsWith("-")
										&& bigInt.compareTo(bigInt2) < 0) {
									System.out.println("933");
									String fifth = line.substring(i + 2);
									BigInt bigInt3 = new BigInt(fifth);
									String first2 = first.substring(1);
									BigInt bigInt5 = new BigInt(first2);
									bigInt.subtract(bigInt5);
									break;
								}
								if (first.startsWith("-")
										&& !second.startsWith("-")
										&& bigInt.compareTo(bigInt2) < 0) {
									System.out.println("955");

									String second7 = line.substring(i);
									BigInt second72 = new BigInt(second7);
									String seventh3 = line.substring(1, i);
									BigInt seven4 = new BigInt(seventh3);
									second72.subtract(seven4);
									break;

								}
								System.out.println("966");

								bigInt.subtract(bigInt2);
								break;

							case '*':
								bigInt.multiply(bigInt2);
								break;

							}

						}

				}

				// BigInt operand1 = new BigInt(line);

			} catch (NumberFormatException e) {
				System.out.println("invalid input");
			}
		} while (true);

	}

	private BigInt add(BigInt val) {

		BigInt result;
		second_value = val;
		StringBuffer s = new StringBuffer();
		String a = this.binary_value;

		String b = val.binary_value;
		if (b.charAt(0) == '1') {
			b = '1' + b;
		}
		System.out.println("a    " + a);

		System.out.println("b    " + b);
		int diff = Math.abs(a.length() - b.length());
		if (a.length() >= b.length()) {
			char first = b.charAt(0);
			for (int i = 0; i <= diff; ++i) {
				b = first + b;
			}
			a = a.charAt(0) + a;
		} else {
			char first = a.charAt(0);
			for (int i = 0; i <= diff; ++i) {
				a = first + a;
			}
			b = a.charAt(0) + b;
		}

		System.out.println("aa    " + a);

		System.out.println("bb    " + b);
		int carry = 0;
		for (int i = a.length() - 1; i >= 0; i--) {
			if ((a.charAt(i) == '1' && b.charAt(i) == '1')
					|| (a.charAt(i) == '0' && b.charAt(i) == '0')) {
				int d = '0' + carry;
				s.insert(0, (char) d);
				if (a.charAt(i) == '1' && b.charAt(i) == '1') {
					carry = 1;
				} else {
					carry = 0;
				}

			} else {
				int d = 1 + carry;
				carry = d / 2;
				d = d % 2;
				s.insert(0, (char) (d + '0'));
			}
			if (i == 0 && carry > 0) {
				s.insert(0, (char) (carry + '0'));
			}

			System.out.println("i  " + i + "  " + s.toString());
		}
		while (s.length() > 1 && s.charAt(0) == '0' && s.charAt(1) == '0') {
			s.deleteCharAt(0);
		}
		while (s.length() > 1 && s.charAt(0) == '1' && s.charAt(1) == '1') {
			s.deleteCharAt(0);
		}

		String ss = s.toString();
		System.out.println("boolean is     " + negative);
		System.out.println("before    " + ss);
		isNeg = (ss.charAt(0) == '1');
		if (isNeg) {
			System.out.println("come  to neg to posit int");
			ss = negToPos(ss);
			System.out.println("after    " + ss);
		}

		System.out.println("ss after negToPos    " + ss);

		showNumber(ss, isNeg);

		BigInt aa = new BigInt(s.toString());
		return aa;
	}

	public void showNumber(String s, boolean neg) {
		System.out.println(" come   in");

		System.out.println("sting is : " + s);

		head = null;
		tail = null;

		for (int i = 0; i < s.length(); i++) {
			System.out.println("sting iss : " + s.substring(i, i + 1));
			InnerNode node = new InnerNode(s.substring(i, i + 1));
			addNode(node);
		}
		String ss = "0";
		InnerNode p = head;

		while (p != null) {

			ss = times2(ss);

			if (p.value.equals("1")) {
				ss = plus1(ss);
			}
			p = p.next;
		}

		System.out
				.println("ending********************************************    "
						+ ((neg) ? "-" : "+") + ss);

	}

	public void showSubNumber(String s, boolean neg) {
		System.out.println(" come   in   substract  ");
		if (s.equals("0")) {
			System.out.println("0");
		}
		int total = 0;
		for (int i = s.length() - 1; i >= 1; i--) {
			if (s.substring(i, i + 1).equals("0"))
				continue;
			total += Math.pow(2,
					Integer.parseInt(s.substring(i, i + 1))
							* (s.length() - 1 - i));
			System.out.println(" sum = " + total);
		}

		if (this.negative || neg) {
			if (s.startsWith("1")) {
				total = -(int) Math.pow(2, s.length() - 1) + total;
			} else {
				total = -(int) Math.pow(2, s.length()) + total;
			}

		} else {
			total += (int) Math.pow(2,
					Integer.parseInt(s.substring(0, 1)) * s.length() - 1);
		}

		System.out.println("last sum = " + total);

	}

	private static String negToPos(String str) {
		System.out.println("before  neg to positive  str   " + str);
		StringBuffer s = new StringBuffer(str);

		int carry = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			int d = s.charAt(i) - '0';
			d = d - carry;
			if (d >= 0) {
				carry = 0;
			} else {
				carry = 1;
			}
			s.setCharAt(i, (char) (Math.abs(d) + '0'));
		}

		while (s.length() > 1 && s.charAt(0) == '0') {
			s.deleteCharAt(0);
		}
		System.out.println("s    before =" + s);
		for (int j = 0; j < s.length(); j++) {
			int f = 1 - (s.charAt(j) - '0');
			s.setCharAt(j, (char) (f + '0'));
		}

		while (s.length() > 1 && s.charAt(0) == '0') {
			s.deleteCharAt(0);
		}
		System.out.println("s    after negative to positive  =" + s.toString());
		return s.toString();
	}

	private BigInt multiply(BigInt val) {
		System.out.println("**********************");
		BigInt result;
		second_value = val;
		String a = this.binary_value;
		String b = val.binary_value;
		if (b.charAt(0) == '1') {
			b = '1' + b;
		}

		if ((a.charAt(0) == '1' && b.charAt(0) != '1')
				|| (a.charAt(0) == '0' && b.charAt(0) == '1')) {
			isNeg = true;
		}
		if (a.charAt(0) == '1') {
			a = "1" + a;
		}
		System.out.println(a + "   " + b);

		// begin to multiply and store every line in the array
		String[] s = new String[b.length()];

		int k = 0;
		for (int i = b.length() - 1; i >= 0; i--) {
			StringBuffer s1 = new StringBuffer();
			int l = b.length() - 1 - i;
			if (l > 0) {
				for (int m = 0; m < l; m++) {
					s1.insert(0, (char) ('0'));
				}
			}
			for (int j = a.length() - 1; j >= 0; j--) {

				if (a.charAt(j) == '1' && b.charAt(i) == '1') {
					s1.insert(0, (char) ('1'));

				} else {
					s1.insert(0, (char) ('0'));
				}
				System.out
						.println("aaaaaa  i= " + i + "   " + s1 + " j = " + j);
			}

			s[k] = s1.toString();
			System.out.println("aaaaaa   s[k]  " + k + "   " + s[k]);
			k++;

		}
		String sum = sumString(s);
		/*
		 * if (isNeg) { System.out.println("come  to neg to posit int"); sum =
		 * "1" + negToPos(sum); System.out.println("after    " + sum); }
		 */

		showMulNumber(sum, val.negative);

		BigInt aa = new BigInt(sum);
		return aa;
	}

	public void showMulNumber(String s, boolean neg) {
		head = null;
		tail = null;
		System.out.println("*************" + s);

		for (int i = 0; i < s.length(); i++) {
			System.out.println("sting iss : " + s.substring(i, i + 1));
			InnerNode node = new InnerNode(s.substring(i, i + 1));
			addNode(node);
		}
		String ss = "0";
		InnerNode p = head;

		while (p != null) {

			ss = times2(ss);

			if (p.value.equals("1")) {
				ss = plus1(ss);
			}
			p = p.next;
		}

		System.out
				.println("ending********************************************    "
						+ ((isNeg) ? "-" : "+") + ss);

	}

	public String times2(String s) {
		int carry = 0;
		int n = 0;
		StringBuilder ss = new StringBuilder(s);
		for (int i = s.length() - 1; i >= 0; i--) {
			n = s.charAt(i) - '0';
			n = 2 * n + carry;
			carry = n / 10;
			n = n % 10;
			ss.setCharAt(i, (char) (n + '0'));
			if (i == 0 && carry > 0) {
				ss.insert(0, (char) (carry + '0'));
			}
		}

		return ss.toString();
	}

	public String plus1(String s) {
		StringBuilder ss = new StringBuilder(s);
		int carry = 0;
		int n = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			n = s.charAt(i) - '0';
			if (i == s.length() - 1) {
				n = n + 1 + carry;
			} else {
				n = n + carry;
			}
			carry = n / 10;
			n = n % 10;
			ss.setCharAt(i, (char) (n + '0'));
			if (i == 0 && carry > 0) {
				ss.insert(0, (char) (carry + '0'));
			}
		}
		return ss.toString();
	}

	public String sumString(String[] arr) {
		boolean tooLong = false;
		// get longest array ,and make all element equal line
		int len = arr[arr.length - 1].length();
		for (int l = 0; l < arr.length - 1; l++) {
			int diff = len - arr[l].length();
			for (int m = 0; m < diff; m++) {
				arr[l] = '0' + arr[l];
			}
		}
		System.out.println("len   	" + len);
		for (String c : arr) {
			System.out.println(c);
		}
		StringBuffer s2 = new StringBuffer();
		for (int n = 0; n < len; n++) {
			s2.append("0");
		}
		int carry = 0;
		for (int i = len - 1; i > 0; i--) {

			int total = 0;
			for (int j = 0; j < arr.length; j++) {
				total += arr[j].charAt(i) - '0';

			}
			total += carry;
			carry = total / 2;
			if (carry == 0) {
				carry = 0;
			}
			total %= 2;
			s2.setCharAt(i, (char) (total + '0'));

			System.out.println("  s2   " + i + "    " + s2.toString());

		}
		if (carry > 0) {
			tooLong = true;
		}

		System.out.println(" ss    before  " + s2.toString());
		if (isNeg) {
			if (tooLong) {
				s2.setCharAt(1, (char) ('1'));
				System.out.println("too long ");
			} else {
				s2.setCharAt(0, (char) ('1'));

			}
		}
		String aa = s2.toString();
		System.out
				.println("  s2  ******************************************** "
						+ aa);
		return aa;

	}

	private BigInt subtract(BigInt val) {

		BigInt result;
		second_value = val;
		StringBuffer s = new StringBuffer();
		String a = this.binary_value;
		String b = val.binary_value;

		System.out.println("a    " + a);

		System.out.println("b    " + b);
		int diff = Math.abs(a.length() - b.length());
		if (a.length() > b.length()) {
			for (int i = 0; i < diff; ++i) {
				b = '0' + b;
			}
		} else if (a.length() < b.length()) {
			for (int i = 0; i < diff; ++i) {
				a = '0' + a;
			}
		}
		System.out.println("aa    " + a);

		System.out.println("bb    " + b);
		int carry = 0;
		for (int i = a.length() - 1; i >= 0; i--) {
			if ((a.charAt(i) == '1' && b.charAt(i) == '1')
					|| (a.charAt(i) == '0' && b.charAt(i) == '0')) {
				int d = 0 + carry;
				if (d < 0) {
					carry = -1;
					d = 1;
				} else {
					carry = 0;
				}

				s.insert(0, (char) (d + '0'));

			} else if (a.charAt(i) == '1' && b.charAt(i) == '0') {
				int d = 1 + carry;
				carry = 0;
				s.insert(0, (char) (d + '0'));
			} else if (a.charAt(i) == '0' && b.charAt(i) == '1') {
				int d = 1 + carry;
				carry = -1;
				s.insert(0, (char) (d + '0'));
			}
			if (i == 0 && carry < 0) {
				s.insert(0, (char) (carry + '0'));
			}

			System.out.println("i  " + i + "  " + s.toString());
		}

		Integer c = Integer.parseInt(s.toString());

		System.out.println("ssss    " + s);
		showSubNumber(s.toString(), val.negative);

		BigInt aa = new BigInt(s.toString());
		System.out.println();
		return aa;
	}

	private BigInt factorial() {
		// recursive method
		System.out.println("binary   factorial");
		System.out.println("binary   " + this.binary_value);
		// BigInt aa = new BigInt(this.binary_value);
		int j = 1;
		BigInt temp = new BigInt("1");
		for (int i = 1; i <= this.n1; i++) {

			String bb = i + "";
			System.out.println("   bb = " + bb);
			BigInt aa = new BigInt(bb);
			System.out.println("   bb = " + aa.n1);
			temp.multiply(aa);
			temp = aa;
			j = j * aa.n1;

		}
		System.out.println("binary   factorial   = " + j);
		// / System.out.println("binary   factorial   = " + aa.n1);
		return null;
	}

	public boolean equals(Object object) {
		return true;
	}

	public String toString2s() {
		return "";
	}

	@Override
	public int compareTo(Object o) {

		int a = Math.abs(this.n1);
		int b = Math.abs(((BigInt) o).n1);
		System.out.println("    compare    value   before:  " + a + "   " + b);
		int c = 0;
		if (a > b)
			c = 1;
		else if (a == b)
			c = 0;
		else if (a < b)
			c = -1;

		System.out.println("    compare    value:  " + c);
		return c;
	}

	/**
	 * Returns the 2's complement binary representation of the given integer
	 */
	public String twosComplement(String n) {
		StringBuffer s = new StringBuffer("");
		neg = (n.charAt(0) == '-');
		if (neg) {
			negative = true;
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
			removeLeadingChars(s, '1');
		}

		if (!neg) { // though not needed here
			// remove all of the leading 0's except the last one
			removeLeadingChars(s, '0');
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
		r = removeLeadingChars(new StringBuffer(r), '0').toString();
		return r;
	}

	/**
	 * Removes all of the leading characters equal to c in s except the last one
	 */
	public static StringBuffer removeLeadingChars(StringBuffer s, char c) {
		if (s == null || s.length() == 0 || s.charAt(0) != c) {
			return s;
		} else {
			while (s.length() > 1 && s.charAt(1) == c) {
				s.deleteCharAt(0);
			}
			return s;
		}
	}

	private class InnerNode {
		InnerNode next;
		InnerNode prev;
		String value;

		public InnerNode(String val) {
			value = val;
		}

		public String toString() {
			return value;
		}
	}

	public void addReverseNode(InnerNode node) {
		if (null == head2) {
			// tail.prev = node;
			head2 = node;
			tail2 = node;
			head2.next = tail2;
			tail2.prev = head;
		} else {
			tail2.next = node;
			node.prev = tail2;

		}
		tail2 = node;
	}

}
