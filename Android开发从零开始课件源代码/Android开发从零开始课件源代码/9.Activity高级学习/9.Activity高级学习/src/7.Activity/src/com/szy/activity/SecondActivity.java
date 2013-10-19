package com.szy.activity;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
	}
}