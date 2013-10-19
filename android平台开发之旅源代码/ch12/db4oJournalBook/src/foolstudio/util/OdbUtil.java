package foolstudio.util;

import java.util.ArrayList;
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
	
	private static OdbUtil mInstance = new OdbUtil();
	
	//-------------------------------------------------------------------------
	private OdbUtil() {
	}
	
	//-------------------------------------------------------------------------
	//�����ӿ�
	public static OdbUtil getInstance() {
		return (mInstance);
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
	public ArrayList getObjects(final String odbName, Object proto) {
		System.out.println("Invoke routine getObjects");
		//�����ݿ�
		ObjectContainer odb = openDB(odbName);
		//��ѯ��ö���
		ObjectSet objectSet = odb.queryByExample(proto);
		//��������
		ArrayList objectDB = new ArrayList();

		while(objectSet.hasNext() ) { //�������󼯺�
			objectDB.add(objectSet.next() );
		}

		//�ر����ݿ�
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
	//��ȡ���ݿ���ָ�����������ж���(Native Query)
	public ArrayList queryObjects(final String odbName, Predicate predicate) {
		System.out.println("Invoke routine queryObjects");
		//�����ݿ�
		ObjectContainer odb = openDB(odbName);
		//��ѯ��ö���
		ObjectSet objectSet = odb.query(predicate);
		//��������
		ArrayList objectDB = new ArrayList();

		while(objectSet.hasNext() ) { //�������󼯺�
			objectDB.add(objectSet.next() );
		}

		//�ر����ݿ�
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
};