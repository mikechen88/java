package com.example.a72;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainTabActivity extends TabActivity
{
	private TabHost tabHost;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    tabHost = getTabHost();
	    createTab(FirstViewActivity.class, "Investment", R.drawable.first_tab);
	    createTab(SecondViewActivity.class, "Agenda", R.drawable.second_tab);
	    
	    tabHost.setCurrentTab(0);
	}
	
	public void createTab(Class activity, String label, int drawableId)
	{
		Intent intent = new Intent(this, activity);
		
		TabSpec spec = tabHost.newTabSpec(label);
		spec.setIndicator(label, getResources().getDrawable(drawableId));
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}












