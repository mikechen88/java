package com.myl.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
public class UserLogin extends 	JDialog{
	
	//定义需要的组件
	JLabel jl1, jl2, jl3;
	JTextField jName;
	JPasswordField jPasswd;
	JButton jCon, jCancel;
	Font f1=new Font("宋体",Font.PLAIN, 16);
	//在创建一款字体
	Font f2=new Font("宋体",Font.PLAIN, 14);
	public static void main(String [] args)
	{
		UserLogin ul=new UserLogin();
	}
	
	public UserLogin()
	{
		//从父类得到 container.也较适合空布局。所有组件都 活在它里面。
		Container ct=this.getContentPane();
		//设置成空布局
		this.setLayout(null);
		//创建各个组件
		jl1=new JLabel("请输入用户名");
		//设置位置
		jl1.setBounds(60, 190, 150, 15);
		jl1.setFont(f1);
		//放入
		ct.add(jl1);
		
		jName=new JTextField(20);
		jName.setFont(f1);
		jName.setBounds(180, 190,120 , 30);
		//设置下凹的感觉
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		
		jl2=new JLabel ("(员工号)");
		jl2.setFont(f2);
		//设置前景色
		jl2.setForeground(Color.red);
		jl2.setBounds(100, 210, 100, 30);
		ct.add(jl2);
		
		//提示输入密码
		jl3=new JLabel ("请输入密码");
		jl3.setFont(f1);
		jl3.setBounds(60,240,150,30);
		ct.add(jl3);
		
		//密码框
		jPasswd=new JPasswordField(20);
		jPasswd.setFont(f1);
		jPasswd.setBounds(180, 240,120 , 30);
		//设置下凹的感觉
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		
		
		jCon=new JButton("确定");
		jCon.setFont(f1);
		jCon.setBounds(110, 300, 70, 30);
		//放入到容器
		ct.add(jCon);
		
		//加入取消按钮
		jCancel=new JButton("取消");
		jCancel.setFont(f1);
		jCancel.setBounds(220, 300, 70, 30);
		ct.add(jCancel);
		
		
		jl2=new JLabel("请输入用户名");
		jl3=new JLabel("请输入用户名");
		
		
		
		//创建一个BackImage对象
		BackImage bi=new BackImage();
		//前面是空布局，定义图片的位置
		bi.setBounds(0, 0, 360, 360);
		//不是用上下框   	x, y, width, height
		
		//把一个组件放入到JFrame,或者JDialog中可以直接放入
	//	this.add(bi);
		//也可以这样做
		
		ct.add(bi);
		//不使用window 缺省的的上下框及图标
		this.setUndecorated(true);
		this.setSize(360,360);
		//确定JWindow的初始位置
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-150);
		this.setVisible(true);
	}
	//内部类
	class BackImage extends JPanel
	{
		Image im;
		public BackImage()
		{
			try {
				im=ImageIO.read(new File("image\\login.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(im, 0,0, 360, 360, this);
		}
	}
}
