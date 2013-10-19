package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PictureReunionAct extends Activity {
	
	private Button mBtnStart = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);
        
        mBtnStart = (Button)findViewById(R.id.btnStart);
        mBtnStart.setOnClickListener(new OnClickListener() {
        	//------------------------------------------------------------------
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent newGameIntent = new Intent();
				newGameIntent.setClass(PictureReunionAct.this, PictureReunionGameAct.class);
				//∆Ù∂Ø”Œœ∑Activity
				startActivity(newGameIntent);
			}        	
        });
    }
    
    //--------------------------------------------------------------------------    
};