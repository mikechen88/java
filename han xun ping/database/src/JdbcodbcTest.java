/*
 * 1.配置数据源：
控制面板------管理工具------数据源------odbc数据源管理器------（系统DNS）则所有用户都能用；(用户DNS)则只有该用户可以用。-------添加------sql   server---------起名（mytest)-----服务器名（如果选择它会在局域网内查找所有的数据库实例，所以不要选），最好直接输入local.选择本地，或者打一个小点（。）就可以。-------选择登陆SQL SERVER的方式-------可以选使用用户登录ID 和密码的SQL SERVER验证。--------选择默认连接的数据库-------测试-------完成
2.在程序中连接数据源：
 * 
 */


import java.sql.*;
public class JdbcodbcTest {
	public static void main (String []  args)
	{
		Connection   ct=null;
		Statement sm=null;
		try {
			//1.加载驱动（作用是把需要的驱动程序加入到内存）
			Class.forName("sun.jdbc.odbc.JdbcodbcDriver");
			//2.得到连接  ,引包  import  java.sql.*;指定连接到哪个数据源
			//如果配置数据源的时候，选择 windows nt验证，则不需要”用户名“，   ”密码“
			//Connection   ct=DriverManager.getConnection("jdbc:odbc:databasname");			
			  ct=DriverManager.getConnection("jdbc:odbc:databasname","loginname","password");
			//3.创建Statement 或者 PreparedStatement 
			//Statement 主要用于发送sql 语句到数据库
			sm=ct.createStatement();
			
			//4.v执行CRUD  操作
			
			
			
			//演示添加一条数据到  dept 表
			//executeUpdate 可以执行CUD 语句,它会返回一个int i=1说明一条记录加入，=n说明 n条记录加入
			/*sm.executeUpdate("insert into  dept  values('50','fire','seattle')");
			
			if (i==1){
				System.out.print("success");
					}else{
						System.out.print("fail");
					}*/
			
			//演示删除的操作：
			/*int  i=sm.executeUpdate("delete from dept   where deptno='50'");
			if (i==1){
				System.out.print("ok");
				}else{
					System.out.print("error");
				}*/
			
			
			//修改的操作,  deptno=40  的local 改为 beijing
		/*	int i=sm.executeUpdate("update dept set local='40'");
			if (i==1){
				System.out.print("ok");
				}else{
					System.out.print("error");
				}*/
			
			
			// 演示查询，关注的是结果有没有 回来
			//显示所有的部门
			//返回的结果是  ResultSet 结果集,可以把它理解为表行的结果集
			//rs 相关于游标，指向第一行记录的上一行。实际上是空，如果直接取会报错。
			ResultSet rs=sm.executeQuery("selece * from dept");
			//取出，因为rs指向了结果集的前一行的上一行
			//就可以循环取出，
			while (rs.next())//试图取出时，就要先把游标往下移一行。
			{
				
				//rs.next();
				int a=rs.getInt(1);
				System.out.print(a);
				//取出第一行的第二列，且为string
				String b=rs.getString(2);
				System.out.print(b);
			}		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.必须关闭资源  netstate -an 检查端口
			//关闭资源的顺序是： 谁后创建则先关闭。
			try {
				//为了程序更健壮，加入if语句
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
