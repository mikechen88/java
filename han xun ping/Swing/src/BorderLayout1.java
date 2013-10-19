/*
 * BorderLayout
 * 1.引包
 * 2.继承JFrame
 * 3.定义组件
 * 4.创建组件
 * 5.添加组件
 * 6.对窗体设置
 * 7.显示窗体
 */

import java.awt.*;
import javax.swing.*;


public class BorderLayout1 extends JFrame {
	
	//定义组件
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
		
		//添加组件， 添加的时候，如果后面不加参数的话，添加5 个组件，实际上只看到一个，它只在中间显示
		this.add(jb1,BorderLayout.CENTER);
		this.add(jb2,BorderLayout.NORTH);
		this.add(jb3,BorderLayout.EAST);
		this.add(jb4,BorderLayout.SOUTH);
		this.add(jb5,BorderLayout.WEST);
		
		
		//设置窗体属性
		this .setTitle("borderlayout example");
		this.setSize(300,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示窗体
		
		this.setVisible(true);
		
	}

}
