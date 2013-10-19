import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Guessing {
	Random random = new Random();

	private int ranNum;
	private final static int CHANCE = 5;	//the total chance that user have in one game.
	private int guessNum;   //the number of user guess.

	private int totalGuess;	//the total number of guessing time in the game
	private int totalGame;	//the total number of the game
	
	private int bestScore ;//get the best number
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Guessing guessing = new Guessing();
		guessing.run();
	}

	public void run() {
		do {
			totalGame++;
			System.out.println("I'm thinking of a number between 1 and 5...");
			ranNum = random.nextInt(5) + 1;
			guess();
		} while (playAgain());
		showGrade();
	}

	//ask user continue or no
	public boolean playAgain() {
	
		System.out.print("Play again?");

		String command = scanner.nextLine().toLowerCase();
		if (command.equals("y") || command.equals("yes")) {
			return true;
		}
		return false;

	}

	public void guess() {
		int tempt = 0;	//tempory var for each round
		while (tempt <= CHANCE) {
			tempt++;
			totalGuess++;
			
			//if waste too much money ,go home
			if (tempt > CHANCE) {
				System.out.println("Sorry, you lost this round");
				return;
			}
			
			//get user input number
			int guessNum = input();
			
			//compare it with random number
			if (guessNum == ranNum) {
				System.out.println("Good job,You guessed it in " + tempt
						+ " guesses!");

				//if it's first time assign value for best scores, use tempt number.
				if (bestScore == 0)
					bestScore = tempt;
				else//compare with the old value
					bestScore = bestScore > tempt ? tempt : bestScore;

				return;

			} else if (guessNum < ranNum)
				System.out.println("It's lower");
			else {
				System.out.println("It's higher");
			}
		}
	}

	//input user guess number 
	//and return 
	public int input() {
		System.out.print("Your guess?");
		int num=scanner.nextInt();
		scanner.nextLine();
		return num;
	}

	//print your score
	public void showGrade() {
		System.out.println("Your overall Results:");
		System.out.println("Total games: " + totalGame);
		DecimalFormat d=new DecimalFormat("0.00");
		
		double percent=(double)(totalGuess) / totalGame;
		System.out.println("Guesses/game:  " + d.format(percent));
		System.out.println("Best Score :" + bestScore);
	}
}
