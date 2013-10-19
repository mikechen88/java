import java.util.Scanner;

public class UsingCheckedExceptions {

	public static void main(String[] args) {
		String[] names = { "Noel", "Azamat", "Aidana", "Ashley" };

		Scanner scan = new Scanner(System.in);
		String input;
		do {
			System.out.print("Enter an index (between 0 and "
					+ (names.length - 1) + ") or q to quit: ");
			input = scan.nextLine();
			if (!input.equals("q")) {
				try {
					int index = getIndex(input, names.length);
					System.out.println(names[index]);
				} catch (BadInputException e) {
					// code to recover from the exception
					System.out.println(e);
					System.out.println("cause = " + e.getCause());
				} catch (Exception e) {
				}
			}
		} while (!input.equals("q"));

	}

	/**
	 * Given the input from the user, returns the index value
	 * 
	 * @param s
	 *            the user's input
	 * @param length
	 *            length-1 is the maximum index value
	 * @return the index value
	 * @throws BadInputException
	 *             if the input is invalid
	 */
	public static int getIndex(String s, int length) 
			throws BadInputException {
		try {
			int index = Integer.parseInt(s);
			if (index < 0 || index >= length) {
				BadInputException e = new BadInputException(
						"Invalid index value");
				e.initCause(new ArrayIndexOutOfBoundsException());
				throw e;

			}
			return index;
		} catch (NumberFormatException e) {
			BadInputException b = new BadInputException("Invalid index value");
			b.initCause(e);
			throw b;
		}
	}

}
