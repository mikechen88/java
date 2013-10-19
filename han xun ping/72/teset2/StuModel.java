package com.teset2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.*;
public class StuModel extends AbstractTableModel{

	//rowData�������������
	//columnNames�������
	Vector rowData, columnNames;
	
	//�����������ݿ����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	String url="jdbc:microsoft:sqlserver://127.0.0.1:1433; databaseName=spdb1";
	String user="sa";
	String passwd="sangliyang";
	String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	
	//����ѧ��
	public boolean updStu(String sql, String []paras)
	{
		
		boolean b=true;
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			ct=DriverManager.getConnection(url, user, passwd);
			//����ps
			ps=ct.prepareStatement(sql);
			//��sql����е�?�Ÿ�ֵ
			for(int i=0; i<paras.length; i++)
			{
				ps.setString(i+1, paras[i]);
				//��Ϊ��ֵ��ʱ���1 ��ʼ������
			}
			//4ִ�в���
			// �����ڳ���ps.executeUpdate()����ᱨΥ������Լ��
			if(ps.executeUpdate()!=1)  // ִ��sql���
			{
				b=false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
		}
			
	}
		return b;
}
	
	
	
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="use spdb1; select *from stu";
		}
		//�м�
		columnNames=new Vector();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData=new Vector();
		//�����ݿ���ȡ������
		rowData=new Vector();
		try {
			//��������
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433; databaseName=spdb1","sa","sangliyang");
			//׼����˵��� .ע��Ҫ��  use spdb1; ����ᱨ ������
			ps=ct.prepareStatement(sql);	
			//ִ�в�ѯ
  			 rs=ps.executeQuery();      
  			 // ���ز�ѯ���
			while(rs.next())
			{
				//rowData
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				//���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	//ͨ�����ݵ�sql������������ģ��
	public StuModel(String sql)
	{
		this.init(sql);
	}
	//��һ�����캯����ʼ������ģ��
	public StuModel()
	{
		this.init("");
	}
	
	//�õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	//	return 0;
	}
	//�õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
		//return 0;
	}
	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(arg0)).get(arg1);    // arg0��ʾ�� arg1 ��ʾ��
	//	return null;
	}

	//��д���� getColumnName
	@Override  
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

}