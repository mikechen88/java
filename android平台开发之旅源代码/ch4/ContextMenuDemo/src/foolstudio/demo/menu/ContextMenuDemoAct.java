package foolstudio.demo.menu;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.EditText;

public class ContextMenuDemoAct extends Activity {
	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        this.registerForContextMenu(mTxtContents);
    }

    //清除文本
	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub		
		switch(item.getItemId() ) {
			case R.id.MI_PASTE: {
				printText(getTextFromClipboard() );
				break;
			}
			case R.id.MI_TIMESTAMP: {
				printText(getDateTimeString() );
				break;
			}
			case R.id.MI_CLEAR: {
				clearText();
				break;
			}
		}
		return super.onContextItemSelected(item);
	}
	
	//获取剪贴板文本
	private String getTextFromClipboard() {
		// TODO Auto-generated method stub
		ClipboardManager service = (ClipboardManager)
			(this.getSystemService(Context.CLIPBOARD_SERVICE) );
		return(service.getText().toString() );
	}

	//-------------------------------------------------------------------------	
	//获取系统日期时间字符串
	public static String getDateTimeString() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
											Locale.CHINA);
		StringBuffer buffer = new StringBuffer();

		buffer.append(cal.get(Calendar.YEAR) + "/" +
			(cal.get(Calendar.MONTH) + 1) + "/" +
			cal.get(Calendar.DAY_OF_MONTH) + " " +
			cal.get(Calendar.HOUR_OF_DAY) + ":" +
			cal.get(Calendar.MINUTE) + ":" +
			cal.get(Calendar.SECOND) );

		return (buffer.toString() );
	}	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		this.getMenuInflater().inflate(R.menu.text_context_menu, menu);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}	

	//--------------------------------------------------------------------------
};