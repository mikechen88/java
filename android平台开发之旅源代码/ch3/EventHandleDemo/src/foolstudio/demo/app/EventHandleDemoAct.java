package foolstudio.demo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EventHandleDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnAction = null;
	
    private OnClickListener mListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId() == R.id.BTN_ACTION) {
				Toast.makeText(EventHandleDemoAct.this, 
							   "Hello, Android!", 
							   Toast.LENGTH_SHORT).show();
			}
		}
    };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        
        //方式1
		//mBtnAction.setOnClickListener(mListener);
        //方式2
        mBtnAction.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.BTN_ACTION) {
			Toast.makeText(EventHandleDemoAct.this, 
						   "Hello, Android!", 
						   Toast.LENGTH_SHORT).show();
		}		
	}
}