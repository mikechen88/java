import java.awt.*;
import javax.swing.*;
public class JTextAreaEx extends JFrame {

	JTextArea jta=null;    //�����ı�
	JScrollPane jsp=null;    // ������
	JPanel jpl=null;
	JComboBox jcb=null;    // ��ѡ��
	JTextField jtf=null;    // �����ı�
	JButton jb=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JTextAreaEx jta=new JTextAreaEx();
	}
	public JTextAreaEx()
	{
		jta=new JTextArea();
		//��Ҫ�趨��С����Ϊ���Ƿ����м�ģ������Զ����ڴ�С
		jsp=new JScrollPane(jta);   //�����ı��м��������
		
		jpl=new JPanel();  // Ĭ������ʽ����		
		String []chartter={"bush","ladon"};
		jcb=new JComboBox(chartter);
		jtf=new JTextField(10);
		jb=new JButton("send");
		
		
		//���ò���
		jpl.add(jcb);
		jpl.add(jtf);
		jpl.add(jb);
		
		//����JFrame
		this.add(jsp);
		this.add(jpl, BorderLayout.SOUTH);
		this.setSize(300,200);
		this.setIconImage(new ImageIcon("Desert.jpg" ).getImage());    //����ͼ��
		this.setLocation(200, 200);
		
		this.setTitle("Tenxun QQ");
		this.setResizable(false);    // ��֧�����
		//�˳������˳�jvm
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // �˳�ʱ��ر�JVM
		
		//��ʾ
		this.setVisible(true);
	}
}
