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
		
		printText("已运行时间（ms）："+elapsed);
		printText("Pid: " + Process.myPid() );
		printText("Tid: " + tid  );
		printText("线程优先级: "+getPriorityDesc(Process.getThreadPriority(tid)));
		printText("Uid: " + uid);
		printText(Process.supportsProcesses()?"支持多进程":"不支持多进程");
		
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

	//获取优先级描述
	private String getPriorityDesc(int priority) {
		// TODO Auto-generated method stub
		switch(priority) {
			case Process.THREAD_PRIORITY_AUDIO: {
				return ("标准音频优先级");
			}
			case Process.THREAD_PRIORITY_BACKGROUND: {
				return ("标准后台优先级");
			}
			case Process.THREAD_PRIORITY_DEFAULT: {
				return ("标准优先级");
			}
			case Process.THREAD_PRIORITY_DISPLAY: {
				return ("标准显示优先级");
			}
			case Process.THREAD_PRIORITY_FOREGROUND: {
				return ("标准前台优先级");
			}
			case Process.THREAD_PRIORITY_LESS_FAVORABLE: {
				return ("少喜好优先级");
			}
			case Process.THREAD_PRIORITY_LOWEST: {
				return ("最低优先级");
			}
			case Process.THREAD_PRIORITY_MORE_FAVORABLE: {
				return ("多喜好优先级");
			}
			case Process.THREAD_PRIORITY_URGENT_AUDIO: {
				return ("重要音频优先级");
			}
			case Process.THREAD_PRIORITY_URGENT_DISPLAY : {
				return ("重要显示优先级");				
			}
		}
		
		return ("未定义优先级");
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