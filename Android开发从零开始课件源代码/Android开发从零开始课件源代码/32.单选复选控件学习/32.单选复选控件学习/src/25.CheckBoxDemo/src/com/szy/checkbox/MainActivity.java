package com.szy.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity
{
	private CheckBox cbJava;
	private CheckBox cbCSharp;
	private CheckBox cbAbap;
	private CheckBox cbPhp;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cbJava=(CheckBox)findViewById(R.id.cbJava);
		cbCSharp=(CheckBox)findViewById(R.id.cbCSharp);
		cbAbap=(CheckBox)findViewById(R.id.cbAbap);
		cbPhp=(CheckBox)findViewById(R.id.cbPhp);
		cbJava.setOnCheckedChangeListener(listener);
		cbCSharp.setOnCheckedChangeListener(listener);
		cbAbap.setOnCheckedChangeListener(listener);
		cbPhp.setOnCheckedChangeListener(listener);
	}
	private OnCheckedChangeListener listener = new OnCheckedChangeListener()
	{
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			if (buttonView.getId()==R.id.cbJava)
			{
				if (isChecked)
				{
					Toast.makeText(MainActivity.this, "你学过Java", Toast.LENGTH_LONG).show();
				}
			}
			else if (buttonView.getId()==R.id.cbCSharp)
			{
				if (isChecked)
				{
					Toast.makeText(MainActivity.this, "你学过C#", Toast.LENGTH_LONG).show();
				}
			}
			else if (buttonView.getId()==R.id.cbAbap)
			{
				if (isChecked)
				{
					Toast.makeText(MainActivity.this, "你学过Abap", Toast.LENGTH_LONG).show();
				}
			}
			else if (buttonView.getId()==R.id.cbPhp)
			{
				if (isChecked)
				{
					Toast.makeText(MainActivity.this, "你学过Php", Toast.LENGTH_LONG).show();
				}
			}
			
		}
	};
}