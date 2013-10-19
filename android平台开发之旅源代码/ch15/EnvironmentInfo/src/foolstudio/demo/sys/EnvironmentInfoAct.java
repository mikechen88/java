package foolstudio.demo.sys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EnvironmentInfoAct extends Activity implements OnClickListener {
	
	private Button mBtnRegister = null;
	private Button mBtnAction = null;
	private Button mBtnUnregister = null;	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnRegister = (Button)findViewById(R.id.BTN_INIT);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUnregister = (Button)findViewById(R.id.BTN_UNINIT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnRegister.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUnregister.setOnClickListener(this);  
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				doInit();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doAction();
				break;
			}			
			case R.id.BTN_UNINIT: {
				doUninit();
				break;
			}		
		}
	}

	private void doUninit() {
		// TODO Auto-generated method stub
	}

	private void doInit() {
		// TODO Auto-generated method stub
	}

	private void doAction() {
		// TODO Auto-generated method stub
		printText("数据文件夹："+Environment.getDataDirectory() );
		printText("下载缓存文件夹："+Environment.getDownloadCacheDirectory() );
		printText("外部存储文件夹："+Environment.getExternalStorageDirectory() );
		printText("外部存储状态："+
				  getStateDesc(Environment.getExternalStorageState() ) );
		printText("根文件夹："+Environment.getRootDirectory() );
	}
	
	//获取存储器状态描述
	private String getStateDesc(String state) {
		// TODO Auto-generated method stub
		if(state.equalsIgnoreCase(Environment.MEDIA_BAD_REMOVAL) ) {
			return ("错误移除");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_CHECKING) ) {
			return ("检测ing");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED) ) {
			return ("就绪");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY) ) {
			return ("只读");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_NOFS) ) {
			return ("不支持的文件系统");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_REMOVED) ) {
			return ("已移除");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_SHARED) ) {
			return ("共享");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTABLE) ) {
			return ("不能安装");
		}		
		else if(state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTED) ) {
			return ("没有安装");
		}			
		
		return (state);
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
};