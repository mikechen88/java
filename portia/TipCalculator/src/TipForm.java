import javax.swing.*;

public class TipForm extends JFrame {
	
	public TipForm(){
		JFrame frame=new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel =new TipPanel();
		this.add(panel);
	}
	

}
