package com.example.a72;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FourthActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourth);
		
		initMapButton();
	}
	
	public void initMapButton()
	{
		Button mapButton = (Button)findViewById(R.id.Button_click);
		mapButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				EditText locationET = (EditText)findViewById(R.id.EditText_Location);
				String locText = locationET.getText().toString();
				
				try 
				{
					Geocoder coder = new Geocoder(getApplicationContext());
					List<Address> addresses = coder.getFromLocationName(locText, 1);
					Iterator<Address> locations = addresses.iterator();
					
					if(locations.hasNext())
					{
						Address location = locations.next();
						double lat = location.getLatitude();
						double lon = location.getLongitude();
						
						String geoURIText = String.format("geo:%f,%f", lat, lon);
						Uri geoURI = Uri.parse(geoURIText);
						Intent geoIntent = new Intent(Intent.ACTION_VIEW, geoURI);
						startActivity(geoIntent);
					}
				}
				catch(Exception e)
				{
					
				}
			}
		});
	}
}


