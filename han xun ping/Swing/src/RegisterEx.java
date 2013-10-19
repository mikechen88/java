import java.awt.*;
import javax.swing.*;
public class RegisterEx extends JFrame{

	JPanel  jp1,jp2,jp3;
	JLabel jl1,jl2;
	JButton jb1,jb2;
	JCheckBox jcb1,jcb2,jcb3;
	JRadioButton  jrb1,jrb2;
	ButtonGroup bg;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegisterEx re=new RegisterEx();
	}
	public RegisterEx()
	{
		//build component
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jl1=new JLabel("your sport");
		jl2=new JLabel("your sex");
		
		jb1=new JButton ("register");
		jb2=new JButton("cancel");
		
		jcb1=new JCheckBox("football");
		jcb2=new JCheckBox("basketball");
		jcb3=new JCheckBox("baseball");
		
		jrb1=new JRadioButton("man");
		jrb2=new JRadioButton("women");
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		
		
		//set layout
		this.setLayout(new GridLayout(3,1));
		
		//add component
		jp1.add(jl1);
		jp1.add(jcb1);		
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		jp2.add(jl2);
		jp2.add(jrb1);
		jp2.add(jrb2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setSize(300,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

}
