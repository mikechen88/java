package com.example.locationdb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_menu);
    
        initAddRecord();
        initDeleteRecords();
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		initPatientList();
	}
	
	/*
	 * get the list of patients from the database
	 * cycle through them and add them to the list view
	 */
	private void initPatientList()
	{		
		ArrayList<Person> people = new SQLHelper(this).getPeople();
		
		if (people.size() > 0) 
		{
			//add the patients to the list view
			ListView personListView = (ListView)findViewById(R.id.ListView_People);
			PersonAdapter personAdapter = new PersonAdapter(this, R.layout.person_row, people);
			personListView.setAdapter(personAdapter);
		}
	}
	
	private void clearListView()
	{
		ListView personListView = (ListView)findViewById(R.id.ListView_People);
		personListView.setAdapter(null);
	}
     
    private void initAddRecord()
    {
    	Button addRecordTV = (Button)findViewById(R.id.Button_AddRecord);   	
    	addRecordTV.setOnClickListener(new OnClickListener(){
    		public void onClick(View v)
    		{
    			startActivity(new Intent(MenuActivity.this, Append.class));
    		}
    	});
    }
    
    private void initDeleteRecords()
    {
    	Button addRecordTV = (Button)findViewById(R.id.Button_DeleteRecords);   	
    	addRecordTV.setOnClickListener(new OnClickListener(){
    		public void onClick(View v)
    		{
    			SQLHelper sqlHelper = new SQLHelper(MenuActivity.this);
    			sqlHelper.deletePeople();
    			clearListView();
    		}
    	});
    }
    
    /*
     * Adds each Patient from the array of patients into the adapter. 
     * Appling the patient_row layout file to each of them
     */
    private class PersonAdapter extends ArrayAdapter<Person> 
    {
    	public PersonAdapter(Context context, int textViewResourceId, ArrayList<Person> people) 
    	{
			super(context, textViewResourceId, people);
		}
    	
    	@Override
		public View getView(int position, View convertView, ViewGroup parent) 
    	{
    		if (convertView == null) 
    		{
				LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.person_row, null);
			}
    		
    		final Person person = getItem(position);
    		
    		if(person != null)
    		{
    			TextView nameTV = (TextView) convertView.findViewById(R.id.TextView_Name);
    			nameTV.setText(person.getName());
    		}
    		
    		return convertView;
    	}
    }
}