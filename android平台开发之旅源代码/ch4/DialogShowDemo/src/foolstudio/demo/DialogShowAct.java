package foolstudio.demo;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DialogShowAct extends Activity implements OnClickListener {
	static final int ALERT_DLG = 0;	
	static final int PROGRESS_DLG = 1;
	static final int DATE_PICKER_DLG = 2;
	static final int TIME_PICKER_DLG = 3;
	static final int CUSTOM_DLG = 4;
	
	private AlertDialog mAlertDlg = null;
	private ProgressThread mProgressThd = null;
	private ProgressDialog mProgressDlg = null;
	private DatePickerDialog mDatePickerDlg = null;
	private TimePickerDialog mTimePickerDlg = null;
	private Dialog mCustomDlg = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //获取显示组件对象
        TextView txtAlert = (TextView)findViewById(R.id.txtAlert);
        TextView txtProgress = (TextView)findViewById(R.id.txtProgress);
        TextView txtDatePicker = (TextView)findViewById(R.id.txtDatePicker);
        TextView txtTimePicker = (TextView)findViewById(R.id.txtTimePicker);
        TextView txtCustom = (TextView)findViewById(R.id.txtCustom);
        
        //设置事件响应侦听
        txtAlert.setOnClickListener(this);
        txtProgress.setOnClickListener(this);
        txtDatePicker.setOnClickListener(this);
        txtTimePicker.setOnClickListener(this);
        txtCustom.setOnClickListener(this);
    }
    
    //--------------------------------------------------------------------------
	//初始化对话框
    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
	        case ALERT_DLG:
	        	return (initAlertDlg() );
	        case PROGRESS_DLG:
	        	return (initProgressDlg() );
	        case DATE_PICKER_DLG:
	        	return (initDatePickerDlg() );
	        case TIME_PICKER_DLG:
	        	return (initTimePickerDlg() );    
	        case CUSTOM_DLG:
	        	return (initCustomDlg() );          	
	        default:
	            return null;
        }
    }
    
    //-------------------------------------------------------------------------
    //初始化提示对话框
    private Dialog initAlertDlg() {
		AlertDialog.Builder builder = new AlertDialog.Builder(DialogShowAct.this);
		//提示信息
		builder.setMessage("Exit program?");
		builder.setCancelable(false);
		//是按钮
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				DialogShowAct.this.finish();
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
		mAlertDlg = builder.create();
        return (mAlertDlg);    	
    }
    
    //--------------------------------------------------------------------------
    //初始化进度对话框
    private Dialog initProgressDlg() {
		mProgressDlg = new ProgressDialog(DialogShowAct.this);
		mProgressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//mProgressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDlg.setMessage("Loading...");
        mProgressThd = new ProgressThread(mHandler);
        mProgressThd.start();
        return (mProgressDlg);    	
    }
    
    //--------------------------------------------------------------------------
	//进度对话框消息处理器变量
    final Handler mHandler = new Handler() {
    	
    	//----------------------------------------------------------------------
    	//Handler的消息回调函数（消息在Thread中发送，在主线程中处理）
        public void handleMessage(Message msg) {
        	//从线程中发送出来的消息
            int curVal = msg.getData().getInt("CUR_VAL");
            mProgressDlg.setProgress(curVal);
            
            if (curVal >= ProgressThread.MAX_VAL){
                dismissDialog(PROGRESS_DLG);
                mProgressThd.setState(ProgressThread.STATE_DONE);
            }
        }
    };
    
    //--------------------------------------------------------------------------
    //初始化日期选择对话框
    private Dialog initDatePickerDlg() {
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
    										Locale.CHINA);
    	DatePickerDialog.OnDateSetListener listener =
    		new DatePickerDialog.OnDateSetListener() {
    			@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					Log.i("INFO", ""+year+"-"+(monthOfYear+1)+"-"+dayOfMonth);						
				}
    	};
	
    	mDatePickerDlg = new DatePickerDialog(DialogShowAct.this, listener,
    			cal.get(Calendar.YEAR),
    			cal.get(Calendar.MONTH),
    			cal.get(Calendar.DAY_OF_MONTH) );
    	
    	return (mDatePickerDlg);
    }
    
    //--------------------------------------------------------------------------
    //初始化时间选择对话框
    private Dialog initTimePickerDlg() {
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), 
    										Locale.CHINA);
    	TimePickerDialog.OnTimeSetListener listener = 
    		new TimePickerDialog.OnTimeSetListener() {    		
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay,
						int minute) {
					// TODO Auto-generated method stub
					Log.i("INFO", ""+hourOfDay+":"+minute);
				}
    	};
    	
    	mTimePickerDlg = new TimePickerDialog(DialogShowAct.this, listener ,
    			cal.get(Calendar.HOUR_OF_DAY),
    			cal.get(Calendar.MINUTE),
    			true); //24小时制
    	
    	return (mTimePickerDlg);    	
    }    
    
    //--------------------------------------------------------------------------    
    private Dialog initCustomDlg() {
    	mCustomDlg = new Dialog(DialogShowAct.this);

    	mCustomDlg.setContentView(R.layout.custom_dlg);
    	mCustomDlg.setTitle("Custom Dialog");

    	return (mCustomDlg);
    }

    //--------------------------------------------------------------------------    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.txtAlert: {
				showDialog(ALERT_DLG);
				break;
			}
			case R.id.txtProgress: {
				showDialog(PROGRESS_DLG);
				break;
			}
			case R.id.txtDatePicker: {
				showDialog(DATE_PICKER_DLG);
				break;
			}
			case R.id.txtTimePicker: {
				showDialog(TIME_PICKER_DLG);
				break;
			}
			case R.id.txtCustom: {
				showDialog(CUSTOM_DLG);
				break;
			}			
		}
	}
  
    //--------------------------------------------------------------------------
	//进度线程
	class ProgressThread extends Thread {
        public final static int STATE_DONE = 0; //结束
        public final static int STATE_RUNNING = 1; //执行中
        private Handler mHandler = null;        
        private int mState = 0;
        private int mCurVal = 0;
        public final static int MAX_VAL = 100;
       
        ProgressThread(Handler handler) {
            mHandler = handler;
        }
       
        public void run() {
            mState = STATE_RUNNING;   

            while (mState == STATE_RUNNING) {
                try {
                    Thread.sleep(100);
                } 
                catch (InterruptedException e) {
                	e.printStackTrace();
                }
                
                //Activity与线程通信的接口：消息
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putInt("CUR_VAL", mCurVal);
                msg.setData(b);

                //发送消息给进度对话框消息处理器
                mHandler.sendMessage(msg);
                mCurVal++;
            }
        }
        
        //改变状态
        public void setState(int state) {
            mState = state;
        }
    };
	
    //--------------------------------------------------------------------------	
};