import java.util.Scanner;

public class UsingExceptions {

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
					int index = Integer.parseInt(input);
					System.out.println(names[index]);
				} catch (NumberFormatException e) {
					// code to recover from the exception
					System.out.println("Enter an integer!");
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Enter a valid index value!");
				}
			}
		} while (!input.equals("q"));

	}

}
