/*jdbc方式去操作数据库
 * 
 * 需要引入 三个jar包 (jdbc 驱动包）
 *都是    ms 开头的jar 包
 * 
 * 右击这个项目-----property-----java build path-----library------外部jar包---引入 * 
 */
package Test;
import java.sql.*;


public class JdbcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义需要的对象
		PreparedStatement ps=null;
		Connection  ct=null;
		ResultSet  rs=null;
		
		
		try {
			//初始化我们的对象
			//1.加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDirver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=name","username","password");
			//3.创建火箭车
			ps=ct.prepareStatement("select * from emp");
			//4.执行      如果是增加，删除，修改  用executeUpdate()
			//如果 是查询  用      executeQuery()
			rs=ps.executeQuery();
			//循环的取出       雇员的名字，薪水，部门的编号
			while (rs.next())
			{//
				String name =rs.getString(1);
				//如果sql 语句用*号，应该用rs.getString(2);
				//表示在数据库中实际的列的位置
				//也可以加入列名  rs.getString("name"); 则相应的type也要改
				float salary=rs.getFloat(2);
				int deptno=rs.getInt(3);
				System.out.println(name+""+salary+""+deptno);
			}
			
			
			
			//添加，删除，修改
			ps=ct.prepareStatement("insert into  dept values(?,?,?)");
			//给？ 赋值
			ps.setInt(1,100);
			ps.setString(2, "finacial");
			ps.setString (3,"da");
			//4.执行      如果是增加，删除，修改  用executeUpdate()
			//如果 是查询  用      executeQuery()
			int k=ps.executeUpdate();
			//循环的取出       雇员的名字，薪水，部门的编号
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
