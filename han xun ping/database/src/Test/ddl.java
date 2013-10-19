/*
 * 在java 中如何使用ddl语句（creat,drop )
 * 
 */
package Test;

import java.sql.*;

public class ddl {
	//定义需要的对象
			
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		Connection  ct=null;
		ResultSet  rs=null;
		
		
		try {
			//加载驱动，固定写法
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDirver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=name","username","password");
			//3.创建火箭车ps 创建数据库
			ps=ct.prepareStatement("create   database   vvv");
			//如果执行的是ddl 语句
			boolean b=ps.execute();
			if (b)
			  {
				  System.out.println("success");
				   }else{
					   System.out.println("failed");
				   }
			}catch(Exception e){
			e.printStackTrace();
			//关闭资源
			
		}finally{
			try{
				if(ps!=null)
				{
					ps.close();
				}
			}catch( Exception e)
			{
				e.printStackTrace();
			}			
		}
		//备份数据库
		 try {
			ps1=ct.prepareStatement("backup database  bbb   to disk='f:/123.bak'");
			boolean b=ps1.execute();
			if (b)
			  {
				  System.out.println("success");
				   }else{
					   System.out.println("failed");
				   }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
