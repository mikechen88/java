package com.szy.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	private Button btnStartService;
	private Button btnStopService;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnStartService = (Button) findViewById(R.id.btnStartService);
		btnStopService = (Button) findViewById(R.id.btnStopService);
		btnStartService.setOnClickListener(listener);
		btnStopService.setOnClickListener(listener);
	}
	
	private OnClickListener listener=new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			Intent intent=new Intent(MainActivity.this, ExampleService.class);
			switch (v.getId())
			{
			case R.id.btnStartService:
				startService(intent);
				break;
			case R.id.btnStopService:
				stopService(intent);
				break;
			default:
				break;
			}
			
		}
	};
}