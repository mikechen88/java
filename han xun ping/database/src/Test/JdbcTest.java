/*jdbc��ʽȥ�������ݿ�
 * 
 * ��Ҫ���� ����jar�� (jdbc ��������
 *����    ms ��ͷ��jar ��
 * 
 * �һ������Ŀ-----property-----java build path-----library------�ⲿjar��---���� * 
 */
package Test;
import java.sql.*;


public class JdbcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������Ҫ�Ķ���
		PreparedStatement ps=null;
		Connection  ct=null;
		ResultSet  rs=null;
		
		
		try {
			//��ʼ�����ǵĶ���
			//1.��������
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDirver");
			//2.�õ�����
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=name","username","password");
			//3.���������
			ps=ct.prepareStatement("select * from emp");
			//4.ִ��      ��������ӣ�ɾ�����޸�  ��executeUpdate()
			//��� �ǲ�ѯ  ��      executeQuery()
			rs=ps.executeQuery();
			//ѭ����ȡ��       ��Ա�����֣�нˮ�����ŵı��
			while (rs.next())
			{//
				String name =rs.getString(1);
				//���sql �����*�ţ�Ӧ����rs.getString(2);
				//��ʾ�����ݿ���ʵ�ʵ��е�λ��
				//Ҳ���Լ�������  rs.getString("name"); ����Ӧ��typeҲҪ��
				float salary=rs.getFloat(2);
				int deptno=rs.getInt(3);
				System.out.println(name+""+salary+""+deptno);
			}
			
			
			
			//��ӣ�ɾ�����޸�
			ps=ct.prepareStatement("insert into  dept values(?,?,?)");
			//���� ��ֵ
			ps.setInt(1,100);
			ps.setString(2, "finacial");
			ps.setString (3,"da");
			//4.ִ��      ��������ӣ�ɾ�����޸�  ��executeUpdate()
			//��� �ǲ�ѯ  ��      executeQuery()
			int k=ps.executeUpdate();
			//ѭ����ȡ��       ��Ա�����֣�нˮ�����ŵı��
			if (k==1)
			  {
				  System.out.println("success");
				   }else{
					   System.out.println("failed");
				   }
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}

}
