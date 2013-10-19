import java.awt.*;
import javax.swing.*;
public class JTextAreaEx extends JFrame {

	JTextArea jta=null;    //多行文本
	JScrollPane jsp=null;    // 滚动条
	JPanel jpl=null;
	JComboBox jcb=null;    // 复选框
	JTextField jtf=null;    // 单行文本
	JButton jb=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JTextAreaEx jta=new JTextAreaEx();
	}
	public JTextAreaEx()
	{
		jta=new JTextArea();
		//不要设定大小，因为它是放在中间的，它会自动调节大小
		jsp=new JScrollPane(jta);   //多行文本中加入滚动条
		
		jpl=new JPanel();  // 默认是流式布局		
		String []chartter={"bush","ladon"};
		jcb=new JComboBox(chartter);
		jtf=new JTextField(10);
		jb=new JButton("send");
		
		
		//设置布局
		jpl.add(jcb);
		jpl.add(jtf);
		jpl.add(jb);
		
		//加入JFrame
		this.add(jsp);
		this.add(jpl, BorderLayout.SOUTH);
		this.setSize(300,200);
		this.setIconImage(new ImageIcon("Desert.jpg" ).getImage());    //标题图标
		this.setLocation(200, 200);
		
		this.setTitle("Tenxun QQ");
		this.setResizable(false);    // 不支持最大化
		//退出窗口退出jvm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 退出时候关闭JVM
		
		//显示
		this.setVisible(true);
	}
}
