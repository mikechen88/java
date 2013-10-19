package foolstudio.demo;

import java.sql.Connection;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RdbDemoAct extends Activity implements OnClickListener {
	public static final String TABLE_LOGIN_INFO = "LOGIN_INFO";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnDo = (Button)findViewById(R.id.BTN_DERBY);
        btnDo.setOnClickListener(this);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		DerbyDB derbyDB = new DerbyDB();
		
		Connection conn = derbyDB.openDB();
		Statement stat = derbyDB.getQueryStat(conn);
		Statement stat2 = derbyDB.getExecStat(conn);
		
		//初始化数据表
		if(derbyDB.isTableExists(stat, TABLE_LOGIN_INFO) != true) {
			String args = "(li_id varchar(64) primary key, li_passwd varchar(64) )";
			derbyDB.createTable(stat2, TABLE_LOGIN_INFO, args);
		}
		
		//插入记录
		derbyDB.insertTable(stat2, TABLE_LOGIN_INFO,
							"(li_id, li_passwd) values('Paul', '123456')");
		derbyDB.insertTable(stat2, TABLE_LOGIN_INFO,
							"(li_id, li_passwd) values('Leo', '123456')");
		derbyDB.closeStat(stat2);		
		derbyDB.closeStat(stat);
		derbyDB.closeDB(conn);		
	}
};