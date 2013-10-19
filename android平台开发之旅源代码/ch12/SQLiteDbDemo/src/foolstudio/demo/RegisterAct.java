package foolstudio.demo;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterAct extends Activity implements OnClickListener {
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_vew);
        
        Button btnDiscard = (Button)findViewById(R.id.btnRegisterDiscard);
        Button btnCommit = (Button)findViewById(R.id.btnRegisterCommit);
        
        btnDiscard.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    //--------------------------------------------------------------------------    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btnRegisterDiscard: {
			this.finish();
			break;
		}
		case R.id.btnRegisterCommit: {
			if(checkForm() ) {
				doRegister();
			}
			break;
		}
		}
	}	
	
    //--------------------------------------------------------------------------
	private boolean checkForm() {
		String id = ((EditText)findViewById(R.id.txtRegisterUserID)).
														getText().toString().trim();
		String passwd = ((EditText)findViewById(R.id.txtRegisterPassword)).
														getText().toString().trim();
		String passwd2 = ((EditText)findViewById(R.id.txtRegisterPassword2)).
														getText().toString().trim();
		String name = ((EditText)findViewById(R.id.txtRegisterName)).
														getText().toString().trim();
		//String sex = ((EditText)findViewById(R.id.spRegisterSex)).
		//												getText().toString().trim();
		//String birthday = ((EditText)findViewById(R.id.txtRegisterBirthday)).
		//												getText().toString().trim();
		
		if(id.length() < 1) {
			SQLiteDBAct.showMessage(this, "User ID can't empty!");
			return (false);
		}
		
		if(passwd.length() < 1) {
			SQLiteDBAct.showMessage(this, "Password can't empty!");
			return (false);
		}
		
		if(passwd.compareTo(passwd2) != 0) {
			SQLiteDBAct.showMessage(this, "Confirm password failed!");
			return (false);
		}
		
		if(name.length() < 1) {
			SQLiteDBAct.showMessage(this, "Name can't empty!");
			return (false);
		}
		
		//判断主键是否存在
		SQLiteDatabase db = 
			SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
													   SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = db.query("login_info", new String[] {"li_id"}, 
				"(li_id='" + id +"')", null, null, null, null, null);
		int recordCount = cursor.getCount(); 
		cursor.close();
		db.close();
		
		if(recordCount > 0) {
			SQLiteDBAct.showMessage(this, "This ID already exists!");
			return(false);
		}
		
		return (true);
	}
	
    //--------------------------------------------------------------------------
	private void doRegister() {
		String id = ((EditText)findViewById(R.id.txtRegisterUserID)).
														getText().toString().trim();
		String passwd = ((EditText)findViewById(R.id.txtRegisterPassword)).
														getText().toString().trim();
		//String passwd2 = ((EditText)findViewById(R.id.txtRegisterPassword2)).
		//												getText().toString().trim();
		String name = ((EditText)findViewById(R.id.txtRegisterName)).
														getText().toString().trim();
		String sex = ((Spinner)findViewById(R.id.spRegisterSex)).
														getSelectedItem().toString().trim();
		String birthday = ((EditText)findViewById(R.id.txtRegisterBirthday)).
														getText().toString().trim();
        
        String sql1 = "INSERT INTO login_info(li_id, li_passwd) values('" + 
        					id +"','" + passwd+"')";
        String sql2 = 
        	"INSERT INTO user_info(ui_id, ui_name, ui_sex, ui_birthday) values('" +
        	id +"','" + name +"','" + sex+ "','" + birthday+"')";
        
        SQLiteDBAct.printLog(this, "SQL1=" + sql1);
        SQLiteDBAct.printLog(this, "SQL2=" + sql2);
        
        //
        SQLiteDatabase db = 
        	SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
        											   SQLiteDatabase.OPEN_READWRITE);
        //开始事务
        db.beginTransaction();
        
        try {
        	db.execSQL(sql1);
        	db.execSQL(sql2);
        	//事务成功
        	db.setTransactionSuccessful();
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        finally {
        	//终止事务
        	db.endTransaction();
        }
        
        db.close();
        //
        this.finish();
	}
    
    //--------------------------------------------------------------------------    
};
