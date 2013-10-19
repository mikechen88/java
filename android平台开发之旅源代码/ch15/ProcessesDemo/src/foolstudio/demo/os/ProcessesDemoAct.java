package foolstudio.demo.os;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.os.Process;

public class ProcessesDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	//
	private ActivityManager mService = null;
	
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
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	private void doInit() {
		// TODO Auto-generated method stub
		mService = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	private void doAction() {
		// TODO Auto-generated method stub
		long elapsed = Process.getElapsedCpuTime();
		int tid = Process.myTid();
		int uid = Process.myUid();
		
		printText("������ʱ�䣨ms����"+elapsed);
		printText("Pid: " + Process.myPid() );
		printText("Tid: " + tid  );
		printText("�߳����ȼ�: "+getPriorityDesc(Process.getThreadPriority(tid)));
		printText("Uid: " + uid);
		printText(Process.supportsProcesses()?"֧�ֶ����":"��֧�ֶ����");
		
		List<RunningAppProcessInfo> processes = mService.getRunningAppProcesses();
		
		if(processes != null) {
			Iterator<RunningAppProcessInfo> iterator = processes.iterator();
			
			printText(" PID\t Process");		
			while(iterator.hasNext() ) {
				RunningAppProcessInfo info = iterator.next();				
				printText(info.pid+"\t"+info.processName);
			}
		}
		
		/*
		Process.getGidForName(name);
		Process.getThreadPriority(tid);
		Process.getUidForName(name);
		*/
	}

	//��ȡ���ȼ�����
	private String getPriorityDesc(int priority) {
		// TODO Auto-generated method stub
		switch(priority) {
			case Process.THREAD_PRIORITY_AUDIO: {
				return ("��׼��Ƶ���ȼ�");
			}
			case Process.THREAD_PRIORITY_BACKGROUND: {
				return ("��׼��̨���ȼ�");
			}
			case Process.THREAD_PRIORITY_DEFAULT: {
				return ("��׼���ȼ�");
			}
			case Process.THREAD_PRIORITY_DISPLAY: {
				return ("��׼��ʾ���ȼ�");
			}
			case Process.THREAD_PRIORITY_FOREGROUND: {
				return ("��׼ǰ̨���ȼ�");
			}
			case Process.THREAD_PRIORITY_LESS_FAVORABLE: {
				return ("��ϲ�����ȼ�");
			}
			case Process.THREAD_PRIORITY_LOWEST: {
				return ("������ȼ�");
			}
			case Process.THREAD_PRIORITY_MORE_FAVORABLE: {
				return ("��ϲ�����ȼ�");
			}
			case Process.THREAD_PRIORITY_URGENT_AUDIO: {
				return ("��Ҫ��Ƶ���ȼ�");
			}
			case Process.THREAD_PRIORITY_URGENT_DISPLAY : {
				return ("��Ҫ��ʾ���ȼ�");				
			}
		}
		
		return ("δ�������ȼ�");
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