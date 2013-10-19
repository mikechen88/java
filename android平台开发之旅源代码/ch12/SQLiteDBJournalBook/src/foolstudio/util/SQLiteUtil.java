package foolstudio.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
//
import java.io.File;

public class SQLiteUtil { //SQLite数据库公共函数库
	
	public static final String SQLite_MASTER_TABLE = "sqlite_master";
	
	private static SQLiteUtil mInstance = new SQLiteUtil();
	
	//-------------------------------------------------------------------------
	private SQLiteUtil() {
	}
	
	//-------------------------------------------------------------------------
	//单例接口
	public static SQLiteUtil getInstance() {
		return (mInstance);
	}	

	//-------------------------------------------------------------------------
	//打开数据库
	private SQLiteDatabase openDB(String dbName) {
		File file = new File(dbName);

		Log.d(this.getClass().getName(), file.getAbsolutePath() );

		if(file.exists() == true) { //库文件存在则打开数据库
			return (SQLiteDatabase.openDatabase(dbName, null, SQLiteDatabase.OPEN_READWRITE) );
		}
		else { //如果不存在则初始化
			return(SQLiteDatabase.openOrCreateDatabase(dbName, null) );
		}
	}

	//-------------------------------------------------------------------------
	//关闭数据库
	private void closeDB(SQLiteDatabase db) {
		db.close();
	}

	//-------------------------------------------------------------------------
	//删除数据库
	public boolean deleteDB(String dbName) {
		File file = new File(dbName);
		
		Log.d(this.getClass().getName(), file.getAbsolutePath() );

		if(file.exists() == true) { //库文件存在则删除
			return (file.delete() );
		}

		return (true);
	}

	//-------------------------------------------------------------------------
	//添加对象
	public void execQuery(String dbName, String sql) {
		SQLiteDatabase db = openDB(dbName);
        db.execSQL(sql);
        closeDB(db);
	}
	
	//-------------------------------------------------------------------------
	public Cursor openQuery(String dbName, String tableName, String condStr) {
		SQLiteDatabase db = openDB(dbName);
		Cursor cursor = db.query(tableName, null, condStr, null, null, null, null);
		//游标复位
		cursor.moveToFirst();
		
		//关闭文件
		closeDB(db);
		
		return (cursor);
	}
	
	//-------------------------------------------------------------------------
	public int getRowsCount(Cursor cursor) {
		return(cursor.getCount() );
	}
	
	//-------------------------------------------------------------------------
	public int getColumnsCount(Cursor cursor) {
		return(cursor.getColumnCount() );
	}
	
	//-------------------------------------------------------------------------
	public String getColumnNameBy(Cursor cursor, int index) {
		return(cursor.getColumnName(index) );
	}	
	
	//-------------------------------------------------------------------------
	public boolean isBOF(Cursor cursor) {
		return(cursor.isBeforeFirst());
	}
	
	//-------------------------------------------------------------------------
	public boolean isEOF(Cursor cursor) {
		return(cursor.isAfterLast() );
	}	
	
	//-------------------------------------------------------------------------
	public boolean moveNext(Cursor cursor) {
		return(cursor.moveToNext() );
	}
	
	//-------------------------------------------------------------------------
	public String getField(Cursor cursor, int index) {
		return(cursor.getString(index) );
	}
	
	//-------------------------------------------------------------------------
	public void closeQuery(Cursor cursor) {
		cursor.close();
	}
	
	//-------------------------------------------------------------------------
	public boolean isTableExists(String dbName, String tableName) {
		Cursor cursor = openQuery(dbName, SQLite_MASTER_TABLE, "(tbl_name='"+tableName+"')");
		int recordCount = cursor.getCount();
		cursor.close();
		
		return(recordCount > 0);	
	}

	//-------------------------------------------------------------------------
};