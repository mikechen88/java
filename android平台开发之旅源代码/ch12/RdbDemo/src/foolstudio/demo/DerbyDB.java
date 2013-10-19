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
	
	//�����ݿ�����
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
	
	//��ȡ��¼������
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

	//�ж�ָ�����ݱ��Ƿ����
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
	
	//�ر����ݿ�����
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

	//�ر����ݼ�
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

	//�ر�������
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

	//��ȡִ����������
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

	//��ȡ��ѯ��������
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

	//�򿪲�ѯ��ȡ���ݼ�
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
	
	//ִ�в�ѯ
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

	//����ָ�����ݱ�
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

	//ɾ��ָ�����ݱ�
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

	//�����¼�����ݱ�
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

	//�������ݱ�
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
