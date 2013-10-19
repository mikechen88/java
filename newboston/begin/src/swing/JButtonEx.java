package swing;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class JButtonEx extends JFrame {
	private JButton reg;
	private JButton custom;
	
	public JButtonEx (){
		super("The title");
		setLayout (new FlowLayout());
		
		reg=new JButton("button text");
		add(reg);
		
		Icon b=new  ImageIcon(getClass().getResource("aa.jpg"));
		Icon x=new  ImageIcon(getClass().getResource("bb.jpg"));
		
		custom=new JButton("Custom", b);
		custom.setRolloverIcon(x);
		add(custom);
		
		
		
		HandlerClass   handler=new  HandlerClass();
		reg.addActionListener(handler);
		custom.addActionListener(handler);
		
		
		
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed( ActionEvent event){
			JOptionPane.showMessageDialog(null, String.format("%s",event.getActionCommand()));
			
		}
	}
}
