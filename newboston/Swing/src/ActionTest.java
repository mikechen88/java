import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActionFrame frame=new ActionFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible ( true);
	}

}
//a frame with a panel that demonstrates color change actions
class ActionFrame extends JFrame{
	public ActionFrame(){
		setTitle("ActionTest");
		setSize (DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		//add panel to frame
		
		ActionPanel panel= new ActionPanel();
		add(panel);
	}
	
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
}

//a panel with buttons and keyboard shortcuts to change the background color
class ActionPanel extends JPanel{
	public ActionPanel(){
		//define actions
		Action yellowAction= new ColorAction ("Yellow",new ImageIcon("yellow.jpg"),Color.YELLOW);
		Action blueAction= new ColorAction ("Blue",new ImageIcon("blue.jpg"),Color.BLUE);
		Action redAction= new ColorAction ("Red",new ImageIcon("red.jpg"),Color.RED);
		
		//add buttons for these actions
		add(new JButton (yellowAction));
		add(new JButton (blueAction));
		add(new JButton (redAction));
		
		//associate the y, b  ,r  keys with names
		InputMap   imap= getInputMap ( JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "red");
		
		//associate the names with actions
		ActionMap amap=getActionMap();
		amap.put("yellow", yellowAction);
		amap.put("blue", blueAction);
		amap.put( "red",redAction);
		
	}
	
	public class ColorAction extends AbstractAction{
		//construct a color action
		//name     is the name to show on the button
		//icon     is the icon to display on the button
		//c    		is the background color
		public ColorAction(String name, Icon icon ,Color c){
			putValue( Action.NAME,name);
			putValue(Action.SMALL_ICON,icon);
			putValue(Action.SHORT_DESCRIPTION,"set panel color to"+name.toLowerCase());
			putValue("color",c);
		}
		
		public void actionPerformed ( ActionEvent event){
			Color c=(Color) getValue("color");
			setBackground(c);
		}
	}
}












