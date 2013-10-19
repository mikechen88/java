package com.szy.listview;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

import com.szy.listview.dao.PersonDAO;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class PersonDAOTest extends AndroidTestCase
{

	PersonDAO personDAO = null;
	
	public void testGetPersonsIntInt()
	{
		personDAO = new PersonDAO(getContext());
		personDAO.getPersons(0, 5);
	}

	public void testGetPersons()
	{
		personDAO = new PersonDAO(getContext());
		Cursor cursor = personDAO.getPersons();
		while(cursor.moveToNext())
		{
			Log.i("MainActivity", cursor.getString(1));
		}
	}

	public void testGetCount()
	{
		personDAO = new PersonDAO(getContext());
		long count = personDAO.getCount();
		Log.i("MainActivity", count+"");
		
	}
}
