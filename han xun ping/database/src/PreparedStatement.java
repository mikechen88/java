/*
 * PreparedStatement ʵ��
 */
import java.sql.*;
public class PreparedStatement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������Ҫ�Ķ���
		PreparedStatement  ps=null;
		ResultSet  rs=null;
		Connection   ct=null;
		
		try {
			//1.���������������ǰ���Ҫ������������뵽�ڴ棩
			Class.forName("sun.jdbc.odbc.JdbcodbcDriver");
			//2.�õ�����  ,����  import  java.sql.*;ָ�����ӵ��ĸ�����Դ
			//�����������Դ��ʱ��ѡ�� windows nt��֤������Ҫ���û�������   �����롰
			//Connection   ct=DriverManager.getConnection("jdbc:odbc:databasname");			
			  ct=DriverManager.getConnection("jdbc:odbc:databasname","loginname","password");
			//3. PreparedStatement 
			//Statement ��Ҫ���ڷ���sql ��䵽���ݿ�
			  
			  
			  
			  
			  
			  
			  
			/*ps=ct.prepareStatement("select * from dept");
			
			 * ps=ct.prepareStatement("select * from dept   where   deptno=?  and loc=?");
			 * ����  ��ֵ, 1��ʾ ��һ���ʺ�
			 * ps. setInt  (1, 20);
			 * 
			 * ps.setString(2," seattle");
			 * 2��ʾ���ڶ����ʺŸ�ֵ��
			 * 
			 * 
			 
			rs=ps.executeQuery();
			
				while( rs.next())
				{
					System.out.println(rs.getInt(1)+"  " +rs.getString(2)+" "+rs.getString(3));
				}*/
			  //ʹ�� PreparedStatement ���һ����¼
			  ps = ct.prepareStatement("insert  into dept  values (?,?,?)");
			  ps.setInt (1,50);
			  ps.setString (2,"fire");
			  ps.setString (3."beijing");
			  //ִ��
			 int i= ps.executeUpdate();
			  if (i==1)
			  {
				  System.out.println("success");
				   }else{
					   System.out.println("failed");
				   }
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//5.����ر���Դ  netstate -an ���˿�
			//�ر���Դ��˳���ǣ� ˭�󴴽����ȹرա�
			try {
				//Ϊ�˳������׳������if���
				if (rs!=null)
				{
					rs.close();
				}
				if(ps!=null){
					
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
