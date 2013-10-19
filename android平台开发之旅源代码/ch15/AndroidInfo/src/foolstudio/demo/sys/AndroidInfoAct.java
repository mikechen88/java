package foolstudio.demo.sys;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AndroidInfoAct extends Activity implements OnClickListener {
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (Button)findViewById(R.id.BTN_INIT);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUninit = (Button)findViewById(R.id.BTN_UNINIT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnInit.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);  
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
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
		
		Debug.stopMethodTracing();		
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doInit() {
		// TODO Auto-generated method stub
		
		Debug.startMethodTracing("fool");
		//Debug.printLoadedClasses(Debug.SHOW_FULL_DETAIL);
		Debug.printLoadedClasses(Debug.SHOW_INITIALIZED);
		//Debug.printLoadedClasses(Debug.SHOW_CLASSLOADER);
		
		MemoryInfo memInfo = new MemoryInfo();
		Debug.getMemoryInfo(memInfo);
		showMemoryInfo(memInfo);
		
		Debug.InstructionCount counter = new Debug.InstructionCount();
		counter.resetAndStart();
		if(counter.collect() ) {
			printText("ִ��ָ������"+counter.globalTotal() );
			printText("���÷�������"+counter.globalMethodInvocations() );
		}		

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	//��ʾ�ڴ��й���Ϣ
	private void showMemoryInfo(MemoryInfo memInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("dalvik�������KB����"+memInfo.dalvikPss+"\n");
		sb.append("ԭ���ѣ�KB����"+memInfo.nativePss+"\n");
		sb.append("������KB����"+memInfo.otherPss);
		
		printText(sb.toString() );
	}

	//��ʾ������Ϣ
	private void doAction() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("SDK�汾��"+Build.VERSION.SDK+"\n");
		sb.append("�����汾��"+Build.VERSION.RELEASE+"\n");
		sb.append("�ڲ��汾��"+Build.VERSION.INCREMENTAL+"\n");
		sb.append("��װ��"+Build.BOARD+"\n");
		sb.append("Ʒ�ƣ�"+Build.BRAND+"\n");
		sb.append("�豸��"+Build.DEVICE+"\n");
		sb.append("��ǩ��"+Build.DISPLAY+"\n");
		sb.append("ָ�ƣ�"+Build.FINGERPRINT+"\n");
		sb.append("������"+Build.HOST+"\n");
		sb.append("��ʶ��"+Build.ID+"\n");
		sb.append("�ͺţ�"+Build.MODEL+"\n");
		sb.append("��Ʒ��"+Build.PRODUCT+"\n");
		sb.append("��ǣ�"+Build.TAGS+"\n");
		sb.append("ʱ�����"+FoolSysUtil.unixTimestamp2Str(Build.TIME)+"\n");
		sb.append("���ͣ�"+Build.TYPE+"\n");
		sb.append("�û���"+Build.USER);		
		
		printText(sb.toString() );
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