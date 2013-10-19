import java.awt.*;
import javax.swing.*;
public class Qqlogin extends JFrame{
	//north
	JLabel jl1;
	
	//south
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	//middle
	JTabbedPane jtp;//选项卡空格
	JPanel jp2,jp3,jp4;
	
	JLabel jl2,jl3,jl4,jl5;
	//号码输入文本框
	JTextField jtf;
	//密码框
	JPasswordField jpf;
	//清除号码的button
	JButton  jb4;
	//隐身登录，和记住密码
	JCheckBox jcb1,jcb2;
	
	public Qqlogin()
	{
		//build component    center 意味着居中排列
		jl2=new JLabel("  qq number",JLabel.CENTER);
		jl3=new JLabel("  qq password",JLabel.CENTER);	
		
		jl4=new JLabel("  forget password",JLabel.CENTER);
		jl4.setFont(new Font("Consolas",Font.PLAIN,16));//set font
		jl4.setForeground(Color.BLUE);//set color
		
		jl5=new JLabel("<html><a href='www.qq.com'> apply protect password</a></html>");
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//鼠标移动到此时，会变为手型
		
		//输入 号码
		jtf=new JTextField();
		//输入密码
		jpf=new JPasswordField();
		//把图片放入button 正中间
		jb4=new JButton(new ImageIcon("face.jpg"));
		
		//checkbox
		jcb1= new JCheckBox("  hide login ");
		jcb2=new JCheckBox("remember password");
		
		
		//north area
		jl1=new JLabel(new ImageIcon("face.jpg"));
		
		//south area
		jp1=new JPanel();
		jb1=new JButton(new ImageIcon("face.jpg"));
		jb2=new JButton(new ImageIcon("face.jpg"));
		jb3=new JButton(new ImageIcon("face.jpg"));
		
		
		//中部区域
		jtp=new JTabbedPane();
		jp2=new JPanel();
		jp3=new JPanel();
		jp3.setBackground(Color.RED);
		//给面板设置背景色
		
		jp4=new JPanel();
		jp4.setBackground(new Color(0,0,255));
		
		//将面板添加到选项卡窗格上
		jtp.add("qq number",jp2);
		//第一个代表选项卡名称，第二个代表 手忙脚乱的panel
		jtp.add("celephone " ,jp3);
		jtp.add("email",jp4);
		
		
		
		//set layout
		jp2.setLayout(new GridLayout(3,3));
		
		
		//add component
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		jp2.add(jl2);
		jp2.add(jtf);
		jp2.add(jb4);
		jp2.add(jl3);
		jp2.add(jpf);
		jp2.add(jl4);
		jp2.add(jcb1);
		jp2.add(jcb2);
		jp2.add(jl5);
		
		
		this .add(jp1,BorderLayout.SOUTH);
		this .add(jl1,BorderLayout.NORTH);
		this .add(jtp,BorderLayout.CENTER);
		
		//展现组件
		ImageIcon icon=new ImageIcon("face.jpg");
		this.setIconImage(icon.getImage());//给窗体设置图标的方法
		//this.setSize(350,240);
		this.setTitle("Tenxun QQ");
		this.setSize(450,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Qqlogin jta=new Qqlogin();
	}
}
