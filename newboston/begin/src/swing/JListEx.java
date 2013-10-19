package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class JListEx extends JFrame{
	private JList list;
	private static String [] colornames={"black","blue","red","white"};
	//this is for human to understand
	private static Color[] colors={Color.BLACK,Color.BLUE,Color.RED,Color.WHITE};
	//this is for java to understand
	
	public JListEx(){
		super("the title");
		setLayout(new FlowLayout());
		
		list=new JList(colornames);
		
		list.setVisibleRowCount(4);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//options for single and multiple choice
		
		add(new JScrollPane(list));
		
		list.addListSelectionListener(
				new ListSelectionListener(){
					//this is special for list
					public void valueChanged(ListSelectionEvent event){
						getContentPane().setBackground(colors[list.getSelectedIndex()]);
						//because content is in front of background,
						//in order to visit background we need to go through the content
						
						
					}
				}
				
				
				
				);
	}
	
	
}
