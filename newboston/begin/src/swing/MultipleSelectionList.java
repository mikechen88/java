package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MultipleSelectionList extends JFrame{
	private JList leftlist,rightlist;
	
	private JButton movebutton;
	
	private static String[] food={"bacon","wings","ham","beef","more"};
	
	public MultipleSelectionList(){
		super("title");
		setLayout (new FlowLayout());
		
		leftlist=new JList(food);
		
		leftlist.setVisibleRowCount(3);
		
		leftlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(new JScrollPane(leftlist));
		
		movebutton=new JButton("move---->");
		
		movebutton.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				  rightlist.setListData(leftlist.getSelectedValues());
				  
			  }
		});
		add(movebutton);
		
		rightlist=new JList();
		
		rightlist.setVisibleRowCount(3);
		rightlist.setFixedCellWidth(100);
		rightlist.setFixedCellHeight(15);
		rightlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(new JScrollPane(rightlist));
		
		
		
		
	}
	
	
	
	
}
