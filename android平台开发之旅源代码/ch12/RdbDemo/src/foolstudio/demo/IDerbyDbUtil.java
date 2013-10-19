package foolstudio.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IDerbyDbUtil {
	//打开数据库连接
	public Connection openDB();
	//获取查询用语句对象
	public Statement getQueryStat(Connection conn);
	//获取执行用语句对象
	public Statement getExecStat(Connection conn);
	//打开查询返回结果集
	public ResultSet openQuery(Statement stat, String sql);
	//执行查询
	public boolean execQuery(Statement stat, String sql);
	//
	//获取结果集行数
	public int getRowsCount(ResultSet rs);
	//判断指定数据表是否存在
	public boolean isTableExists(Statement stat, String tableName);
	//创建指定数据表
	public boolean createTable(Statement stat, String tableName, String args);
	//插入记录到指定表
	public boolean insertTable(Statement stat, String tableName, String args);
	//更新记录指定表
	public boolean updateTable(Statement stat, String tableName, String args);
	//删除数据表
	public boolean dropTable(Statement stat, String tableName);
	//
	//关闭查询
	public boolean closeQuery(ResultSet rs);
	//关闭语句
	public boolean closeStat(Statement stat);
	//关闭数据库连接
	public boolean closeDB(Connection conn);	
};
