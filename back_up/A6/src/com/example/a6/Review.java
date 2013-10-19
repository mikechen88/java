package com.example.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Review extends Activity implements OnClickListener{
	private TextView mPrevieTitle = null;
	private EditText mTxtTimestamp = null;
	private EditText mTxtComments = null;
	private EditText mTxtMoney = null;
	//°´Å¥
	private Button mBtnNext = null;
	private Button mBtnPrev = null;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.review_view);
	        
	       
	        mPrevieTitle = (TextView)findViewById(R.id.REVIEW_TITLE);
	        mTxtTimestamp = (EditText)findViewById(R.id.TEXT_TIMESTAMP);
	        mTxtComments = (EditText)findViewById(R.id.TEXT_COMMENTS);
	        mTxtMoney = (EditText)findViewById(R.id.TEXT_MONEY);        
	        
	        //listener
	        mBtnNext = (Button)findViewById(R.id.BTN_NEXT);
	        mBtnPrev = (Button)findViewById(R.id.BTN_PREV);
	        Button btnBack = (Button)findViewById(R.id.BTN_BACK);
	        mBtnNext.setOnClickListener(this);
	        mBtnPrev.setOnClickListener(this); 
	        btnBack.setOnClickListener(this); 
	        
	 }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				switch(v.getId() ) {
					case R.id.BTN_BACK: {
						this.finish();
						//startActivity(new Intent ( this, MainActivity.class));
						break;
					}		
					case R.id.BTN_NEXT: {
						break;
					}
					case R.id.BTN_PREV: {
						break;
					}
				}
	}

}
