package com.example.a72.sql;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "data5.db";
	private static final int DATABASE_VERSION = 1;

	// Table name
	public static final String PEOPLE_TABLE = "agenda5";

	// people columns
	public static final String TIME = "time";
	public static final String TITLE = "title";
	public static final String COMMENT = "comment";
	public static final String FINISH = "finish";

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql_people = "create table " + PEOPLE_TABLE + "( 'id'"
				 + " integer primary key autoincrement, " + TIME
				+ " text not null, " + TITLE + " text not null, " + COMMENT
				+ " text not null, " + FINISH + " text , " + "unique (" + TIME
				+ ", " + TITLE + "));  ";

		db.execSQL(sql_people);

		String add_agenda = "   INSERT INTO "
				+ PEOPLE_TABLE
				+ " VALUES  (1,'2','shop  a',   'fruit','1'),(2,'3','shop  b',   'fruitss','1'), (3,'4','shop  c',   'seafood','1'),"+
				"  (4,'5','shop  d',   'vege','1'), (5,'6','shop  ddd',   'vegeeeeee','2');";
		db.execSQL(add_agenda);
	}

	// public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	public long onUpgrade(String id,String firstName, String lastName, String comment,
			String finish) {
		
		String[] args={id};

		ContentValues values = new ContentValues();
		
		
		values.put(SQLHelper.TIME, firstName);
		values.put(SQLHelper.TITLE, lastName);
		values.put(SQLHelper.COMMENT, comment);
		values.put(SQLHelper.FINISH, finish);
		long patientId = getWritableDatabase().update(PEOPLE_TABLE, values, "id=?",args);
		return patientId;
	}

	public long addPatientToDB(String firstName, String lastName,
			String comment, String finish) {
		ContentValues values = new ContentValues();
		values.put(SQLHelper.TIME, firstName);
		values.put(SQLHelper.TITLE, lastName);
		values.put(SQLHelper.COMMENT, comment);
		values.put(SQLHelper.FINISH, finish);

		long patientId = getWritableDatabase().insert(PEOPLE_TABLE, null,
				values);

		return patientId;
	}

	public ArrayList<Person> getUnfinish() {
		ArrayList<Person> people = new ArrayList<Person>();

		Cursor personCursor = getReadableDatabase().query(
				SQLHelper.PEOPLE_TABLE, new String[] { "*" }, "finish=?",new String[] { String.valueOf("1") }, null,
				null, null);


		
		
		int personId = -1;
		Person person;

		// cycle through each row
		while (personCursor.moveToNext()) {
			personId = personCursor.getInt(0);

			if (personId != -1) {
				person = new Person(personId, personCursor.getString(1),
						personCursor.getString(2), personCursor.getString(3),
						personCursor.getString(4));

				people.add(person); // add the patients to a list of patients
			}
		}

		return people;
	}
	
	public ArrayList<Person> getFinish() {
		ArrayList<Person> people = new ArrayList<Person>();

		Cursor personCursor = getReadableDatabase().query(
				SQLHelper.PEOPLE_TABLE, new String[] { "*" }, "finish=?",new String[] { String.valueOf("2") }, null,
				null, null);


		
		
		int personId = -1;
		Person person;

		// cycle through each row
		while (personCursor.moveToNext()) {
			personId = personCursor.getInt(0);

			if (personId != -1) {
				person = new Person(personId, personCursor.getString(1),
						personCursor.getString(2), personCursor.getString(3),
						personCursor.getString(4));

				people.add(person); // add the patients to a list of patients
			}
		}

		return people;
	}
	
	public ArrayList<Person> query(String key, String value) {
		ArrayList<Person> people = new ArrayList<Person>();

		Cursor personCursor = getReadableDatabase().query(
				SQLHelper.PEOPLE_TABLE, new String[] { "*" }, key+"=?",new String[] { value }, null,
				null, null);


		
		
		int personId = -1;
		Person person;

		// cycle through each row
		while (personCursor.moveToNext()) {
			personId = personCursor.getInt(0);

			if (personId != -1) {
				person = new Person(personId, personCursor.getString(1),
						personCursor.getString(2), personCursor.getString(3),
						personCursor.getString(4));

				people.add(person); // add the patients to a list of patients
			}
		}

		return people;
	}
	

	public void deletePeople() {
		long id = getWritableDatabase().delete(PEOPLE_TABLE, null, null);
	}
	
	public void deleteOne(String[] args){
		getWritableDatabase().delete(PEOPLE_TABLE, "id=?", args);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}