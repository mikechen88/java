package com.example.applicationflow;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InstructionsActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
		
		populateInstructions();
	}
	
	private void populateInstructions()
	{
		InputStream file = getResources().openRawResource(R.raw.instructions);
		String textFromFile = getTextFromFile(file);
		
		TextView textView = (TextView)findViewById(R.id.TextView_Instructions);
		textView.setText(textFromFile);
	}
	
	private String getTextFromFile(InputStream file)
	{
		String data = "";
		InputStreamReader inputreader = new InputStreamReader(file);
        BufferedReader buffreader = new BufferedReader(inputreader);
		
		try {
			String line = "";
			
			 while (( line = buffreader.readLine()) != null) {
                    data += line + "\n";
                  }
		}
		catch(Exception e)
		{
			return "Sorry, technical trouble";
		}
		
		return data;
	}
}















