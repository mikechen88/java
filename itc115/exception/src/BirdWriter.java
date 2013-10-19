import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BirdWriter {

	private static final String OUTPUT_FILE = "output/birds.txt";

	Scanner in = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BirdWriter bird = new BirdWriter();
		bird.run();
	}

	public void run() {
		while (true) {
			System.out.println("input    (e)  (P)  (q) ");
			String command =getString("input    (e)  (P)  (q) ").toLowerCase();
			if (command.equals("e")) {
				enterBird();
			} else if (command.equals("p")) {
				 printBirds();
			} else {
				System.out.println("good bye");
				System.exit(-1);
			}
		}
	}
	
	public String getString( String question){
		System.out.print(question+": ");
		return in.nextLine();
	}

	public PrintWriter getWriter() {

		try {
			// second parameter as true will open file for appending

			FileOutputStream file = new FileOutputStream(OUTPUT_FILE, true);

			return new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		return null;

	}
	
	public void  enterBird(){
		String birdName=getString("Bird Name");
		PrintWriter writer=getWriter();
		writer.println(birdName);
		writer.close();
	}
	public void printBirds(){
		Scanner reader=getReader();
		while( reader.hasNextLine()){
			String bird=reader.nextLine().trim();
			System.out.println(bird);
		}
		reader.close();
	}

	public Scanner getReader() {
		File readfile = new File(OUTPUT_FILE);
		
		try {
			return new Scanner(readfile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
}
