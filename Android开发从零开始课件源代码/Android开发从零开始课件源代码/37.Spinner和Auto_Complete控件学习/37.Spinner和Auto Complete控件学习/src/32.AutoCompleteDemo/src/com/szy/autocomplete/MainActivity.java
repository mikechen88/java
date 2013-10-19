package com.szy.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity
{
	static final String[] COUNTRIES = new String[] {
		  "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
		  "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi","ÄãºÃ","ÄãºÃÂð"};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES);
	    textView.setAdapter(adapter);
	}
}