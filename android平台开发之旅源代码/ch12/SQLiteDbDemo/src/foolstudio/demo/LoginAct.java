package foolstudio.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginAct extends Activity implements OnClickListener {
	public static final String INTENT_EXTRAS_NAME = "USER_ID";
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        
        Button btnDiscard = (Button)findViewById(R.id.btnLoginDiscard);
        Button btnCommit = (Button)findViewById(R.id.btnLoginCommit);
        
        btnDiscard.setOnClickListener(this);
        btnCommit.setOnClickListener(this);     
    }
    
    //--------------------------------------------------------------------------    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
		case R.id.btnLoginCommit: {
			doLogin();
			break;
		}
		case R.id.btnLoginDiscard: {
			this.finish();
			break;
		}		
		}		
	}
	
    //--------------------------------------------------------------------------
	//登录行为
	private void doLogin() {
		String txtID = 
			((EditText)findViewById(R.id.txtLoginID)).getText().toString().trim();
		String txtPassword = 
			((EditText)findViewById(R.id.txtPassword)).getText().toString().trim();
		
		if(txtID.length() < 1 || txtPassword.length() < 1) {
			SQLiteDBAct.showMessage(this, "ID or password can't empty!!");
			return;
		}
		
		if(checkUser(txtID, txtPassword) ) { //登录通过	
			Intent infoViewer = 
				new Intent(LoginAct.this, UserInfoViewerAct.class);
			infoViewer.putExtra(INTENT_EXTRAS_NAME, txtID);
			//压栈
			startActivity(infoViewer);
			
			//出栈
			this.finish();
		}
		else { //登录失败
			//SQLiteDBAct.showMessage(this, "User ID or password error, pls check!");
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("User ID or password error, pls select:")
			.setCancelable(false)
			.setPositiveButton("Try again!", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						return;
					}				
				})
			.setNegativeButton("Register!", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent register = new Intent(LoginAct.this, RegisterAct.class);
						//压栈（2）
						startActivity(register);
						
						//出栈（1）
						LoginAct.this.finish();
					}				
				});
			AlertDialog dlg = builder.create();
			dlg.show(); //非阻塞
		}
		
		//this.finish();
	}
    
    //--------------------------------------------------------------------------
    //初始化列表项目
    private boolean checkUser(String ID, String password) {
    	String condition = "(li_id='" + ID + "' and li_passwd='"+password+"')";
        SQLiteDatabase db = 
        	SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
        											   SQLiteDatabase.OPEN_READONLY);        
        Cursor cursor = db.query("login_info", null, condition, null, null, null, null);
        
        SQLiteDBAct.printLog(this, "Condition="+condition);
        SQLiteDBAct.printLog(this, "Records count="+cursor.getCount() );
        
        boolean isValid = (cursor.getCount() == 1);
        
        cursor.close();        
        //一定要及时关闭数据库，否则会提示内存泄露
        db.close();
        
        return (isValid);
	}
    
    //--------------------------------------------------------------------------
};
