package foolstudio.demo.view;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ImageSwitcherDemoAct extends Activity implements OnClickListener,
	ViewSwitcher.ViewFactory{
	
	private static final int[] mImageIds = {
		R.drawable.cm,
		R.drawable.fq,
		R.drawable.qj,
		R.drawable.xj
	};
	
	private int mCurIndex = 0;
	private Random mRandomizer = null;
	//	
	private ImageButton mBtnNext = null;
	private ImageButton mBtnRandom = null;
	private ImageButton mBtnPrev = null;	
	//private EditText mTxtContents = null;	
	//
	private ImageSwitcher mSwitcher = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mRandomizer = new Random(System.currentTimeMillis() );
        
        mBtnNext = (ImageButton)findViewById(R.id.BTN_NEXT);
        mBtnRandom = (ImageButton)findViewById(R.id.BTN_RANDOM);
        mBtnPrev = (ImageButton)findViewById(R.id.BTN_PREV);
        //
        mSwitcher = (ImageSwitcher)findViewById(R.id.SWT_PANEL);
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

	//切换到前一张图片
	private void doPrev() {
		// TODO Auto-generated method stub
		mSwitcher.setImageResource(mImageIds[mCurIndex]);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		
		if(mCurIndex <= 0) {
			mCurIndex = (mImageIds.length-1);
		}
		else {
			mCurIndex--;	
		}
	}

	//切换到后一张图片
	private void doNext() {
		// TODO Auto-generated method stub
		mSwitcher.setImageResource(mImageIds[mCurIndex]);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		
		mCurIndex = ((mCurIndex+1)%mImageIds.length);	
	}

	private void doRandom() {
		// TODO Auto-generated method stub		
		int index = mRandomizer.nextInt(mImageIds.length);
		Log.d(this.getClass().getName(), "Current index " + mCurIndex);
		mSwitcher.setImageResource(mImageIds[index]);
		
		mCurIndex = ((index+1)%mImageIds.length);		
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView iv = new ImageView(this);
		final int padding = 16;
		
		iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iv.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT) );
		iv.setPadding(padding, padding, padding, padding);
		
		return (iv);
	}	

	//--------------------------------------------------------------------------
};