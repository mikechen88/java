/**
 * 修改学生信息
 */

import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener{
	
	//定义我需要的swing组件
	JLabel jl1, jl2, jl3, jl4, jl5;
	JButton jb1,jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
	JPanel jp1, jp2, jp3;
	
	//owner 它的父窗口，这个窗口是从它的父窗口生出来的
	//title 窗口名
	//modal 指定的模式窗口，还有非模式窗口，  
	//模式窗口就是说   打开这个窗口后，不许用户再点别的窗口
	public StuAddDialog(Frame owner, String title, boolean modal)
	{
		super(owner, title, modal); //调用父类的构造方法
		jl1=new JLabel("学号");
		jl2=new JLabel("姓名");
		jl3=new JLabel("性别");
		jl4=new JLabel("姓名");
		jl5=new JLabel("姓名");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		
		jb1=new JButton("添加");
		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		//展现
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			//连接数据库
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			//连接数据库,判断数据库是否合法
			try {
				//1：加载驱动
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				//2：创建数据库、
				conn=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433; databaseName=spdb1","sa","sangliyang");
				//3:获取语句对象
				stmt=conn.createStatement();
				//4：编辑语句对象
				String strsql="insert into student";
				pstmt=conn.prepareStatement(strsql);
				
				//给参数赋值
				pstmt.setString(1, jtf1.getText());
				pstmt.setString(2, jtf2.getText());
				pstmt.setString(3, jtf3.getText());
				pstmt.setString(4, jtf4.getText());
				pstmt.setString(5, jtf5.getText());
				
				//执行操作
				pstmt.executeUpdate();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}finally
			{
				//释放资源
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					if(conn!=null)	conn.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		}
	}

}
