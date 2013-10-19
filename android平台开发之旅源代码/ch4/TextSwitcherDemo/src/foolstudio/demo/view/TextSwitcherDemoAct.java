package foolstudio.demo.view;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class TextSwitcherDemoAct extends Activity implements OnClickListener,
	ViewSwitcher.ViewFactory {
	
	private static String[] mTitles = null;
	
	private int mCurIndex = 0;
	private Random mRandomizer = null;
	
	private ImageButton mBtnNext = null;
	private ImageButton mBtnRandom = null;
	private ImageButton mBtnPrev = null;	
	//private EditText mTxtContents = null;	
	//
	private TextSwitcher mSwitcher = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTitles = this.getResources().getStringArray(R.array.titles);
        mRandomizer = new Random(System.currentTimeMillis() );
        
        mBtnNext = (ImageButton)findViewById(R.id.BTN_NEXT);
        mBtnRandom = (ImageButton)findViewById(R.id.BTN_RANDOM);
        mBtnPrev = (ImageButton)findViewById(R.id.BTN_PREV);
        //
        mSwitcher = (TextSwitcher)findViewById(R.id.SWT_PANEL);
        mSwitcher.setFactory(this);
        
        mBtnNext.setOnClickListener(this);
        mBtnRandom.setOnClickListener(this);
        mBtnPrev.setOnClickListener(this);
        
        doNext();       
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_NEXT: {
				doNext();
				break;
			}	
			case R.id.BTN_RANDOM: {
				doRandom();
				break;
			}			
			case R.id.BTN_PREV: {
				doPrev();
				break;
			}		
		}
	}

	private void doPrev() {
		// TODO Auto-generated method stub		
		mSwitcher.setText(mTitles[mCurIndex]);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		
		if(mCurIndex <= 0) {
			mCurIndex = (mTitles.length-1);
		}
		else {
			mCurIndex--;	
		}
	}

	private void doNext() {
		// TODO Auto-generated method stub		
		mSwitcher.setText(mTitles[mCurIndex]);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		
		mCurIndex = ((mCurIndex+1)%mTitles.length);	
	}

	private void doRandom() {
		// TODO Auto-generated method stub		
		int index = mRandomizer.nextInt(mTitles.length);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		mSwitcher.setText(mTitles[index]);
		
		mCurIndex = ((index+1)%mTitles.length);			
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		TextView tv = new TextView(this);
		//设置重心位置
		tv.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
		//设置颜色
		tv.setTextColor(this.getResources().getColor(R.color.font_color) );
		//设置字体大小
        tv.setTextSize(this.getResources().getDimension(R.dimen.font_size) );
        
		return (tv);
	}	

	//--------------------------------------------------------------------------
};