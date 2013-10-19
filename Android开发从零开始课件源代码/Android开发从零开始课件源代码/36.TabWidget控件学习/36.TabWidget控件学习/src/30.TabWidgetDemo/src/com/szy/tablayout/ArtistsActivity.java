package com.szy.tablayout;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ArtistsActivity extends ListActivity
{
	
	static final String[] ARTISTS = new String[] { "≈£ƒÃøß∑»", "”Ùø…Œ®", "¡÷∑≤", "œÙ—«–˘", "Ã’Ü¥", "π‚¡º"};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, ARTISTS));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}