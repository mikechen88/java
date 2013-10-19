package com.example.a72.a82;

import com.example.a72.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity implements OnClickListener {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_view);

		Button btnBack = (Button) findViewById(R.id.BTN_RETURN);
		btnBack.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();

	}

}
