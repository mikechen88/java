package com.szy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity
{
	private final static String TAG="MainActivity";
	private static final String CONTENT = "content";
	private Button secondBtn=null;
	private EditText txt = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt=(EditText)findViewById(R.id.txt);
		if (null!=savedInstanceState&&savedInstanceState.containsKey(CONTENT))
		{
			txt.setText(savedInstanceState.getString(CONTENT));
		}
		secondBtn=(Button)findViewById(R.id.secondBtn);
		secondBtn.setOnClickListener(listener);
		Log.i(TAG, "MainActivity-->onCreate");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.i(TAG, "MainActivity-->onDestroy");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.i(TAG, "MainActivity-->onPause");
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.i(TAG, "MainActivity-->onRestart");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.i(TAG, "MainActivity-->onResume");
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i(TAG, "MainActivity-->onStart");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.i(TAG, "MainActivity-->onStop");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.i(TAG, "MainActivity-->onSaveInstanceState");
		String content=txt.getText().toString();
		outState.putString(CONTENT, content);
	}

	private OnClickListener listener=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Button btn=(Button)v;
			Intent intent=new Intent();
			switch (btn.getId())
			{
			case R.id.secondBtn:
				intent.setClass(MainActivity.this, SecondActivity.class);
				break;
			}
			startActivity(intent);
		}
		
	};
	
}