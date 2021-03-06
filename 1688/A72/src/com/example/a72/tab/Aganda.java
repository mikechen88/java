
package com.example.a72.tab;
import java.util.ArrayList;

import com.example.a72.R;
import com.example.a72.sql.Append;
import com.example.a72.sql.Lookup;
import com.example.a72.sql.Person;
import com.example.a72.sql.Record;
import com.example.a72.sql.Review;
import com.example.a72.sql.SQLHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Aganda extends Activity implements OnClickListener {

	Button review_btn, lookup_btn, about_btn, record_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_main);

		/*
		 * review_btn = (Button) findViewById(R.id.review); about_btn = (Button)
		 * findViewById(R.id.about); about_btn.setOnClickListener(this);
		 * review_btn.setOnClickListener(this);
		 */

		lookup_btn = (Button) findViewById(R.id.lookup);

		record_btn = (Button) findViewById(R.id.record);

		lookup_btn.setOnClickListener(this);

		record_btn.setOnClickListener(this);

		initAddRecord();
		initDeleteRecords();
		initPatientList();

	}

	@Override
	public void onResume() {
		super.onResume();
		initPatientList();
	}

	/*
	 * get the list of patients from the database cycle through them and add
	 * them to the list view
	 */
	private void initPatientList() {
		ArrayList<Person> people = new SQLHelper(this).getUnfinish();

		if (people.size() > 0) {
			// add the patients to the list view
			ListView personListView = (ListView) findViewById(R.id.ListView_People);
			PersonAdapter personAdapter = new PersonAdapter(this,
					R.layout.person_row, people);
			personListView.setAdapter(personAdapter);
			personListView.setOnItemClickListener(new FruitItemClickListener());
		}
	}

	private void clearListView() {
		ListView personListView = (ListView) findViewById(R.id.ListView_People);
		personListView.setAdapter(null);
	}

	private void initAddRecord() {
		Button addRecordTV = (Button) findViewById(R.id.append);
		addRecordTV.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Aganda.this, Append.class));
			}
		});
	}

	private void initDeleteRecords() {
		Button addRecordTV = (Button) findViewById(R.id.delete);
		addRecordTV.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SQLHelper sqlHelper = new SQLHelper(Aganda.this);
				sqlHelper.deletePeople();
				clearListView();
			}
		});
	}

	/*
	 * Adds each Patient from the array of patients into the adapter. Appling
	 * the patient_row layout file to each of them
	 */
	private class PersonAdapter extends ArrayAdapter<Person> {
		public PersonAdapter(Context context, int textViewResourceId,
				ArrayList<Person> people) {
			super(context, textViewResourceId, people);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.person_row, null);
			}

			final Person person = getItem(position);

			if (person != null) {
				TextView nameTV = (TextView) convertView
						.findViewById(R.id.TextView_Name);
				nameTV.setText(person.getName());
			}

			return convertView;
		}
	}

	private class FruitItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long id) {
			Person fruit = (Person) adapter.getItemAtPosition(position);

			Intent intent = new Intent(Aganda.this, Review.class);
			Bundle bundle = new Bundle();
			bundle.putInt("id", fruit.getId());
			bundle.putString("time", fruit.getTime());

			bundle.putString("title", fruit.getTitle());
			bundle.putString("comment", fruit.getComment());
			bundle.putString("finish", fruit.getFinish());
			intent.putExtras(bundle);
			startActivity(intent);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.lookup: {
			startActivity(new Intent(this, Lookup.class));
			break;
		}

		case R.id.record: {
			startActivity(new Intent(this, Record.class));
			break;
		}
		}
	}
	/*
	 * case R.id.about: { startActivity(new Intent(this, About.class)); break; }
	 * 
	 * 
	 * 
	 * case R.id.review: { startActivity(new Intent(this, Review.class)); break;
	 * 
	 * 
	 * 
	 * >>>>>>>>>>>>>>>>>>>> <TableRow>
	 * 
	 * <Button android:id="@+id/review" style="@style/h2"
	 * android:layout_width="wrap_content" android:layout_height="wrap_content"
	 * android:text="Review" />
	 * 
	 * <Button android:id="@+id/about" style="@style/h2"
	 * android:layout_width="wrap_content" android:layout_height="wrap_content"
	 * android:text="About" />
	 * 
	 * 
	 * </TableRow> }
	 */

}
