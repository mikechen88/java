package com.szy.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity
{
	private RadioGroup rgGender;
	private RadioGroup rgQuestion;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		rgGender=(RadioGroup)findViewById(R.id.rgGender);
		rgGender.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if (checkedId == R.id.rbBoy)
				{
					Toast.makeText(MainActivity.this, "男孩", Toast.LENGTH_LONG).show();
				}
				else if (checkedId == R.id.rbGirl) 
				{
					Toast.makeText(MainActivity.this, "女孩", Toast.LENGTH_LONG).show();
				}
			}
		});
		rgQuestion=(RadioGroup)findViewById(R.id.rgQuestion);
		rgQuestion.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if (checkedId == R.id.rbYes)
				{
					Toast.makeText(MainActivity.this, "毕业了", Toast.LENGTH_LONG).show();
				}
				else if (checkedId == R.id.rbNo) 
				{
					Toast.makeText(MainActivity.this, "没有毕业", Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
}