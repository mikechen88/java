package com.example.a72.sql;

import com.example.a72.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Review extends Activity implements OnClickListener {
	private TextView mPrevieTitle = null;
	private EditText mTxtTimestamp = null;
	private EditText mTxtComments = null;
	private EditText mTxtTitle = null;
	// ��ť
	private Button update = null;
	private Button discard = null, delete;

	CheckBox check;
	
	int  id;
	String time, title , comment, finish;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_view);
		
		Bundle bundle = getIntent().getExtras();
        id=bundle.getInt("id");
        time=bundle.getString("time");
        title=bundle.getString("title");
        comment=bundle.getString("comment");
        finish=bundle.getString("finish");
        

		mPrevieTitle = (TextView) findViewById(R.id.REVIEW_TITLE);
		mTxtTimestamp = (EditText) findViewById(R.id.TEXT_TIMESTAMP);
		mTxtTitle = (EditText) findViewById(R.id.view_title);
		mTxtComments = (EditText) findViewById(R.id.TEXT_COMMENTS);
	check=(CheckBox)findViewById ( R.id.check_finish);
		
		mTxtTimestamp.setText(time);
		mTxtTitle.setText(title);
		mTxtComments.setText(comment);

		// listener
		update = (Button) findViewById(R.id.update);
		discard = (Button) findViewById(R.id.discard);
		delete = (Button) findViewById(R.id.delete);
		update.setOnClickListener(this);
		discard.setOnClickListener(this);
		delete.setOnClickListener(this);
		check.setOnClickListener(this);

	
        
        
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.update: {
			
			String rowId=String.valueOf(id);
			String time= mTxtTimestamp.getText().toString();
			String title=mTxtTitle.getText().toString();
			
			String comment=mTxtComments.getText().toString();
			
			long patientId =  new SQLHelper(Review.this).onUpgrade(rowId,time, title, comment,finish);
			this.finish();
			// startActivity(new Intent ( this, MainActivity.class));
			break;
		}
		case R.id.discard: {
			this.finish();
			break;
		}
		case R.id.delete: {
			String[]  rowId ={String.valueOf(id)};
		   new SQLHelper( Review.this).deleteOne(rowId);
		   this.finish();
			break;
		}
		case R.id.check_finish:
			
			if (check.isChecked()){
				finish="2";
			}else{
				finish="1";
			}
			
			break;
		}
	}

}
