package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxEx extends JFrame{
	
	private JComboBox box;
	private JLabel picture;
	
	private static String[] filename={"aa.jpg","bb.jpg"};
	private Icon[]  pics={new ImageIcon(getClass().getResource(filename[0])),new ImageIcon(getClass().getResource(filename[1]))};

	public JComboBoxEx(){
		super("the title");
		setLayout( new FlowLayout());
		
		box =new JComboBox(filename);
		//create drop down box
		
		
		//anonymous listener
		box.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if (event.getStateChange()==ItemEvent.SELECTED){
							picture.setIcon(pics[box.getSelectedIndex()]);
						}
					}
				}
				
				);
		
		add(box);
		picture =new JLabel(pics[0]);
		add(picture);
	}



}
