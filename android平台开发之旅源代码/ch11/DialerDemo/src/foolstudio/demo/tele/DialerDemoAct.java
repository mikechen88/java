package foolstudio.demo.tele;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DialerDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtContents = null;	
	private Button mBtnCall = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        mBtnCall = (Button)findViewById(R.id.BTN_CALL);
        
        mBtnCall.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_CALL: {
				doCall();
				break;
			}
		}
	}

	//Ö´ÐÐºô½Ð¶¯×÷
	private void doCall() {
		// TODO Auto-generated method stub
		Uri uri = Uri.parse("tel:"+mTxtContents.getText().toString().trim() );
		Intent dialIntent = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(dialIntent);
	}
};