import javax.swing.JFrame;

public class ServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server sally=new Server();
		sally.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sally.startRunning();
	}

}
