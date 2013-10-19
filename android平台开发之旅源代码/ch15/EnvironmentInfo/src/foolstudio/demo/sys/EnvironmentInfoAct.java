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
		printText("�����ļ��У�"+Environment.getDataDirectory() );
		printText("���ػ����ļ��У�"+Environment.getDownloadCacheDirectory() );
		printText("�ⲿ�洢�ļ��У�"+Environment.getExternalStorageDirectory() );
		printText("�ⲿ�洢״̬��"+
				  getStateDesc(Environment.getExternalStorageState() ) );
		printText("���ļ��У�"+Environment.getRootDirectory() );
	}
	
	//��ȡ�洢��״̬����
	private String getStateDesc(String state) {
		// TODO Auto-generated method stub
		if(state.equalsIgnoreCase(Environment.MEDIA_BAD_REMOVAL) ) {
			return ("�����Ƴ�");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_CHECKING) ) {
			return ("���ing");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED) ) {
			return ("����");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY) ) {
			return ("ֻ��");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_NOFS) ) {
			return ("��֧�ֵ��ļ�ϵͳ");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_REMOVED) ) {
			return ("���Ƴ�");
		}
		if(state.equalsIgnoreCase(Environment.MEDIA_SHARED) ) {
			return ("����");
		}
		else if(state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTABLE) ) {
			return ("���ܰ�װ");
		}		
		else if(state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTED) ) {
			return ("û�а�װ");
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