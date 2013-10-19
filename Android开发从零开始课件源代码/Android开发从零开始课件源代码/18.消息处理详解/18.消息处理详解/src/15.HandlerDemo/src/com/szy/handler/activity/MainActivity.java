package com.szy.handler.activity;

import com.szy.handler.service.ExampleService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	private Button btnStartService;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnStartService=(Button)findViewById(R.id.btnStartService);
		btnStartService.setOnClickListener(listener);
	}
	private OnClickListener listener=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.btnStartService:
				Intent intent=new Intent(MainActivity.this, ExampleService.class);
				startService(intent);
				break;

			default:
				break;
			}
		}
	};
}