import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(new File("src/Example.java"));
			File output = new File("elpmaxe.java");

			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			reverse(scan, writer);
			scan.close();
			writer.flush();
			writer.close();

		} catch (FileNotFoundException e) {
			System.out.println("could not find the file");
		} catch (IOException e) {
			System.out.println("could not write stream to the output file");
		}
	}

	public static void reverse(Scanner scan, BufferedWriter writer)
			throws IOException {
		// base case: no more lines in the input file
		if (scan.hasNextLine()) {
			String line = scan.nextLine();
			reverse(scan, writer);

			writer.write(line);
			writer.newLine();
		}
	}

	// write a static method that takes a Scanner to a text file
	// and that writes the lines of the file to another file in reverse order
	// if the input file contains:

	// monday, tuesday, wednesday

	// the output file will contain: wednesday, tuesday, monday

}
