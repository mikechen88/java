/*
 * BorderLayout
 * 1.����
 * 2.�̳�JFrame
 * 3.�������
 * 4.�������
 * 5.������
 * 6.�Դ�������
 * 7.��ʾ����
 */

import java.awt.*;
import javax.swing.*;


public class BorderLayout1 extends JFrame {
	
	//�������
	JButton jb1,jb2,jb3,jb4,jb5;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BorderLayout1 bl=new BorderLayout1();
	}
	public BorderLayout1()
	{
		jb1=new JButton("middle");
		jb2=new JButton("north");
		jb3=new JButton("east");
		jb4=new JButton("south");
		jb5=new JButton("west");
		
		//�������� ��ӵ�ʱ��������治�Ӳ����Ļ������5 �������ʵ����ֻ����һ������ֻ���м���ʾ
		this.add(jb1,BorderLayout.CENTER);
		this.add(jb2,BorderLayout.NORTH);
		this.add(jb3,BorderLayout.EAST);
		this.add(jb4,BorderLayout.SOUTH);
		this.add(jb5,BorderLayout.WEST);
		
		
		//���ô�������
		this .setTitle("borderlayout example");
		this.setSize(300,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ����
		
		this.setVisible(true);
		
	}

}
