package com.example.a72;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class BaseActivity extends Activity{
	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	public static final String FIRST_NAME = "FIRST NAME";
	
	public static final String AVATAR = "AVATAR";
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flow_menu, menu);
		menu.findItem(R.id.settings).setIntent(new Intent(this,com.example.a72.a82.About.class));
		menu.findItem(R.id.options).setIntent(new Intent(this,SecondActivity.class));
		return true;
	}
}
