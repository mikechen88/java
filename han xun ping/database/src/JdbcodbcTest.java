/*
 * 1.��������Դ��
�������------������------����Դ------odbc����Դ������------��ϵͳDNS���������û������ã�(�û�DNS)��ֻ�и��û������á�-------���------sql   server---------������mytest)-----�������������ѡ�������ھ������ڲ������е����ݿ�ʵ�������Բ�Ҫѡ�������ֱ������local.ѡ�񱾵أ����ߴ�һ��С�㣨�����Ϳ��ԡ�-------ѡ���½SQL SERVER�ķ�ʽ-------����ѡʹ���û���¼ID �������SQL SERVER��֤��--------ѡ��Ĭ�����ӵ����ݿ�-------����-------���
2.�ڳ�������������Դ��
 * 
 */


import java.sql.*;
public class JdbcodbcTest {
	public static void main (String []  args)
	{
		Connection   ct=null;
		Statement sm=null;
		try {
			//1.���������������ǰ���Ҫ������������뵽�ڴ棩
			Class.forName("sun.jdbc.odbc.JdbcodbcDriver");
			//2.�õ�����  ,����  import  java.sql.*;ָ�����ӵ��ĸ�����Դ
			//�����������Դ��ʱ��ѡ�� windows nt��֤������Ҫ���û�������   �����롰
			//Connection   ct=DriverManager.getConnection("jdbc:odbc:databasname");			
			  ct=DriverManager.getConnection("jdbc:odbc:databasname","loginname","password");
			//3.����Statement ���� PreparedStatement 
			//Statement ��Ҫ���ڷ���sql ��䵽���ݿ�
			sm=ct.createStatement();
			
			//4.vִ��CRUD  ����
			
			
			
			//��ʾ���һ�����ݵ�  dept ��
			//executeUpdate ����ִ��CUD ���,���᷵��һ��int i=1˵��һ����¼���룬=n˵�� n����¼����
			/*sm.executeUpdate("insert into  dept  values('50','fire','seattle')");
			
			if (i==1){
				System.out.print("success");
					}else{
						System.out.print("fail");
					}*/
			
			//��ʾɾ���Ĳ�����
			/*int  i=sm.executeUpdate("delete from dept   where deptno='50'");
			if (i==1){
				System.out.print("ok");
				}else{
					System.out.print("error");
				}*/
			
			
			//�޸ĵĲ���,  deptno=40  ��local ��Ϊ beijing
		/*	int i=sm.executeUpdate("update dept set local='40'");
			if (i==1){
				System.out.print("ok");
				}else{
					System.out.print("error");
				}*/
			
			
			// ��ʾ��ѯ����ע���ǽ����û�� ����
			//��ʾ���еĲ���
			//���صĽ����  ResultSet �����,���԰������Ϊ���еĽ����
			//rs ������αָ꣬���һ�м�¼����һ�С�ʵ�����ǿգ����ֱ��ȡ�ᱨ��
			ResultSet rs=sm.executeQuery("selece * from dept");
			//ȡ������Ϊrsָ���˽������ǰһ�е���һ��
			//�Ϳ���ѭ��ȡ����
			while (rs.next())//��ͼȡ��ʱ����Ҫ�Ȱ��α�������һ�С�
			{
				
				//rs.next();
				int a=rs.getInt(1);
				System.out.print(a);
				//ȡ����һ�еĵڶ��У���Ϊstring
				String b=rs.getString(2);
				System.out.print(b);
			}		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.����ر���Դ  netstate -an ���˿�
			//�ر���Դ��˳���ǣ� ˭�󴴽����ȹرա�
			try {
				//Ϊ�˳������׳������if���
				if(sm!=null){
					sm.close();
				}
				if (ct!=null){
					ct.close();	
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}

}
