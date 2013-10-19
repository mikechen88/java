package foolstudio.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SQLiteDBAct extends Activity implements OnClickListener {
	public static final String DB_NAME="/sdcard/FOOLSTUDIO.DB";
	private Button btnDBMan = null;
	private Button btnLogin = null;
	
	//--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        btnDBMan = (Button)findViewById(R.id.btnDbMan);
        btnLogin = (Button)findViewById(R.id.btnLoginMan);

        btnDBMan.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

	//--------------------------------------------------------------------------    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
		case R.id.btnDbMan:
			manDB();
			break;		
		case R.id.btnLoginMan:
			login();
			break;
		default:
			break;
		}
	}
	
	//--------------------------------------------------------------------------
	//数据库管理
	private void manDB() {
		Intent dbManIntent = new Intent(SQLiteDBAct.this, DBManagerAct.class);
		startActivity(dbManIntent);
	}
	
	//--------------------------------------------------------------------------
	//记录管理
	private void login() {
		Intent loginIntent = new Intent(SQLiteDBAct.this, LoginAct.class);
		startActivity(loginIntent);		
	}
	
    //--------------------------------------------------------------------------
	//输出日志
	public static void printLog(Context act, final String msg) {
		Log.d(act.getClass().getName(), msg);		
	}	
	
    //--------------------------------------------------------------------------
	//提示消息
	public static void showMessage(Context act, final String msg) {
    	Toast toast = Toast.makeText(act, msg, Toast.LENGTH_LONG);
    	toast.show();		
	}	
	
	//--------------------------------------------------------------------------
};