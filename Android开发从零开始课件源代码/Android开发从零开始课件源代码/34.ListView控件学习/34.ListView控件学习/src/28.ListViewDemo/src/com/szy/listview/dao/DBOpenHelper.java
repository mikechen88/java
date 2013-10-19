package com.szy.listview.dao;

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
	private static final String PERSON="t_person";
	
	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table "+PERSON+" (_id varchar(4) primary key,pname varchar(20),pgender varchar(2))");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1001','ÕÅÈı','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1002','ÀîËÄ','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1003','ÍõÎå','Å®')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1004','ÕÔÇ®','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1005','ËïÀî','Å®')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1006','ÖÜÎâ','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1007','Ö£Íõ','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1008','·ë³Â','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1009','ñÒÎÀ','Å®')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1010','½¯Éò','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1011','º«Ñî','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1012','ÖìÇØ','ÄĞ')");
		db.execSQL("insert into t_person (_id, pname, pgender) values ('1013','ÓÈĞí','ÄĞ')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
	}
}
