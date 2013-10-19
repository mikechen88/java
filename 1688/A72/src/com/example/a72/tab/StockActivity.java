package com.example.a72.tab;

import com.example.a72.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class StockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_activity);
		
		TextView name=(TextView)findViewById ( R.id.stockName2);
		TextView buyPrice=(TextView)findViewById ( R.id.buyPrice);
		
		TextView bidPrice=(TextView)findViewById ( R.id.bidPrice);
		
		TextView hands=(TextView)findViewById ( R.id.hands);
		
		TextView profits=(TextView)findViewById ( R.id.profits);
		
		
		Bundle bundle=getIntent().getExtras();
		
		String stockName=bundle.getString("stockName");
		String buy_Price=bundle.getString("buy");
		String bid_Price=bundle.getString("bit");
		String buy_hands=bundle.getString("hands");
		String profit=bundle.getString("profit");
		
		name.setText("Stock Name:"+stockName);
		buyPrice.setText("Buy Price:$ "+buy_Price);
		bidPrice.setText( "Bid Price:$ "+bid_Price);
		hands.setText("Total hands: "+buy_hands);
		profits.setText("Your profit:"+profit);
		
	}
}
