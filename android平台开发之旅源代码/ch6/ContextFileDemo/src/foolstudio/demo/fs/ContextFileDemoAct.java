package foolstudio.demo.fs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContextFileDemoAct extends Activity implements OnClickListener {
	
	private final String PRIVATE_FILE = "ContextFileDemoAct.pri";
	
	private Button mBtnRaw = null;
	private Button mBtnXml = null;
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnRaw = (Button)findViewById(R.id.BTN_INIT);
        mBtnXml = (Button)findViewById(R.id.BTN_ACTION);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnRaw.setOnClickListener(this);
        mBtnXml.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
				try {
					doWrite();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				try {
					doRead();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}		
		}
	}

	//写文件
	private void doWrite() throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fos = this.openFileOutput(PRIVATE_FILE, 
												   Context.MODE_PRIVATE);
		PrintWriter pr = new PrintWriter(fos);
		
		String contents = this.getClass().getName();
		
		pr.println(contents);
		printText("Wrote '"+contents + "'");
		printText(" to " + PRIVATE_FILE);
		
		pr.flush();
		pr.close();
		fos.close();
	}

	//读取文件
	private void doRead() throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = this.openFileInput(PRIVATE_FILE);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis) );
		String line = null;
		
		printText("Contents of " + PRIVATE_FILE+":");
		
		while((line=br.readLine()) != null) {
			printText(line);
		}
		
		br.close();
		fis.close();
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	

	//--------------------------------------------------------------------------
};