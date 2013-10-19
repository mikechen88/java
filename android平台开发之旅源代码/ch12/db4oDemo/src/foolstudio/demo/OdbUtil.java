package foolstudio.demo;

import java.util.Calendar;
import java.util.Vector;
import android.util.Log;
//
import com.db4o.Db4o;
import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import java.io.File;
//�������
import com.db4o.config.Configuration;
import com.db4o.reflect.jdk.JdkReflector;
//import java.lang.ClassLoader;
//��ѯ��
import com.db4o.query.Predicate;

public class OdbUtil { //�������ݿ⹫��������
	//-------------------------------------------------------------------------
	public OdbUtil() {
	}
	
	//-------------------------------------------------------------------------	
	public String getDateTimeString() {
		Calendar rightNow = Calendar.getInstance();
		StringBuffer buffer = new StringBuffer();

		buffer.append(rightNow.get(Calendar.YEAR) + "/" +
			(rightNow.get(Calendar.MONTH) + 1) + "/" +
			rightNow.get(Calendar.DAY_OF_MONTH) + " " +
			rightNow.get(Calendar.HOUR_OF_DAY) + ":" +
			rightNow.get(Calendar.MINUTE) + ":" +
			rightNow.get(Calendar.SECOND) );

		return (buffer.toString() );
	}	

	//-------------------------------------------------------------------------
	//�����ݿ�
	public ObjectContainer openDB(final String odbName) {
		File file = new File(odbName);

		Log.d("ODBUtility.openDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //���ļ�����������ݿ�
			return (openDBFile(odbName) );
		}
		else { //������������ʼ��
			return(initDB(odbName) );
		}
	}
	
	//-------------------------------------------------------------------------
	//�����ݿ��ļ�
	private ObjectContainer openDBFile(final String odbName) {
		//����һ���µ�����ʵ��
		Configuration config = Db4o.newConfiguration();
		//Ϊ����ָ���ض��ķ���
		//config.reflectWith(new JdkReflector(ClassLoader.getSystemClassLoader() ) );
		config.reflectWith(new JdkReflector(this.getClass().getClassLoader() ) );

		//ʹ��ָ�������ô����ݿ�
		//�粻ʹ��ָ�������ã��ڵڶ��δ����ݿ��ļ�ʱ����ʾ�йط��������ʱ����
		return (Db4o.openFile(config, odbName) );
	}
	
	//-------------------------------------------------------------------------
	//��ʼ�����ݿ�
	private ObjectContainer initDB(final String odbName) {
		File file = new File(odbName);

		Log.d("ODBUtility.initDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //���ļ��Ѿ�������ɾ��
			if(file.delete() == false) { //ɾ��ʧ��
				return (null);
			}
		}

		return (openDBFile(odbName) );
	}	

	//-------------------------------------------------------------------------
	//�ر����ݿ�
	public void closeDB(ObjectContainer odb) {
		odb.close();
	}

	//-------------------------------------------------------------------------
	//ɾ�����ݿ�
	public boolean deleteDB(final String odbName) {
		File file = new File(odbName);
		
		Log.d("ODBUtility.deleteDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //���ļ�������ɾ��
			return (file.delete() );
		}

		return (true);
	}

	//-------------------------------------------------------------------------
	//��Ӷ���
	public void appendObject(final String odbName, Object obj) {
		//�����ݿ�
		ObjectContainer odb = openDB(odbName);
		//�洢����
		odb.store(obj);
		//�ر����ݿ�
		closeDB(odb);
	}

	//-------------------------------------------------------------------------
	//��ȡ���ݿ���ָ�����������ж���(Query By Example)
	public Vector getObjects(final String odbName, Object proto) {
		System.out.println("Invoke routine getObjects");
		//�����ݿ�
		ObjectContainer odb = openDB(odbName);
		//��ѯ��ö���
		ObjectSet objectSet = odb.queryByExample(proto);
		//��������
		Vector objectDB = new Vector();

		while(objectSet.hasNext() ) { //�������󼯺�
			objectDB.addElement(objectSet.next() );
		}

		//�ر����ݿ�
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
	//��ȡ���ݿ���ָ�����������ж���(Native Query)
	public Vector queryObjects(final String odbName, Predicate predicate) {
		System.out.println("Invoke routine queryObjects");
		//�����ݿ�
		ObjectContainer odb = openDB(odbName);
		//��ѯ��ö���
		ObjectSet objectSet = odb.query(predicate);
		//��������
		Vector objectDB = new Vector();

		while(objectSet.hasNext() ) { //�������󼯺�
			objectDB.addElement(objectSet.next() );
		}

		//�ر����ݿ�
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
};