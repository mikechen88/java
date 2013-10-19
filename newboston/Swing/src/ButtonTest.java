import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ButtonFrame   frame  =new   ButtonFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setVisible( true);
	}
}

//a frame with a button panel
class ButtonFrame  extends JFrame
{
	public ButtonFrame(){
		setTitle("button Test");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		//add panel to frame
		
		ButtonPanel  panel= new ButtonPanel();
		add(panel);
	}
	
	public static  final  int DEFAULT_WIDTH=300;
	public static  final  int DEFAULT_HEIGHT=300;
}
//A panel with three buttons
class ButtonPanel extends JPanel{
	public ButtonPanel(){
		JButton yellowButton= new JButton("YELLOW");
		JButton blueButton= new JButton("BLUE");
		JButton redButton= new JButton("RED");
		
		//add butons to panel
		add(yellowButton);
		add(blueButton);
		add(redButton);
		
		//create button actions
		
		ColorAction yellowAction= new ColorAction(Color.yellow);
		ColorAction blueAction=new ColorAction( Color.blue);
		ColorAction redAction=new ColorAction( Color.red);
		
		//associate actions with buttons
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
		
	}
	
	//an action listener inner class that sets the panel's background color
	private class ColorAction implements ActionListener{
		public ColorAction(Color c){
			backgroundColor=c;
		}
		
		public void actionPerformed (ActionEvent event){
			setBackground(backgroundColor);
		}
		
		private Color backgroundColor;
	}
}





