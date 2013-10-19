package com.szy.sqlite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class DBOpenHelper extends SQLiteOpenHelper
{
	private static final int VERSION = 1;
	private static final String DBNAME = "data.db";
	
	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table t_student (sid integer primary key,name varchar(20),age integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
