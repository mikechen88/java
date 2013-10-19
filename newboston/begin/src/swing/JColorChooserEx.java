package swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JColorChooserEx extends JFrame{
	
	private JButton b;
	private Color color=(Color.white);
	private JPanel panel;
	
	
	public JColorChooserEx(){
		super ("the title");
		panel=new JPanel();
		panel.setBackground(color);
		
		b=new JButton("choose a color");
		b.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						color=JColorChooser.showDialog(null, "pick your color", color);
		//null is for the position
		//second parameter is when user click the button, it's for the pop up window's title
		//last parameter is the initial color when window pop up.
						
						
						if (color==null)  //when user didn't choose a color
							color=(Color.white);
						
						panel.setBackground(color);
					}
				}
				
				
				);
		add(panel,BorderLayout.CENTER);
		add(b,BorderLayout.SOUTH);
		
	}
	
}
