package com.szy.file;

import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.szy.file.service.FileService;

public class MainActivity extends Activity
{
	private Button btnSave;
	private Button btnRead;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnRead = (Button) findViewById(R.id.btnRead);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnRead.setOnClickListener(listener);
		btnSave.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			FileService service = new FileService();
			EditText editName = (EditText) findViewById(R.id.editName);
			EditText editContent = (EditText) findViewById(R.id.editContent);
			TextView txtResult = (TextView) findViewById(R.id.txtResult);
			switch (v.getId())
			{
			case R.id.btnSave:

				try
				{
					OutputStream outStream = MainActivity.this.openFileOutput(editName.getText().toString(), Context.MODE_APPEND);
					service.save(outStream, editContent.getText().toString());
					Toast.makeText(MainActivity.this, "±£´æ³É¹¦", Toast.LENGTH_LONG).show();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			case R.id.btnRead:
				try
				{
					InputStream inStream = MainActivity.this.openFileInput(editName.getText().toString());
					String content = service.read(inStream);
					txtResult.setText(content);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			}
		}
	};
}