/*
 * ��java �����ʹ��ddl��䣨creat,drop )
 * 
 */
package Test;

import java.sql.*;

public class ddl {
	//������Ҫ�Ķ���
			
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
			//�����������̶�д��
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDirver");
			//2.�õ�����
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=name","username","password");
			//3.���������ps �������ݿ�
			ps=ct.prepareStatement("create   database   vvv");
			//���ִ�е���ddl ���
			boolean b=ps.execute();
			if (b)
			  {
				  System.out.println("success");
				   }else{
					   System.out.println("failed");
				   }
			}catch(Exception e){
			e.printStackTrace();
			//�ر���Դ
			
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
		//�������ݿ�
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
