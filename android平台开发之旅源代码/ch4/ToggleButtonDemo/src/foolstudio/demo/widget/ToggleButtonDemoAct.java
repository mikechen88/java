package foolstudio.demo.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ToggleButtonDemoAct extends Activity implements OnCheckedChangeListener {
	
	private ToggleButton mBtnToggle1 = null;
	private ToggleButton mBtnToggle2 = null;
	private ToggleButton mBtnToggle3 = null;
	private ToggleButton mBtnToggle4 = null;
	private ImageView mViewBkg1 = null;		
	private ImageView mViewBkg2 = null;		
	private ImageView mViewBkg3 = null;	
	private ImageView mViewBkg4 = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnToggle1 = (ToggleButton)findViewById(R.id.BTN_TOGGLE1);
        mBtnToggle2 = (ToggleButton)findViewById(R.id.BTN_TOGGLE2);
        mBtnToggle3 = (ToggleButton)findViewById(R.id.BTN_TOGGLE3);
        mBtnToggle4 = (ToggleButton)findViewById(R.id.BTN_TOGGLE4);
        mViewBkg1 = (ImageView)findViewById(R.id.VIEW_BKG1);           
        mViewBkg2 = (ImageView)findViewById(R.id.VIEW_BKG2);           
        mViewBkg3 = (ImageView)findViewById(R.id.VIEW_BKG3);        
        mViewBkg4 = (ImageView)findViewById(R.id.VIEW_BKG4);
        
        mBtnToggle1.setOnCheckedChangeListener(this);
        mBtnToggle2.setOnCheckedChangeListener(this); 
        mBtnToggle3.setOnCheckedChangeListener(this);
        mBtnToggle4.setOnCheckedChangeListener(this);        
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(buttonView.getId() == R.id.BTN_TOGGLE1) {
			if(isChecked) {
				mViewBkg1.setVisibility(View.VISIBLE);
				mBtnToggle1.setGravity(Gravity.TOP);
			}
			else {
				mViewBkg1.setVisibility(View.GONE);
				mBtnToggle1.setGravity(Gravity.BOTTOM);				
			}
		}
		else if(buttonView.getId() == R.id.BTN_TOGGLE2) {
			if(isChecked) {
				mViewBkg2.setVisibility(View.VISIBLE);
				mBtnToggle2.setGravity(Gravity.TOP);
			}
			else {
				mViewBkg2.setVisibility(View.GONE);
				mBtnToggle2.setGravity(Gravity.BOTTOM);				
			}
		}
		else if(buttonView.getId() == R.id.BTN_TOGGLE3) {
			if(isChecked) {
				mViewBkg3.setVisibility(View.VISIBLE);
				mBtnToggle3.setGravity(Gravity.TOP);
			}
			else {
				mViewBkg3.setVisibility(View.GONE);
				mBtnToggle3.setGravity(Gravity.BOTTOM);				
			}
		}
		else if(buttonView.getId() == R.id.BTN_TOGGLE4) {
			if(isChecked) {
				mViewBkg4.setVisibility(View.VISIBLE);
				mBtnToggle4.setGravity(Gravity.TOP);
			}
			else {
				mViewBkg4.setVisibility(View.GONE);
				mBtnToggle4.setGravity(Gravity.BOTTOM);				
			}
		}		
	}
	
	//--------------------------------------------------------------------------
};