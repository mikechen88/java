

import java.awt.*;
import javax.swing.*;
public class LoginEx extends JFrame{

	JPanel jp1,jp2,jp3;
	JLabel jlb1,jlb2;
	JButton jb1,jb2;
	JTextField  jtf1;
	JPasswordField jpf1;
	
	public static void main(String[] args) {
		LoginEx lo=new LoginEx();

	}
	//constructor
	public LoginEx()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		
		jlb1=new JLabel("name");
		jlb2=new JLabel("password");
		
		jb1=new JButton("login");
		jb2=new JButton("cancle");
		
		jtf1=new JTextField(10);
		jpf1=new JPasswordField(10);
		//10  表示密码框 的宽度是多大
		
		//set layout
		this.setLayout(new GridLayout(3,1));
		
		//加入各个组件
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jpf1);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		//把各个panel   加入到JFrame
		this .add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setSize(300,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}
}
