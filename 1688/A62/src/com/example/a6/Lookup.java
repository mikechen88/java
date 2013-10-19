package com.example.a6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Lookup extends Activity implements OnClickListener {

	
	public static final String EXTRA_NAME = "RECORD_SET";
	
	private Spinner mSpnColumns = null;
	private Spinner mSpnOperators = null;
	private EditText mTxtValue = null;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup_view);
        
        mSpnColumns = (Spinner)findViewById(R.id.SPN_COLUMNS);
        mSpnOperators = (Spinner)findViewById(R.id.SPN_OPERATORS);
        mTxtValue = (EditText)findViewById(R.id.TEXT_QUERY_VALUE);        
        
        //listener
        Button btnQuery = (Button)findViewById(R.id.BTN_QUERY);
        Button btnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
        btnQuery.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				this.finish();
				break;
			}
			case R.id.BTN_QUERY: {
			
				break;
			}
		}
	}
	
}