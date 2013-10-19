package foolstudio.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipperDemoAct extends Activity implements OnClickListener {
	
	private static final int[] mDrawableIds = {
		R.drawable.box,
		R.drawable.bus,
		R.drawable.chain,
		R.drawable.flower,
		R.drawable.girl,
		R.drawable.grass,
		R.drawable.house,
		R.drawable.knob,
		R.drawable.label,
		R.drawable.lake,
		R.drawable.lake_tree,
		R.drawable.light,
		R.drawable.root,
		R.drawable.roy,
		R.drawable.scale,
		R.drawable.screw,
		R.drawable.snow,
		R.drawable.tiger,
		R.drawable.window,
		R.drawable.woman
	};
	
	private ImageButton mBtnInit = null;
	private ImageButton mBtnUninit = null;	
	private ViewFlipper mContentFlipper = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (ImageButton)findViewById(R.id.BTN_INIT);
        mBtnUninit = (ImageButton)findViewById(R.id.BTN_UNINIT);
        //
        mContentFlipper = (ViewFlipper)findViewById(R.id.CONTENTS_PANEL);
        initFlipper();
        
        mBtnInit.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);  
    }
	
	private void initFlipper() {
		// TODO Auto-generated method stub
		final int padding = 8;
		
		for(int i = 0; i < mDrawableIds.length; ++i) {
			ImageView iv = new ImageView(this);
			iv.setImageResource(mDrawableIds[i]);
			iv.setScaleType(ImageView.ScaleType.CENTER);
			iv.setPadding(padding, padding, padding, padding);
			iv.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT) );
			mContentFlipper.addView(iv);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				doInit();
				break;
			}		
			case R.id.BTN_UNINIT: {
				doUninit();
				break;
			}		
		}
	}
	
	//开始滑动
	private void doInit() {
		// TODO Auto-generated method stub
		mContentFlipper.startFlipping();
		
		mBtnUninit.setEnabled(true);
		mBtnInit.setEnabled(false);		
	}	

	//停止滑动
	private void doUninit() {
		// TODO Auto-generated method stub
		mContentFlipper.stopFlipping();		
		
		mBtnUninit.setEnabled(false);
		mBtnInit.setEnabled(true);
	}

	//--------------------------------------------------------------------------
};