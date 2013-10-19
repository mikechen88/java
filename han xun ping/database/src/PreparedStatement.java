/*
 * PreparedStatement 实例
 */
import java.sql.*;
public class PreparedStatement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义需要的对象
		PreparedStatement  ps=null;
		ResultSet  rs=null;
		Connection   ct=null;
		
		try {
			//1.加载驱动（作用是把需要的驱动程序加入到内存）
			Class.forName("sun.jdbc.odbc.JdbcodbcDriver");
			//2.得到连接  ,引包  import  java.sql.*;指定连接到哪个数据源
			//如果配置数据源的时候，选择 windows nt验证，则不需要”用户名“，   ”密码“
			//Connection   ct=DriverManager.getConnection("jdbc:odbc:databasname");			
			  ct=DriverManager.getConnection("jdbc:odbc:databasname","loginname","password");
			//3. PreparedStatement 
			//Statement 主要用于发送sql 语句到数据库
			  
			  
			  
			  
			  
			  
			  
			/*ps=ct.prepareStatement("select * from dept");
			
			 * ps=ct.prepareStatement("select * from dept   where   deptno=?  and loc=?");
			 * 给？  赋值, 1表示 第一个问号
			 * ps. setInt  (1, 20);
			 * 
			 * ps.setString(2," seattle");
			 * 2表示给第二个问号赋值。
			 * 
			 * 
			 
			rs=ps.executeQuery();
			
				while( rs.next())
				{
					System.out.println(rs.getInt(1)+"  " +rs.getString(2)+" "+rs.getString(3));
				}*/
			  //使用 PreparedStatement 添加一条记录
			  ps = ct.prepareStatement("insert  into dept  values (?,?,?)");
			  ps.setInt (1,50);
			  ps.setString (2,"fire");
			  ps.setString (3."beijing");
			  //执行
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
			//5.必须关闭资源  netstate -an 检查端口
			//关闭资源的顺序是： 谁后创建则先关闭。
			try {
				//为了程序更健壮，加入if语句
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
