package com.example.applicationflow;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RatingsActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ratings);
		
		showRatings();
	}

	private void showRatings()
	{
		XmlResourceParser ratingsXML = getResources().getXml(R.xml.ratings);
		int eventType = -1;
		
		try 
		{
			while(eventType != XmlResourceParser.END_DOCUMENT)
			{
				eventType = ratingsXML.next();
				
				//if it's not a start tag go to next interation of loop
				if(eventType != XmlResourceParser.START_TAG) continue;
				String tagName = ratingsXML.getName();
				if(!tagName.equals("rating")) continue;
				
				String movieName = ratingsXML.getAttributeValue(null, "moviename");
				String movieGenre = ratingsXML.getAttributeValue(null, "genre");
				String movieRating = ratingsXML.getAttributeValue(null, "rating");
				
				insertMovieRow(movieName, movieGenre, movieRating);
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void insertMovieRow(String movieName, String genre, String rating)
	{
		TableLayout table = (TableLayout)findViewById(R.id.TableLayout_Ratings);
		TableRow row = new TableRow(this);
		
		addTextToRow(row, movieName);
		addTextToRow(row, genre);
		addTextToRow(row, rating);
		table.addView(row);
	}
	
	public void addTextToRow(TableRow row, String text)
	{
		TextView textView = new TextView(this);
		textView.setText(text);
		row.addView(textView);
	}
}


















