package foolstudo.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class AutoCompleteTextViewDemoAct extends Activity implements OnClickListener {
	
	AutoCompleteTextView mAutoView = null;
	ImageButton mBtnAction = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mAutoView = (AutoCompleteTextView)findViewById(R.id.ACTV_VIEW);
        mBtnAction = (ImageButton)findViewById(R.id.BTN_ACTION);        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        	android.R.layout.simple_dropdown_item_1line,
        	this.getResources().getStringArray(R.array.topics) );

        mAutoView.setAdapter(adapter);
        mBtnAction.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.BTN_ACTION) {
			doAction();
		}
	}

	private void doAction() {
		// TODO Auto-generated method stub
		Toast.makeText(this, mAutoView.getText(), Toast.LENGTH_SHORT).show();
	}
};