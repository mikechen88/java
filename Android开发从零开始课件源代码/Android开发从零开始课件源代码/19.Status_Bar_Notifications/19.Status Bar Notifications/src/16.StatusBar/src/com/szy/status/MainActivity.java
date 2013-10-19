package com.szy.status;

import android.app.Activity;
import android.app.NotificationManager;
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
		btnStartService.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(MainActivity.this, StatusService.class);
				startService(intent);
			}
		});
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(R.layout.main);
	}
	
	
}