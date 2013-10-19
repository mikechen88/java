import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class Notepad extends JFrame{

	JMenuBar jmb;  //�˵������
	JMenu menu1,menu2,menu3,menu4,menu5;
	JMenuItem item2,item3,item4,item5,item6,item7;
	JMenu xinjian; //�����˵�
	JMenuItem file,project;
	
	JTextArea jta;
	
	//������
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	
	public Notepad()
	{
		//create toolbar
		jtb=new JToolBar();
		jb1=new JButton(new ImageIcon("face.jpg"));
		jb1.setToolTipText("create new");//set reminder information
		jb2=new JButton(new ImageIcon("face.jpg"));
		jb2.setToolTipText("open");//set reminder information
		jb3=new JButton(new ImageIcon("face.jpg"));
		jb4=new JButton(new ImageIcon("face.jpg"));
		jb5=new JButton(new ImageIcon("face.jpg"));
		jb6=new JButton(new ImageIcon("face.jpg"));
		
		
		jmb=new JMenuBar();
		
		menu1=new JMenu("File(F)");
		menu1.setMnemonic('F');
		//�������ʷ�   alt+f   �����˵�
		
		menu2=new JMenu("Edit(E)");
		menu2.setMnemonic('E');//�������ʷ�
		
		menu3=new JMenu("Format(O)");
		menu3.setMnemonic('O');//�������ʷ�
		
		menu4=new JMenu("Search(S)");
		menu4.setMnemonic('S');//�������ʷ�
		
		menu5=new JMenu("Help(H)");
		menu5.setMnemonic('H');//�������ʷ�
		
		xinjian=new JMenu("Create New");
		file=new JMenuItem("File");
		project=new JMenuItem("Project");
		
		item2=new JMenuItem("open", new ImageIcon("face.jpg"));
		item3=new JMenuItem("Save(S)");
		item3.setMnemonic('S');
		//��ɢװ����ӿ�ݷ�ʽ
		item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		item4=new JMenuItem("Save As");
		item5=new JMenuItem("Setting");
		item6=new JMenuItem("Print");
		item7=new JMenuItem("Quite");
		
		jta=new JTextArea();
		
		//����ť��ӵ���������
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		
		//���˵�����ӵ��˵���
		xinjian.add(file);
		xinjian.add(project);
		
		menu1.add(xinjian);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		menu1.addSeparator();//��ӷָ���
		menu1.add(item5);
		menu1.add(item6);
		menu1.addSeparator();//��ӷָ���
		menu1.add(item7);
		
		//���˵���ӵ��˵�����
		jmb.add(menu1);
		jmb.add(menu2);
		jmb.add(menu3);
		jmb.add(menu4);
		jmb.add(menu5);
		
		
		//���˵�����ӵ�������
		this.setJMenuBar(jmb);
		
		//����������ӵ�������
		this.add(jtb,BorderLayout.NORTH);
		
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
		
		
		
		
		
		
		
	
		
		
		//չ��
		this.setTitle("Notepad");
		ImageIcon icon =new ImageIcon("face.jpg");
		this.setIconImage(icon.getImage());//����������ͼ�귽��
		this.setSize(500,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notepad np=new Notepad();
	}

}
