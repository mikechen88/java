package com.example.fruitlisting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		populateFruitView();
	}

	public void populateFruitView() {
		ListView listView = (ListView) findViewById(R.id.listview_fruits);
		// make fruit adapter
		FruitAdapter fruitAdapter = new FruitAdapter(this, R.layout.fruit_row,
				getFruitFromXML());
		// set adapter
		listView.setAdapter(fruitAdapter);

		listView.setOnItemClickListener(new FruitItemClickListener());

	}

	private ArrayList<Fruit> getFruitFromXML() {
		ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
		XmlResourceParser fruitXML = getResources().getXml(R.xml.fruit_listing);
		int eventType = -1;
		try {
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				eventType = fruitXML.next();
				if (eventType != XmlResourceParser.START_TAG)
					continue;
				String tagName = fruitXML.getName();
				if (!tagName.equals("fruit"))
					continue;

				String fruitName = fruitXML.getAttributeValue(null, "name");
				String fruitColor = fruitXML.getAttributeValue(null, "buy");

				fruitList.add(new Fruit(fruitName, fruitColor));

			}

		} catch (Exception e) {

		}

		return fruitList;

	}

	private class FruitAdapter extends ArrayAdapter<Fruit> {

		public FruitAdapter(Context context, int textViewResourceId,
				ArrayList<Fruit> fruits) {
			super(context, textViewResourceId, fruits);

		}

		/**
		 * position the position (index) convertView what's current now
		 * 
		 */
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				convertView = inflater.inflate(R.layout.fruit_row, null);

			}

			Fruit fruit = getItem(position);
			if (fruit != null) {
				TextView textview = (TextView) convertView
						.findViewById(R.id.textview_fruitname);
				textview.setText(fruit.getFruitName());
			}

			return convertView;
		}

	}

	private class FruitItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long id) {
			Fruit fruit = (Fruit) adapter.getItemAtPosition(position);

			Intent intent = new Intent(MainActivity.this, FruitActivity.class);
			intent.putExtra("stockName", fruit.getFruitName());
			intent.putExtra("buy", fruit.getColor());
			startActivity(intent);

		}

	}

}
