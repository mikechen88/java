package com.example.a6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Append extends Activity implements OnClickListener{

	private EditText mTxtTimestamp = null;
	private EditText mTxtComments = null;
	private EditText mTxtMoney = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.append_view);
		
		 mTxtTimestamp = (EditText)findViewById(R.id.TEXT_TIMESTAMP);
	     
	        mTxtMoney = (EditText)findViewById(R.id.TEXT_MONEY);        
	        mTxtComments = (EditText)findViewById(R.id.TEXT_COMMENTS);
	        //listener
	        Button btnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
	        btnDiscard.setOnClickListener(this);
	        
	        //init save button
	        Button saveButton = (Button) findViewById(R.id.BTN_SUBMIT);
	        saveButton.setOnClickListener(new SaveClickListener());
	        
		
	}
	
	
	
	
	/*
	 * if all the patient parameters were entered correctly it attempts to add the patient to the db
	 */
	private class SaveClickListener implements View.OnClickListener
    {
    	public void onClick(View v) 
    	{	
    		String firstName = showHideNameError(R.id.TEXT_TIMESTAMP, R.id.TextView_ErrorFirstName);
    		String lastName = showHideNameError(R.id.TEXT_MONEY, R.id.TextView_ErrorLastName);
    		String comment = showHideNameError(R.id.TEXT_COMMENTS, R.id.TextView_comment);
    		
        	if( firstName == null || lastName == null)
        	{
        		Toast errorEntry = Toast.makeText(Append.this, 
        				"Settings were entered incorectly", Toast.LENGTH_LONG);
        		errorEntry.show();
        	}
        	else
        	{
        		long patientId =  new SQLHelper(Append.this).addPatientToDB(firstName, lastName,comment,"1");
        		
        		if(patientId == -1)
        		{
        			Toast successEntry = Toast.makeText(Append.this, "Error adding patient", Toast.LENGTH_LONG);
        			return;
        		}
        		
                 Toast successEntry = Toast.makeText(Append.this, "Patient successfully added", Toast.LENGTH_LONG);
	        	 successEntry.show();
               
        	}
        }
    }
	 
	/*
	 * Will test if the entry is empty
	 * if so it will display an error and if not it will clear the error
	 */
	private String showHideNameError(int editTextId, int errorTextViewId)
    {
    	EditText nameBox = (EditText)findViewById(editTextId);
    	String name = nameBox.getText().toString().trim();
    	
    	TextView nameError = (TextView)findViewById(errorTextViewId);
    	
    	if(name.length() <= 0)
    	{
    		/* show error if the name isn't at least 3 characters */
    		nameError.setText(R.string.error_blank);
    		return null;
    	}
    	else
    	{
    		/* clear error if the name isn't at least 3 characters */
    		nameError.setText("");
    		return name;
    	}	
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				this.finish();
				
			//startActivity(new Intent( this,MainActivity.class));
				break;
			}
		
		}
	}

}
