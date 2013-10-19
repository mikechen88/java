package com.szy.listview;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.szy.listview.dao.PersonDAO;

/**
 * @author coolszy
 * @blog http://blog.csdn.net/coolszy
 */
public class PersonActivity extends Activity
{
	ListView lvPerson;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person);
		PersonDAO personDAO = new PersonDAO(this);
		Cursor cursor = personDAO.getPersons();
		lvPerson = (ListView) findViewById(R.id.lvPerson);
//		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.person_item, cursor, new String[]
		SimpleCursorAdapter adapter = new PersonAdapter(this, R.layout.person_item, cursor, 
										new String[]{ "_id", "pname", "pgender" }, 
										new int[]{ R.id.tvPid, R.id.tvPname, R.id.ivPgender });
		lvPerson.setAdapter(adapter);
		
		lvPerson.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				Toast.makeText(PersonActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
			}
		});
	}
}

class PersonAdapter extends SimpleCursorAdapter
{
	private Cursor mCursor;
	protected int[] mFrom;
	protected int[] mTo;

	private ViewBinder mViewBinder;

	public PersonAdapter(Context context, int layout, Cursor c, String[] from, int[] to)
	{
		super(context, layout, c, from, to);
		mCursor = c;
		mTo = to;
		findColumns(from);
		
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor)
	{
		final ViewBinder binder = mViewBinder;
		final int count = mTo.length;
		final int[] from = mFrom;
		final int[] to = mTo;

		for (int i = 0; i < count; i++)
		{
			final View v = view.findViewById(to[i]);
			if (v != null)
			{
				boolean bound = false;
				if (binder != null)
				{
					bound = binder.setViewValue(v, cursor, from[i]);
				}

				if (!bound)
				{
					String text = cursor.getString(from[i]);
					if (text == null)
					{
						text = "";
					}

					if (v instanceof TextView)
					{
						setViewText((TextView) v, text);
					} else if (v instanceof ImageView)
					{
						if (text.equals("ÄÐ"))
						{
							setViewImage((ImageView) v, String.valueOf(R.drawable.boy));
						}
						else
						{
							setViewImage((ImageView) v, String.valueOf(R.drawable.girl));
						}
						
					} else
					{
						throw new IllegalStateException(v.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
					}
				}
			}
		}
	}

	private void findColumns(String[] from)
	{
		if (mCursor != null)
		{
			int i;
			int count = from.length;
			if (mFrom == null || mFrom.length != count)
			{
				mFrom = new int[count];
			}
			for (i = 0; i < count; i++)
			{
				mFrom[i] = mCursor.getColumnIndexOrThrow(from[i]);
			}
		} else
		{
			mFrom = null;
		}
	}

}
