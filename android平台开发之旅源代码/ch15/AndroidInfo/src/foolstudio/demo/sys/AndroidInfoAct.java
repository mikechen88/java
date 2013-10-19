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
			printText("执行指令数："+counter.globalTotal() );
			printText("调用方法数："+counter.globalMethodInvocations() );
		}		

		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}

	//显示内存有关信息
	private void showMemoryInfo(MemoryInfo memInfo) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("dalvik虚拟机（KB）："+memInfo.dalvikPss+"\n");
		sb.append("原生堆（KB）："+memInfo.nativePss+"\n");
		sb.append("其他（KB）："+memInfo.otherPss);
		
		printText(sb.toString() );
	}

	//显示构建信息
	private void doAction() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("SDK版本："+Build.VERSION.SDK+"\n");
		sb.append("发布版本："+Build.VERSION.RELEASE+"\n");
		sb.append("内部版本："+Build.VERSION.INCREMENTAL+"\n");
		sb.append("包装："+Build.BOARD+"\n");
		sb.append("品牌："+Build.BRAND+"\n");
		sb.append("设备："+Build.DEVICE+"\n");
		sb.append("标签："+Build.DISPLAY+"\n");
		sb.append("指纹："+Build.FINGERPRINT+"\n");
		sb.append("主机："+Build.HOST+"\n");
		sb.append("标识："+Build.ID+"\n");
		sb.append("型号："+Build.MODEL+"\n");
		sb.append("产品："+Build.PRODUCT+"\n");
		sb.append("标记："+Build.TAGS+"\n");
		sb.append("时间戳："+FoolSysUtil.unixTimestamp2Str(Build.TIME)+"\n");
		sb.append("类型："+Build.TYPE+"\n");
		sb.append("用户："+Build.USER);		
		
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