package com.example.a6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Record extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_view);
		
	
	            
	        
	        //listener
	        Button go_back = (Button)findViewById(R.id.go_home);
	     
	        go_back.setOnClickListener(this);
	        
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.finish();
	}

}
