package foolstudio.demo.pim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PIMDemoAct extends Activity implements OnClickListener {
	
	private EditText mTxtContents = null;	
	private Button mBtnPeople = null;
	private Button mBtnPhone = null;
	private Button mBtnOrgs = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        //
        mBtnPeople = (Button)findViewById(R.id.BTN_PEOPLE);
        mBtnPhone = (Button)findViewById(R.id.BTN_PHONES);
        mBtnOrgs = (Button)findViewById(R.id.BTN_ORGS);
        
        mBtnPeople.setOnClickListener(this);
        mBtnPhone.setOnClickListener(this);
        mBtnOrgs.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_PEOPLE: {
				mTxtContents.setText(null);
				showPeople();
				break;
			}
			case R.id.BTN_PHONES: {
				mTxtContents.setText(null);
				showPhones();
				break;
			}	
			case R.id.BTN_ORGS: {
				mTxtContents.setText(null);
				showOrganizations();
				break;
			}			
		}
	}    

	private void showPeople() {
		// TODO Auto-generated method stub
		printText(PeopleUtil.getInfo(this.getContentResolver()) );
	}

	private void showPhones() {
		// TODO Auto-generated method stub
		printText(PhonesUtil.getInfo(this.getContentResolver()) );
	}

	private void showOrganizations() {
		// TODO Auto-generated method stub
		printText(OrganizationsUtil.getInfo(this.getContentResolver()) );
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};