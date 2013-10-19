
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//WritetoFile g=new WritetoFile();
		
		
		
		ReadFile g=new ReadFile();
		g.openFile();
		//g.addRecords();
		g.ReadFile();
		g.closeFile();

	}

}
