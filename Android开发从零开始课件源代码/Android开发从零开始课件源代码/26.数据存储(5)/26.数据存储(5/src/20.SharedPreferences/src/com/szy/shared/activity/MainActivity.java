package com.szy.shared.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private Button btnSave;
	private Button btnShow;
	private EditText editName;
	private EditText editAge;
	private TextView txtResult;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnShow = (Button) findViewById(R.id.btnShow);
		editName = (EditText) findViewById(R.id.editName);
		editAge = (EditText) findViewById(R.id.editAge);
		txtResult=(TextView)findViewById(R.id.txtResult);
		btnSave.setOnClickListener(listener);
		btnShow.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			SharedPreferences pres = MainActivity.this.getSharedPreferences("kuka", Context.MODE_PRIVATE);
			switch (v.getId())
			{
			case R.id.btnSave:
				Editor editor = pres.edit();
				editor.putString("name", editName.getText().toString());
				editor.putInt("age", Integer.valueOf(editAge.getText().toString()));
				editor.commit();
				Toast.makeText(MainActivity.this, "±£´æ³É¹¦", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.btnShow:
				String name = pres.getString("name", "NO");
				int age = pres.getInt("age", 0);
				String bir = pres.getString("bir", "NO");
				txtResult.setText("Name="+name+";age="+age+";bir="+bir);
				break;
			}
		}
	};
}