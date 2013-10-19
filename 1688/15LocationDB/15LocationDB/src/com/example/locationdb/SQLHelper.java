package com.example.locationdb;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "people.db";
	private static final int DATABASE_VERSION = 1;

	// Table name
	public static final String PEOPLE_TABLE = "people";
	
	// people columns
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql_people = "create table " + PEOPLE_TABLE + "( " + 
				PEOPLE_TABLE + " integer primary key autoincrement, " +
				FIRST_NAME + " text not null, " + 
				LAST_NAME + " text not null, " + 
				"unique (" + FIRST_NAME + ", " + LAST_NAME + "));";
		
		db.execSQL(sql_people);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	
	public long addPatientToDB(String firstName, String lastName)
	{
		ContentValues values = new ContentValues();
        values.put(SQLHelper.FIRST_NAME, firstName);
        values.put(SQLHelper.LAST_NAME, lastName);
        
        long patientId = getWritableDatabase().insert(PEOPLE_TABLE, null, values);
        
        return patientId;
	}
	
	public ArrayList<Person> getPeople()
	{
		ArrayList<Person> people = new ArrayList<Person>();
		
        Cursor personCursor = getReadableDatabase().query(SQLHelper.PEOPLE_TABLE, 
        		new String[] {"*"}, "", null, null, null, null);
        
        int personId = -1;
        Person person;
        
        //cycle through each row
		while(personCursor.moveToNext())
		{
			personId = personCursor.getInt(0);
					
			if(personId != -1)
			{
				person = new Person(personId, personCursor.getString(1), personCursor.getString(2));
				
				people.add(person);	//add the patients to a list of patients
			}
		}
		
		return people;
	}
	
	public void deletePeople()
	{
		long id = getWritableDatabase().delete(PEOPLE_TABLE,null, null);
	}
}