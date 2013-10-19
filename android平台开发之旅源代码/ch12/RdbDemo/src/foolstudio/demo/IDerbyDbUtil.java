package foolstudio.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IDerbyDbUtil {
	//�����ݿ�����
	public Connection openDB();
	//��ȡ��ѯ��������
	public Statement getQueryStat(Connection conn);
	//��ȡִ����������
	public Statement getExecStat(Connection conn);
	//�򿪲�ѯ���ؽ����
	public ResultSet openQuery(Statement stat, String sql);
	//ִ�в�ѯ
	public boolean execQuery(Statement stat, String sql);
	//
	//��ȡ���������
	public int getRowsCount(ResultSet rs);
	//�ж�ָ�����ݱ��Ƿ����
	public boolean isTableExists(Statement stat, String tableName);
	//����ָ�����ݱ�
	public boolean createTable(Statement stat, String tableName, String args);
	//�����¼��ָ����
	public boolean insertTable(Statement stat, String tableName, String args);
	//���¼�¼ָ����
	public boolean updateTable(Statement stat, String tableName, String args);
	//ɾ�����ݱ�
	public boolean dropTable(Statement stat, String tableName);
	//
	//�رղ�ѯ
	public boolean closeQuery(ResultSet rs);
	//�ر����
	public boolean closeStat(Statement stat);
	//�ر����ݿ�����
	public boolean closeDB(Connection conn);	
};
