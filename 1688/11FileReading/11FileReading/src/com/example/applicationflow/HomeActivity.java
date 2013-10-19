package com.example.applicationflow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		initChangeScreenButton(R.id.Button_Instructions, InstructionsActivity.class);
		initChangeScreenButton(R.id.Button_Ratings, RatingsActivity.class);
	}
	
	private void initChangeScreenButton(int buttonId, final Class destination)
	{
		Button button = (Button)findViewById(buttonId);
		button.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				startActivity(new Intent(HomeActivity.this, destination));
			}
		});
	}
}
















