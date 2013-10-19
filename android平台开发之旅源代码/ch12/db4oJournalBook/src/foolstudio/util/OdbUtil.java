package foolstudio.util;

import java.util.ArrayList;
import android.util.Log;
//
import com.db4o.Db4o;
import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import java.io.File;
//配置相关
import com.db4o.config.Configuration;
import com.db4o.reflect.jdk.JdkReflector;
//import java.lang.ClassLoader;
//查询用
import com.db4o.query.Predicate;

public class OdbUtil { //对象数据库公共函数库
	
	private static OdbUtil mInstance = new OdbUtil();
	
	//-------------------------------------------------------------------------
	private OdbUtil() {
	}
	
	//-------------------------------------------------------------------------
	//单例接口
	public static OdbUtil getInstance() {
		return (mInstance);
	}	

	//-------------------------------------------------------------------------
	//打开数据库
	public ObjectContainer openDB(final String odbName) {
		File file = new File(odbName);

		Log.d("ODBUtility.openDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //库文件存在则打开数据库
			return (openDBFile(odbName) );
		}
		else { //如果不存在则初始化
			return(initDB(odbName) );
		}
	}
	
	//-------------------------------------------------------------------------
	//打开数据库文件
	private ObjectContainer openDBFile(final String odbName) {
		//创建一个新的配置实例
		Configuration config = Db4o.newConfiguration();
		//为配置指定特定的反射
		//config.reflectWith(new JdkReflector(ClassLoader.getSystemClassLoader() ) );
		config.reflectWith(new JdkReflector(this.getClass().getClassLoader() ) );

		//使用指定的配置打开数据库
		//如不使用指定的配置，在第二次打开数据库文件时会提示有关反射的运行时错误
		return (Db4o.openFile(config, odbName) );
	}
	
	//-------------------------------------------------------------------------
	//初始化数据库
	private ObjectContainer initDB(final String odbName) {
		File file = new File(odbName);

		Log.d("ODBUtility.initDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //库文件已经存在则删除
			if(file.delete() == false) { //删除失败
				return (null);
			}
		}

		return (openDBFile(odbName) );
	}	

	//-------------------------------------------------------------------------
	//关闭数据库
	public void closeDB(ObjectContainer odb) {
		odb.close();
	}

	//-------------------------------------------------------------------------
	//删除数据库
	public boolean deleteDB(final String odbName) {
		File file = new File(odbName);
		
		Log.d("ODBUtility.deleteDB: ", file.getAbsolutePath() );

		if(file.exists() == true) { //库文件存在则删除
			return (file.delete() );
		}

		return (true);
	}

	//-------------------------------------------------------------------------
	//添加对象
	public void appendObject(final String odbName, Object obj) {
		//打开数据库
		ObjectContainer odb = openDB(odbName);
		//存储对象
		odb.store(obj);
		//关闭数据库
		closeDB(odb);
	}

	//-------------------------------------------------------------------------
	//获取数据库中指定条件的所有对象(Query By Example)
	public ArrayList getObjects(final String odbName, Object proto) {
		System.out.println("Invoke routine getObjects");
		//打开数据库
		ObjectContainer odb = openDB(odbName);
		//查询获得对象集
		ObjectSet objectSet = odb.queryByExample(proto);
		//对象容器
		ArrayList objectDB = new ArrayList();

		while(objectSet.hasNext() ) { //遍历对象集合
			objectDB.add(objectSet.next() );
		}

		//关闭数据库
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
	//获取数据库中指定条件的所有对象(Native Query)
	public ArrayList queryObjects(final String odbName, Predicate predicate) {
		System.out.println("Invoke routine queryObjects");
		//打开数据库
		ObjectContainer odb = openDB(odbName);
		//查询获得对象集
		ObjectSet objectSet = odb.query(predicate);
		//对象容器
		ArrayList objectDB = new ArrayList();

		while(objectSet.hasNext() ) { //遍历对象集合
			objectDB.add(objectSet.next() );
		}

		//关闭数据库
		closeDB(odb);

		return (objectDB);
	}

	//-------------------------------------------------------------------------
};