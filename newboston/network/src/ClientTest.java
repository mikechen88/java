import javax.swing.JFrame;
public class ClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client  charlie;
		charlie=new Client("127.0.0.1");
		
		charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		charlie.startRunning();
	}

}
