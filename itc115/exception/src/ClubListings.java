import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ClubListings {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClubListings clist=new ClubListings();
		clist.run();
	}
	
	public void run(){
		File clubFile=new File("input/clubs.txt");
		Scanner fileIn=null;
		try {
			 fileIn =new Scanner(clubFile);
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
		System.out.println("I love ");
		
		while( fileIn.hasNextLine()){
			String line=fileIn.nextLine();
			System.out.println(line);
		}
		fileIn.close();
	}

}
