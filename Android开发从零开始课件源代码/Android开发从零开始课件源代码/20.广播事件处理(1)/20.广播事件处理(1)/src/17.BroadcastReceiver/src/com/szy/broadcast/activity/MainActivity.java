package com.szy.broadcast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	protected static final String ACTION = "com.szy.broadcast.ACTION";
	private Button btnBroadcast;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnBroadcast=(Button)findViewById(R.id.btnBroadcast);
		btnBroadcast.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent();
				intent.setAction(ACTION);
				sendBroadcast(intent);
			}
		});
	}
}