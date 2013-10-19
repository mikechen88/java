import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadWrite {
	private static final String OUTPUT_FILE = "animal_talk.txt";

	private static final String INPUT_FILE = "animal.txt";
	

	
	Scanner in = new Scanner(System.in);
	String user;
	String cat="cat";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadWrite rr = new ReadWrite();
		rr.run();
	}

	public void run() {
		user=getString("input your animal");
		writeToFile();
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
	
	public void writeToFile(){
		Scanner reader=getReader();
		while( reader.hasNext()){
			String aa=reader.nextLine();
			aa=aa.replace(cat,user);
			
			
			/*char[] stringArray = aa.toCharArray();
			stringArray[0] = Character.toUpperCase(stringArray[0]);
			aa = new String(stringArray);*/
			
			aa=Character.toUpperCase(aa.charAt(0))+aa.substring(1);
			
			aa=aa+".";
			PrintWriter writer=write();
			writer.println(aa);
			writer.close();
		}
	}
	
	public PrintWriter write(){
		try{
			FileOutputStream file = new FileOutputStream(INPUT_FILE, true);
			return new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		return null;
	}

		public String replace(String aa){
			if (aa.contains(user)){
				aa.replace(cat,user);
				
			}
	
			return aa;
		}
		
		public String getString(String question) {
			System.out.print(question + ": ");
			return in.nextLine().toLowerCase().trim();
		}
}
