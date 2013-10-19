import java.util.Random;
import java.util.Scanner;

public class Fortune {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
    private static final String[] FORTUNES={"you are cool","you are hungry","you like cake","I like football","YOu like basketball"};
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Fortune pp = new Fortune();
		pp.run();

	}

	public void run() {
		System.out.println("fortune cookie");

		do {

			System.out.println(presentFortune());

		} while (playAgain());
		System.out.println("bye");
	}

	public boolean playAgain() {
		System.out.println("do you want to play again");
		String aa = scan.nextLine().toLowerCase();
		if (aa.equals("y")||aa.equals("yes"))
			return true;
		else
			return false;
	}

	public String presentFortune() {
		return FORTUNES[rand.nextInt(FORTUNES.length)];
	}

}
