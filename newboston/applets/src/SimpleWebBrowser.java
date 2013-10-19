
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class SimpleWebBrowser extends JFrame {
	
	private JTextField addressBar;
	private JEditorPane display;
	
	//constructor
	public SimpleWebBrowser(){
		super("the title");
		
		addressBar=new JTextField("enter a url hoss!");
		addressBar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						loadCrap(event.getActionCommand());
						//getActionCommand ():    will get the text user input and pass in as parameter
					}
				}
		);
		add(addressBar, BorderLayout.NORTH);
		
		display=new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener(
				new HyperlinkListener(){
					public void hyperlinkUpdate(HyperlinkEvent event){
						if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
							//this event means onclick,    there are others are mouseover and mouseleft
							loadCrap(event.getURL().toString());
						}
					}
				}
				
		);
		add( new JScrollPane(display),BorderLayout.CENTER);
		setSize(500,300);
		setVisible(true);
		
	}
	
	private void loadCrap(String userText){
		//read html file ,and display in the screen
		try{
			display.setPage(userText);
			//setPage () is built in method.it take whatever url we pass in,and display in the display window
			addressBar.setText(userText);
		}catch(Exception e){
			System.out.println("crap");
		}
	}
}
