package com.szy.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentActivity extends Activity
{
	protected static final String TAG = "IntentActivity";
	
	private Button btnStartNormalService;
	private Button btnStartIntentService;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent);
		btnStartNormalService = (Button) findViewById(R.id.btnStartNormalService);
		btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);
		btnStartNormalService.setOnClickListener(listener);
		btnStartIntentService.setOnClickListener(listener);
	}
	
	private OnClickListener listener=new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			Intent intent;
			switch (v.getId())
			{
			case R.id.btnStartNormalService:
				intent=new Intent(IntentActivity.this, MyService.class);
				Log.i(TAG, "主线程ID："+Thread.currentThread().getId());
				startService(intent);
				break;
			case R.id.btnStartIntentService:
				intent=new Intent(IntentActivity.this, ExampleIntentService.class);
				Log.i(TAG, "主线程ID："+Thread.currentThread().getId());
				startService(intent);
				break;
			default:
				break;
			}
			
		}
	};
}
