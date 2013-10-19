package foolstudio.demo.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class RatingBarDemoAct extends Activity {
	
	private static final int[] DRAWABLE_IDs = {
		R.drawable.flower1, R.drawable.flower2,
		R.drawable.flower3, R.drawable.flower4
	};
	
	private LinearLayout mMainView = null; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMainView = (LinearLayout)findViewById(R.id.MAIN_PANEL);
        init();
    }

	private void init() {
		// TODO Auto-generated method stub		
		for(int i = 0; i < DRAWABLE_IDs.length; ++i) {
			mMainView.addView(createImageView(DRAWABLE_IDs[i]) );
			mMainView.addView(createRatingBar() );
		}		
	}
	
	//根据资源ID创建图片视图组件
	private View createImageView(int resId) {
		// TODO Auto-generated method stub
		ImageView iv = new ImageView(this);
		iv.setImageResource(resId);
		iv.setScaleType(ImageView.ScaleType.CENTER);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, 
					LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		iv.setLayoutParams(params);

		return (iv);
	}	

	//创建等级滑动条
	private View createRatingBar() {
		// TODO Auto-generated method stub
		RatingBar rb = new RatingBar(this);
		rb.setNumStars(5);
		rb.setMax(5);
		rb.setStepSize(0.5f);		
		rb.setRating(1.0f);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);		
		params.gravity = Gravity.CENTER_HORIZONTAL;
		rb.setLayoutParams(params);
		return (rb);
	}
};