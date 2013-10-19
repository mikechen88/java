package foolstudio.demo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
//
import org.apache.derby.jdbc.EmbeddedSimpleDataSource;

public class DerbyDB implements IDerbyDbUtil{
	private static EmbeddedSimpleDataSource mDS = 
		EmbeddedDataSource.getInstance().getDataSource();
	
	//打开数据库连接
	public Connection openDB() {
		// TODO Auto-generated method stub
		try {
			return(mDS.getConnection() );
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return (null);
	}
	
	//获取记录集行数
	public int getRowsCount(ResultSet rs) {
		try {
			int rowsCount = 0; 
			//Backup current row no. 
			int rowNo = rs.getRow(); 
			//Locate last row 
			rs.last(); 
			//Get the rows count 
			rowsCount = rs.getRow(); 
			//Return back original row 
			if(rowNo < 1) { //before first row 
				rs.beforeFirst();
			} 
			else {// 
				rs.absolute(rowNo);
			}
			
			return (rowsCount);
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return (0);
		}
	}	

	//判断指定数据表是否存在
	public boolean isTableExists(Statement stat, String tableName) {
		// TODO Auto-generated method stub
		String sql = ("SELECT tablename FROM sys.systables WHERE tablename='" + 
				tableName + "'");
		
		//System.out.println(sql);
		
		try {
			ResultSet rs = stat.executeQuery(sql);
			
			//System.out.println("" +  getRowsCount(rs) );
			
			return(getRowsCount(rs) > 0);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//关闭数据库连接
	public boolean closeDB(Connection conn) {
		// TODO Auto-generated method stub
		try {
			if(conn != null) {
				conn.close();
			}
			
			mDS.setShutdownDatabase("shutdown");
			
			return (true);
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			//return (false);
		}

		return (true);
	}

	//关闭数据集
	public boolean closeQuery(ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//关闭语句对象
	public boolean closeStat(Statement stat) {
		// TODO Auto-generated method stub
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//获取执行用语句对象
	public Statement getExecStat(Connection conn) {
		// TODO Auto-generated method stub
		try {
			return(conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
										ResultSet.CONCUR_UPDATABLE) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (null);
	}

	//获取查询用语句对象
	public Statement getQueryStat(Connection conn) {
		// TODO Auto-generated method stub
		try {
			return(conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
										ResultSet.CONCUR_READ_ONLY) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (null);
	}

	//打开查询获取数据集
	public ResultSet openQuery(Statement stat, String sql) {
		// TODO Auto-generated method stub
		try {
			return(stat.executeQuery(sql) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (null);
	}	
	
	//执行查询
	public boolean execQuery(Statement stat, String sql) {
		// TODO Auto-generated method stub
		try {
			stat.execute(sql);
			
			return (true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	//创建指定数据表
	public boolean createTable(Statement stat, String tableName, String args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("CREATE TABLE ");
		sb.append(tableName);
		sb.append(args);
		
		try {
			stat.execute(sb.toString() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//删除指定数据表
	public boolean dropTable(Statement stat, String tableName) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("DROP TABLE ");
		sb.append(tableName);
		
		try {
			stat.execute(sb.toString() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//插入记录到数据表
	public boolean insertTable(Statement stat, String tableName, String args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		sb.append(tableName);
		sb.append(args);
		
		try {
			stat.execute(sb.toString() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//更新数据表
	public boolean updateTable(Statement stat, String tableName, String args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("UPDATE TABLE ");
		sb.append(tableName);
		sb.append(args);
		
		try {
			stat.execute(sb.toString() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}	
};
