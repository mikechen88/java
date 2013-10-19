package foolstudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class FoolImageViewerAct extends Activity {
	
	private Integer mImageIds[] = {
		R.drawable.bird,
		R.drawable.flower1,
		R.drawable.flower2,
		R.drawable.flower3,
		R.drawable.flower4,
		R.drawable.flower5,
		R.drawable.flower_tree1,
		R.drawable.flower_tree2,
		R.drawable.flower_tree4,
		R.drawable.flower_tree5,
		R.drawable.flower_tree6,
		R.drawable.sky,
		R.drawable.tree1
	};
	
	private Integer mCurImageIndex1 = 0;
	private Integer mCurImageIndex2 = 1;
	
	private CheckBox mShowModeBox;
	private ImageView mIvTop;
	private ImageView mIvBottom;
	private Button mBtnNext;
	private Button mBtnPrev;
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //获取组件对象
        mShowModeBox = (CheckBox)findViewById(R.id.cbxShowMode);
        mIvTop = (ImageView)findViewById(R.id.ivTop);
        mIvBottom = (ImageView)findViewById(R.id.ivBottom);
        mBtnNext = (Button)findViewById(R.id.btnNext);
        mBtnPrev = (Button)findViewById(R.id.btnPrev);
        
        //设置对象属性
        mIvTop.setImageResource(mImageIds[mCurImageIndex1]);
        mIvBottom.setImageResource(mImageIds[mCurImageIndex2]);
        
        //设置事件响应
        mBtnNext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurImageIndex2++;
				mCurImageIndex1++;
				
				if(mCurImageIndex2 >= mImageIds.length) {
					mCurImageIndex2 = 0;
				}
				
				if(mCurImageIndex1 >= mImageIds.length) {
					mCurImageIndex1 = 0;
				}				
				
		        mIvTop.setImageResource(mImageIds[mCurImageIndex1]);
		        mIvBottom.setImageResource(mImageIds[mCurImageIndex2]);				
			}        	
        });
        
        mBtnPrev.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub		
				if(mCurImageIndex1 > 0) {
					mCurImageIndex1--;			
				}
				else {
					mCurImageIndex1 = (mImageIds.length-1);			
				}		
				
				if(mCurImageIndex2 > 0) {
					mCurImageIndex2--;			
				}
				else {
					mCurImageIndex2 = (mImageIds.length-1);					
				}					
				
		        mIvTop.setImageResource(mImageIds[mCurImageIndex1]);
		        mIvBottom.setImageResource(mImageIds[mCurImageIndex2]);				
			}        	
        });
        
        mShowModeBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	//------------------------------------------------------------------
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked) {
					mIvBottom.setVisibility(View.VISIBLE);
					mShowModeBox.setText("Double/Page");
				}
				else {
					mIvBottom.setVisibility(View.INVISIBLE);
					mShowModeBox.setText("Single/Page");
				}
			}
			
			//------------------------------------------------------------------
        });
    }
    
    //--------------------------------------------------------------------------
};