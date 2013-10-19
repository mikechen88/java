package com.szy.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.szy.test.dao.PersonDAO;

public class MainActivity extends Activity
{
	private static final String TAG = "MainActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String str = test();
		PersonDAO p=null;
		p.add();
		int i = 10;
		int j = 100;
		int k = i + j;
		Log.i(TAG, str);
	}

	private String test()
	{
		int i = 10;
		int j = 100;
		int k = i + j;
		String str = "android" + k;
		return str;
	}
}