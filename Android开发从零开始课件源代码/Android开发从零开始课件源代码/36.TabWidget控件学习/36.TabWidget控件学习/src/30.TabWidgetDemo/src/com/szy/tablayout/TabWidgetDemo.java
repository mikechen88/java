package com.szy.tablayout;

import android.app.TabActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

/**
 *coolszy 2011-7-25
 */
public class TabWidgetDemo extends TabActivity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabwidget);
		
		Resources res = getResources(); 
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec; 
		spec = tabHost.newTabSpec("Button").setIndicator("Button", res.getDrawable(R.drawable.ic_tab_artists)).setContent(R.id.btn);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("TextView").setIndicator("TextView", res.getDrawable(R.drawable.ic_tab_albums)).setContent(R.id.textView);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("EditText").setIndicator("EditText", res.getDrawable(R.drawable.ic_tab_songs)).setContent(R.id.editText);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener()
		{
			@Override
			public void onTabChanged(String tabId)
			{
				Toast.makeText(TabWidgetDemo.this, tabId, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
