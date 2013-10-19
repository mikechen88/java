package com.szy.provider;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	private static final String TAG = "MainActivity";
	private static final int ONE = 1;
	private static final int TWO = 2;
	private Button btnAdd1;
	private Button btnAdd2;
	private Button btnUpdate1;
	private Button btnUpdate2;
	private Button btnQuery1;
	private Button btnQuery2;
	private Button btnDel1;
	private Button btnDel2;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnDel1=(Button)findViewById(R.id.btnDel1);
		btnDel2=(Button)findViewById(R.id.btnDel2);
		btnAdd1=(Button)findViewById(R.id.btnAdd1);
		btnAdd2=(Button)findViewById(R.id.btnAdd2);
		btnUpdate1=(Button)findViewById(R.id.btnUpdate1);
		btnUpdate2=(Button)findViewById(R.id.btnUpdate2);
		btnQuery1=(Button)findViewById(R.id.btnQuery1);
		btnQuery2=(Button)findViewById(R.id.btnQuery2);
		
		btnAdd1.setOnClickListener(listener);
		btnAdd2.setOnClickListener(listener);
		btnUpdate1.setOnClickListener(listener);
		btnUpdate2.setOnClickListener(listener);
		btnQuery1.setOnClickListener(listener);
		btnQuery2.setOnClickListener(listener);
		btnDel1.setOnClickListener(listener);
		btnDel2.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btnAdd1:
				add(ONE);
				break;
			case R.id.btnAdd2:
				add(TWO);
				break;
			case R.id.btnUpdate1:
				update(ONE);
			    break;
			case R.id.btnUpdate2:
				update(TWO);
				break;
			case R.id.btnQuery1:
				query(ONE);
				break;
			case R.id.btnQuery2:
				query(TWO);
				break;
			case R.id.btnDel1:
				delete(ONE);
				break;
			case R.id.btnDel2:
				delete(TWO);
				break;
			default:
				break;
			}

		}

	};

	protected void delete(int type)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri url = null;
		String where = "";
		String[] selectionArgs = null;
		switch (type)
		{
		case ONE:
			url = Uri.parse("content://com.szy.provider.studentprovider/student");
			where = "sid in (?,?)";
			selectionArgs = new String[]
			{ "1", "2" };
			contentResolver.delete(url, where, selectionArgs);
			break;
		case TWO:
			url = Uri.parse("content://com.szy.provider.studentprovider/student/2");
			// where = "age = ?";
			// selectionArgs = new String[]{"23"};
			where = null;
			selectionArgs = null;
			contentResolver.delete(url, where, selectionArgs);
			break;
		}
	}

	protected void query(int type)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri url = null;
		String[] projection = new String[] {"sid","name","age"};
		String selection = "";
		String[] selectionArgs = null;
		String sortOrder = "";
		Cursor cursor = null;
		switch (type)
		{
		case ONE:
			url = Uri.parse("content://com.szy.provider.studentprovider/student");
			selection = "sid < ?";
			selectionArgs = new String[]{"3"};
			cursor =contentResolver.query(url, projection, selection, selectionArgs, sortOrder);
			while (cursor.moveToNext())
			{
				Log.i(TAG, "sid="+cursor.getInt(0)+"name="+cursor.getString(1)+"age="+cursor.getShort(2));
			}
			break;
		case TWO:
			url = Uri.parse("content://com.szy.provider.studentprovider/student/1");
			cursor =contentResolver.query(url, projection, selection, selectionArgs, sortOrder);
			while (cursor.moveToNext())
			{
				Log.i(TAG, "sid="+cursor.getInt(0)+"name="+cursor.getString(1)+"age="+cursor.getShort(2));
			}
			break;
		}
	}

	protected void update(int type)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri url = null;
		ContentValues values = new ContentValues();
		String where = "";
		String[] selectionArgs = null;
		switch (type)
		{
		case ONE:
			url = Uri.parse("content://com.szy.provider.studentprovider/student");
			values.put("name", "Updated");
			values.put("age", "23");
			where = "sid = ?";
			selectionArgs = new String[]{"1"};
			contentResolver.update(url, values, where, selectionArgs);
			break;
		case TWO:
			url = Uri.parse("content://com.szy.provider.studentprovider/student/2");
			values.put("name", "Updated");
			values.put("age", "23");
			where = "name = ?";
			selectionArgs = new String[]{"kuka"};
			contentResolver.update(url, values, where, selectionArgs);
			break;
		}
		
	}

	protected void add(int type)
	{
		ContentResolver contentResolver = getContentResolver();
		Uri url = null;
		ContentValues values = new ContentValues();
		switch (type)
		{
		case ONE:
			url = Uri.parse("content://com.szy.provider.studentprovider/student");
			values.put("name", "libai");
			values.put("age", "100");
			Log.i(TAG, contentResolver.insert(url, values).toString());
			break;
		case TWO:
			url = Uri.parse("content://com.szy.provider.studentprovider/student/1");
			values.put("name", "dufu");
			values.put("age", "101");
			Log.i(TAG, contentResolver.insert(url, values).toString());
			break;
		}
	}

}