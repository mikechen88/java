package foolstudio.demo.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ScrollViewDemoAct extends Activity implements OnCheckedChangeListener, 
														   OnSeekBarChangeListener, 
														   OnClickListener {
	private static final int QUIT_DLG = 0;		
	private static final int ABOUT_DLG = 1;
	private static final int JUMP_DLG = 2;
	//
	private static final int MSG_ID = 2010;
	//
	private static final int AUTO_STEP = 10;
	private static final long INTERVAL = 500L;
	//
	private AlertDialog mQuitDlg = null;
	private Dialog mAboutDlg = null;
	private Dialog mJumpDlg = null;
	//
	private ScrollView mMainView = null;
	//
	private Handler mMsgHandler = null;
	private Timer mTimer = null;
	private boolean mIsAuto = false;
	private MenuItem mMenuItem = null;
	//
	private RadioGroup mRgpSel = null;
	private RadioButton mBtnMid = null;
	private SeekBar mBarProgress = null;
	private Button mBtnDiscard = null;
	private Button mBtnOk = null;
	//
	private int mMaxScrollRange = 0;
	private int mCurPos = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMainView = (ScrollView)findViewById(R.id.MAIN_VIEW);
        mMainView.setSmoothScrollingEnabled(true);
        
        mMsgHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == MSG_ID) {
					doAutoScroll();
				}
				super.handleMessage(msg);
			}
        };       
    }    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//充实菜单
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	//注意：必须要调用超类的方法，否则无法实现意图回调
    	return (super.onCreateOptionsMenu(menu) );
    }
    
    //--------------------------------------------------------------------------
	//初始化对话框
    protected Dialog onCreateDialog(int id) {
        switch(id) {
	        case QUIT_DLG:
	        	return (initQuitDlg() ); 
	        case ABOUT_DLG:
	        	return (initAboutDlg() ); 
	        case JUMP_DLG:
	        	return (initJumpDlg() );	        	
	        default:
	            return null;
        }
    }     
    
    //初始化跳转对话框
	private Dialog initJumpDlg() {
		// TODO Auto-generated method stub
		mJumpDlg = new Dialog(this);		
		//LayoutInflater inflater = this.getLayoutInflater();
		LayoutInflater inflater = mJumpDlg.getLayoutInflater();
		View jumpView = inflater.inflate(R.layout.jump_view, null);
		//必须先填充，再获取其子组件
		mJumpDlg.setContentView(jumpView);
		mJumpDlg.setTitle("Jump setting");
		
		mRgpSel = (RadioGroup)jumpView.findViewById(R.id.RGP_SEL);
		mBtnMid = (RadioButton)jumpView.findViewById(R.id.BTN_MID);
		mBarProgress = (SeekBar)jumpView.findViewById(R.id.BAR_PROGRESS);
		mBtnDiscard = (Button)jumpView.findViewById(R.id.BTN_DISCARD);
		mBtnOk = (Button)jumpView.findViewById(R.id.BTN_OK);
		
		mRgpSel.setOnCheckedChangeListener(this);
		
		//
		mBarProgress.setOnSeekBarChangeListener(this);
		mBtnDiscard.setOnClickListener(this);
		mBtnOk.setOnClickListener(this);
    	
		return (mJumpDlg);
	}

	//初始化退出对话框
	private Dialog initQuitDlg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//提示信息
		builder.setMessage("Exit program?");
		builder.setCancelable(false);
		//是按钮
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ScrollViewDemoAct.this.finish();
			}				
		});
		//否按钮
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
				
		});
		
		mQuitDlg = builder.create();
        return (mQuitDlg);   
	}
	
	//初始化关于对话框
    private Dialog initAboutDlg() {
		// TODO Auto-generated method stub
    	mAboutDlg = new Dialog(this);
    	mAboutDlg.setContentView(R.layout.about_view);
    	mAboutDlg.setTitle("About Newspaper Reader");
    	
		return (mAboutDlg);
	}    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	if(mMaxScrollRange == 0) {
			//探测滚动最大范围
			mMaxScrollRange = getMaxScrollRange(); 
    	}
    	
    	switch(item.getItemId() ) {
	    	case R.id.MI_AUTO: {
	    		doAuto();
	    		mMenuItem = item;
	    		//
	    		updateMenu();	    		
	    		break;
	    	}  
	    	case R.id.MI_JUMP: {
	    		_doJump();
	    		break;
	    	}	    	
	    	case R.id.MI_ABOUT: {
	    		doAbout();
	    		break;
	    	}        	
	    	case R.id.MI_QUIT: {
	    		doQuit();
	    		break;
	    	}
    	}
    	
		return (super.onOptionsItemSelected(item) );    	
    }
    
    //获取最大滚动范围
	private int getMaxScrollRange() {
		// TODO Auto-generated method stub
		int delta = 0;
		
		mMainView.setVisibility(View.INVISIBLE);
		
		mMainView.fullScroll(ScrollView.FOCUS_DOWN);
		delta = mMainView.getScrollY(); //0
		Log.d("getMaxScrollRange", ""+delta);
		
		mMainView.fullScroll(ScrollView.FOCUS_UP);
		delta = mMainView.getScrollY(); //0
		Log.d("getMaxScrollRange", ""+delta);
		
		mMainView.fullScroll(ScrollView.FOCUS_DOWN);
		delta = mMainView.getScrollY(); //810
		Log.d("getMaxScrollRange", ""+delta);
		
		mMainView.fullScroll(ScrollView.FOCUS_UP);
		//delta = mMainView.getScrollY();
		//Log.d("getMaxScrollRange", ""+delta);		
		
		mMainView.setVisibility(View.VISIBLE);
		
		return (delta);
	}    
    
	//执行跳转
    private void _doJump() {
		// TODO Auto-generated method stub    	
    	showDialog(JUMP_DLG);
	}

    //自动滚动
	private void doAuto() {
		// TODO Auto-generated method stub
		if(!mIsAuto) {
	        mTimer = new Timer();
	        TimerTask task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mMsgHandler.sendEmptyMessage(MSG_ID);				
				}
	        };
	        
			mTimer.scheduleAtFixedRate(task, 0, INTERVAL);
			
			mIsAuto = true;
		}
		else {
			stopAuto();
		}
	}
	
	//停止自动滚动
	private void stopAuto() {
		mTimer.cancel();
		mTimer.purge();
		mTimer = null;		
		
		mIsAuto = false;
		updateMenu();
	}
	
	//更新菜单显示
	private void updateMenu() {
		// TODO Auto-generated method stub
		if(mIsAuto) {
			mMenuItem.setTitle("Stop");
		}
		else {
			mMenuItem.setTitle("Auto");
		}		
	}

	//自动滚动
	private void doAutoScroll() {
		// TODO Auto-generated method stub
		if(mCurPos <= mMaxScrollRange) {
			mCurPos += AUTO_STEP;
			mMainView.scrollTo(0, mCurPos);
		}
		else {
			stopAuto();
		}		
	}	

    private void doAbout() {
    	showDialog(ABOUT_DLG);    	
    }    
    
    private void doQuit() {
    	showDialog(QUIT_DLG);
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mBtnMid.setText(progress+"%");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		//Log.d(this.getClass().getName(), Integer.toHexString(checkedId) );
		this.mBarProgress.setEnabled(checkedId == R.id.BTN_MID);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				mJumpDlg.dismiss();
				break;
			}
			case R.id.BTN_OK: {
				doJump();
				mJumpDlg.dismiss();
				break;
			}
		}
	}

	//跳转核心函数
	private void doJump() {
		// TODO Auto-generated method stub
		//Log.d(this.getClass().getName(), 
		//		Integer.toHexString(mRgpSel.getCheckedRadioButtonId()) );		
		
		switch(mRgpSel.getCheckedRadioButtonId() ) {		
			case R.id.BTN_BEG: {
				mMainView.fullScroll(ScrollView.FOCUS_UP);
				mCurPos = 0;
				break;
			}
			case R.id.BTN_MID: {
				mCurPos = mMaxScrollRange*mBarProgress.getProgress()/100;
				mMainView.scrollTo(0, mCurPos);
				break;
			}
			case R.id.BTN_END: {
				mMainView.fullScroll(ScrollView.FOCUS_DOWN);
				mCurPos = mMaxScrollRange;
				break;
			}			
		}
	} 
	
	//--------------------------------------------------------------------------
};