import java.lang.reflect.Array;
import java.util.Scanner;

public class Grade {
	Scanner scan = new Scanner(System.in);

	double[] input;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grade grade = new Grade();
		grade.run();

	}

	public void run() {
		System.out.println("Grading start");
		input();
		while (true) {
			System.out.print("enter your command");
			String command = scan.nextLine().toLowerCase();
			if (command.equals("a"))
				System.out.println(getAverage());
			else if (command.equals("m"))
				System.out.println(getMax());
			else if (command.equals("l"))
				System.out.println(getMin());
			else if (command.equals("q"))
				break;
			else
				System.out.println("invalid command");
		}
		System.out.println("bye bye");

	}

	public double getMin() {
		double min = input[0];
		for (int i = 0; i < input.length; i++) {
			if (input[i] < min)
				min = input[i];

		}
		return min;
	}

	public double getMax() {
		double max = input[0];
		for (int i = 0; i < input.length; i++) {
			if (input[i] > max)
				max = input[i];

		}
		return max;
	}

	public double getAverage() {
		double ave = 0;
		for (int i = 0; i < input.length; i++) {
			ave += input[i];
		}
		return ave / input.length;
	}

	public void input() {

		input = new double[getInt("Num student")];
		for (int i = 0; i < input.length; i++) {
			getDouble(i);
		}

	}

	public int getInt(String question) {
		System.out.print(question + ": ");
		int answer = scan.nextInt();
		scan.nextLine();
		return answer;

	}

	public void getDouble(int i) {
		System.out.print("input student " + (i + 1) + ":  ");
		input[i] = scan.nextDouble();
		scan.nextLine();

	}
}
