import java.awt.*;
import javax.swing.*;
public class Qqlogin extends JFrame{
	//north
	JLabel jl1;
	
	//south
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	//middle
	JTabbedPane jtp;//ѡ��ո�
	JPanel jp2,jp3,jp4;
	
	JLabel jl2,jl3,jl4,jl5;
	//���������ı���
	JTextField jtf;
	//�����
	JPasswordField jpf;
	//��������button
	JButton  jb4;
	//�����¼���ͼ�ס����
	JCheckBox jcb1,jcb2;
	
	public Qqlogin()
	{
		//build component    center ��ζ�ž�������
		jl2=new JLabel("  qq number",JLabel.CENTER);
		jl3=new JLabel("  qq password",JLabel.CENTER);	
		
		jl4=new JLabel("  forget password",JLabel.CENTER);
		jl4.setFont(new Font("Consolas",Font.PLAIN,16));//set font
		jl4.setForeground(Color.BLUE);//set color
		
		jl5=new JLabel("<html><a href='www.qq.com'> apply protect password</a></html>");
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//����ƶ�����ʱ�����Ϊ����
		
		//���� ����
		jtf=new JTextField();
		//��������
		jpf=new JPasswordField();
		//��ͼƬ����button ���м�
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
		
		
		//�в�����
		jtp=new JTabbedPane();
		jp2=new JPanel();
		jp3=new JPanel();
		jp3.setBackground(Color.RED);
		//��������ñ���ɫ
		
		jp4=new JPanel();
		jp4.setBackground(new Color(0,0,255));
		
		//�������ӵ�ѡ�������
		jtp.add("qq number",jp2);
		//��һ������ѡ����ƣ��ڶ������� ��æ���ҵ�panel
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
		
		//չ�����
		ImageIcon icon=new ImageIcon("face.jpg");
		this.setIconImage(icon.getImage());//����������ͼ��ķ���
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
