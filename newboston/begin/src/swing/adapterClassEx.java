package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class adapterClassEx extends JFrame {
	private String details;
	private JLabel statusbar;
	
	
	
	public adapterClassEx(){
		super("tutle");
		
		statusbar=new JLabel("this is default");
		
		add(statusbar,BorderLayout.SOUTH);
		
		addMouseListener(new Mouseclass());
	}
	
	private class Mouseclass extends MouseAdapter{
		//mouseApter is built in java
		public void mouseClicked(MouseEvent event){
			details=String.format("youclicked %d ", event.getClickCount());
			
			if(event.isMetaDown())
				//isMetaDown  is to distinguish different type mouse button
				details+="with right mouse button";
				else if (event.isAltDown())
					details+="either center mouse button";
				else 
					details+="with left mouse button";
				
				
				statusbar.setText(details);
				
			
		}
	}
}
