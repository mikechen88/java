package com.szy.sqlite.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.szy.sqlite.dao.DBOpenHelper;
 
/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class StudentContentProvider extends ContentProvider
{
	private DBOpenHelper dbOpenHelper;
	private final static int STUDENTS = 1;
	private final static int STUDENT = 2;
	private final static String AUTHORITY = "com.szy.provider.studentprovider";
	private static final UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static
	{
		sMatcher.addURI(AUTHORITY, "student", STUDENTS);
		sMatcher.addURI(AUTHORITY, "student/#", STUDENT);
	}

	//delete from t_student where sid in (...)
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (sMatcher.match(uri))
		{
		//uri="content://com.szy.provider.studentprovider/person"
		case STUDENTS:
			count = db.delete("t_student", selection, selectionArgs);
			break;
		//uri="com.szy.provider.studentprovider/person/#"
		case STUDENT:
			long sid = ContentUris.parseId(uri);
			String where = TextUtils.isEmpty(selection) ? "sid=?" : selection + " and sid=?";
			String[] params = new String[] { String.valueOf(sid) };
			if (!TextUtils.isEmpty(selection) && selectionArgs != null)
			{
				params = new String[selectionArgs.length + 1];
				for (int i = 0; i < selectionArgs.length; i++)
				{
					params[i] = selectionArgs[i];
				}
				params[selectionArgs.length] = String.valueOf(sid);
			}
			count = db.delete("t_student", where, params);
			break;
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
		return count;
	}

	@Override
	public String getType(Uri uri)
	{
		switch (sMatcher.match(uri))
		{
		case STUDENTS:
			return "vnd.android.cursor.dir/personprovider.person";
		case STUDENT:
			return "vnd.android.cursor.item/personprovider.person";
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		long pid = 0;
		switch (sMatcher.match(uri))
		{
		case STUDENTS:
			pid = db.insert("t_student", "name", values);
			return ContentUris.withAppendedId(uri, pid);
		case STUDENT:
			pid = db.insert("t_student", "name", values);
			String path = uri.toString();
			return Uri.parse(path.substring(0, path.lastIndexOf('/')+1) + pid);
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	@Override
	public boolean onCreate()
	{
		dbOpenHelper = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (sMatcher.match(uri))
		{
		case STUDENTS:
			return db.query("t_student", projection, selection, selectionArgs, null, null, sortOrder);
		case STUDENT:
			long pid = ContentUris.parseId(uri);
			String where = TextUtils.isEmpty(selection) ? "sid=?" : selection + " and sid=?";
			String[] params = new String[]
			{ String.valueOf(pid) };
			if (!TextUtils.isEmpty(selection) && selectionArgs != null)
			{
				params = new String[selectionArgs.length + 1];
				for (int i = 0; i < selectionArgs.length; i++)
				{
					params[i] = selectionArgs[i];
				}
			}
			return db.query("t_student", projection, where, params, null, null, sortOrder);
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (sMatcher.match(uri))
		{
		case STUDENTS:
			count = db.update("t_student", values, selection, selectionArgs);
			break;
		case STUDENT:
			long sid = ContentUris.parseId(uri);
			String where = TextUtils.isEmpty(selection) ? "sid=?" : selection + " and sid=?";
			String[] params = new String[] { String.valueOf(sid) };
			if (!TextUtils.isEmpty(selection) && selectionArgs != null)
			{
				params = new String[selectionArgs.length + 1];
				for (int i = 0; i < selectionArgs.length; i++)
				{
					params[i] = selectionArgs[i];
				}
				params[selectionArgs.length + 1] = String.valueOf(sid);
			}
			count = db.update("t_student", values, where, params);
			break;
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
		return count;
	}
}
