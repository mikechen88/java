package swing;
import   java.awt.FlowLayout;
import    javax.swing.JFrame;
import   javax.swing.JLabel;
public class JFrameEx extends  JFrame{

		private JLabel item1;
		 
		public JFrameEx (){
			super("The title bar");
			//set the title
			setLayout(new FlowLayout());
			//set default layout
			
			item1=new JLabel("this is a sentence");
			item1.setToolTipText("this is gona show up on hover");
			//set  pop up    tooltip text
			
			add(item1);
			//add item to the window
			
		}
}
