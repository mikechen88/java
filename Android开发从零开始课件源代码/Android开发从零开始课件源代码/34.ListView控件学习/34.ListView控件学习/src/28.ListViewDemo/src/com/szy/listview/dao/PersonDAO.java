package com.szy.listview.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class PersonDAO
{
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public PersonDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}

	public Cursor getPersons(int start, int count)
	{
		db = helper.getWritableDatabase();
		Cursor cursor=db.query("t_person", new String[]{"_id","pname","pgender"}, null, null, null, null, "_id desc",start+","+count);
		return cursor;
	}
	
	public Cursor getPersons()
	{
		db = helper.getWritableDatabase();
		Cursor cursor=db.query("t_person", new String[]{"_id,pname,pgender"}, null, null, null, null, null);
		return cursor;
	}
	
	public long getCount()
	{
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from t_person", null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}
}
