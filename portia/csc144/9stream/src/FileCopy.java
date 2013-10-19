import java.io.*;

/**
 * A simple file copy utility
 */
public class FileCopy {

	/**
	 * Copies a source file to a destination file. The file names are given on
	 * the command line. The source file name is the first argument. The
	 * destination file name is the second argument.
	 * 
	 * @param args
	 *            the array of the command line arguments
	 */
	public static void main(String[] args) throws IOException {

		// quit if we don't have exactly two command-line arguments
		if (args.length < 2) {
			System.out.println("provide two file names");
			return;
		}

		InputStream input = null;
		OutputStream output = null;
		try {
			// Open an input stream from the input file (FileInputStream )
			input = new FileInputStream(args[0]);

			// Open an output stream to the output file (use FileWriter and
			// PrintWriter)
			output = new FileOutputStream(args[1]);

			// copy input file to output one line at a time,
			// one byte at a time -> not very efficient
			
			
			
			/*int b;
			while ((b = input.read()) != -1) {
				output.write(b);
			}*/

			
			//usee an array of bytes   -> more efficient
			byte[] b= new byte[500];
			int n;
			while ( (n=input.read(b))!=-1){
				output.write(b,0,n);
				//n    is for prevent copy all the array include empty one 
			}
			
			
			
			
			
			input.close();
			output.close();
			// close both streams
		} catch (FileNotFoundException e) {
			if (input == null) {
				System.out.println(args[0] + "   doesn't exists !");
			} else {
				System.out.println(args[1]
						+ "   doesn't  exists or couldn't be created !");
			}
		} catch (IOException e) {
			System.out
					.println(" Couldn't read/write from/to the input/output file");
		} finally {
			// close the input and output streams
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				System.out.println("Couldn't close the stream.");
			}
		}

	}
}
