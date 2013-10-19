/*
 * 多种布局管理器的使用
 */

import javax.swing.*;

import java.awt.*;
public class JPanelEx extends JFrame {

	//定义组件
	JPanel  jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanelEx jp=new JPanelEx();
	}
	public JPanelEx() {
		// 创建组件
		//Jpanel布局默认是FlowLayout
		jp1=new JPanel();
		jp2=new JPanel();
		
		
		jb1=new JButton("melon");
		jb2=new JButton("apple");
		jb3=new JButton("bannan");
		jb4=new JButton("peach");
		jb5=new JButton("pineapple");
		jb6=new JButton("punkin");

		
		
		
		//添加JPanel
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.add(jb5);
		
		//设置布局
		//把JPanel 加入JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb6, BorderLayout.CENTER);
		this .add(jp2,BorderLayout.SOUTH);
		
		this.setSize(300,150);
		this.setLocation(200,200);
		this.setVisible(true);
		
	}

}
