package com.example.a72.tab;

import java.util.ArrayList;

import com.example.a72.R;
import com.example.a72.tab.Stock;
import com.example.a72.tab.StockActivity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FirstViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_view);
		populateFruitView();
	}

	public void populateFruitView() {
		ListView listView = (ListView) findViewById(R.id.listview_stocks);
		// make stock adapter
		StockAdapter stockAdapter = new StockAdapter(this, R.layout.stock_row,
				getStockFromXML());
		// set adapter
		listView.setAdapter(stockAdapter);

		 listView.setOnItemClickListener(new StockClickListener());

	}

	private ArrayList<Stock> getStockFromXML() {
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		XmlResourceParser stockXML = getResources().getXml(R.xml.stock_listing);
		int eventType = -1;
		try {
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				eventType = stockXML.next();
				if (eventType != XmlResourceParser.START_TAG)
					continue;
				String tagName = stockXML.getName();
				if (!tagName.equals("stock"))
					continue;

				String stockName = stockXML.getAttributeValue(null, "name");
				String buyPrice = stockXML.getAttributeValue(null, "buy");
				String hands = stockXML.getAttributeValue(null, "hands");
				String bid = stockXML.getAttributeValue(null, "bid");
				
				Log.i("    stock name     ",stockName);
				Log.i("    price     ",buyPrice);
				Log.i("  hands    ",hands);
				Log.i("    stock name     ",bid);
				stockList.add(new Stock(stockName, buyPrice, bid, hands));

			}

		} catch (Exception e) {
			System.out.println("input wrong");
		}

		return stockList;

	}

	private class StockAdapter extends ArrayAdapter<Stock> {

		public StockAdapter(Context context, int textViewResourceId,
				ArrayList<Stock> fruits) {
			super(context, textViewResourceId, fruits);

		}

		/**
		 * position the position (index) convertView what's current now
		 * 
		 */
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				convertView = inflater.inflate(R.layout.stock_row, null);

			}

			Stock stock = getItem(position);
			if (stock != null) {

				TextView textview = (TextView) convertView
						.findViewById(R.id.textview_stockname);
				textview.setText(stock.getStockName());
			}

			return convertView;
		}

	}

	private class StockClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view,
				int position, long id) {
			Stock stock = (Stock) adapter.getItemAtPosition(position);

			Intent intent = new Intent(FirstViewActivity.this,
					StockActivity.class);
			Bundle bundle = new Bundle();

			bundle.putString("stockName", stock.getStockName());
			bundle.putString("buy", String.valueOf(stock.getBuyPrice()));
			bundle.putString("bit", String.valueOf(stock.getBidPrice()));
			bundle.putString("hands", String.valueOf(stock.getHands()));
			bundle.putString("profit", stock.getProfit());
			intent.putExtras(bundle);
			startActivity(intent);

		}
	}

}
