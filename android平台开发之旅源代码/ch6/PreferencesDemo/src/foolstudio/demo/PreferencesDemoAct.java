package foolstudio.demo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PreferencesDemoAct extends Activity implements OnClickListener {
	
	public static final String PREFERENCES_NAME = "PreferencesDemo.pre";
	public static final String SETTING_NAME1 = "property1";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnStore = (Button)findViewById(R.id.BTN_STORE);
        Button btnFetch = (Button)findViewById(R.id.BTN_FETCH);
        
        btnStore.setOnClickListener(this);
        btnFetch.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_STORE: {
				doStore();
				break;
			}
			case R.id.BTN_FETCH: {
				doFetch();
				break;
			}			
		}
	}

	//执行存储
	private void doStore() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, 
													Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(SETTING_NAME1, "foolstudio");
		//提交修改
		editor.commit();
	}	
	
	//执行读取
	private void doFetch() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences(PREFERENCES_NAME, 
													Activity.MODE_PRIVATE);
		String setting1 = sp.getString(SETTING_NAME1, "");
		
		Toast.makeText(this, setting1, Toast.LENGTH_LONG).show();
	}
};