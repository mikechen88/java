package com.szy.listview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity
{
	
//	static final String[] COUNTRIES = new String[] { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba"};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		String[] countries = getResources().getStringArray(R.array.countries_array);
//		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, countries));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}