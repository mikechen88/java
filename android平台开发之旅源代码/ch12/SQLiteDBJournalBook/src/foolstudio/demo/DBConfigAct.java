package foolstudio.demo;

import java.io.File;

import foolstudio.util.FoolUtil;
import foolstudio.util.SQLiteUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DBConfigAct extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config_view);
		
		Button btnInit = (Button)findViewById(R.id.BTN_INIT);
		Button btnDrop = (Button)findViewById(R.id.BTN_DROP);
		
		btnInit.setOnClickListener(this);
		btnDrop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				doInit();
				break;
			}
			case R.id.BTN_DROP: {
				doDrop();
				break;
			}			
		}
	}
	
	//初始化数据库
	private void doInit() {
		// TODO Auto-generated method stub
    	if(SQLiteUtil.getInstance().isTableExists(Config.DATABASE_NAME, 
    											Config.TABLE_PAYOUT) == false) {
	    	String sql = "create table " + Config.TABLE_PAYOUT + 
	    		"(Timestamp TEXT primary key, Comments TEXT, Money TABLE_PAYOUT)";
	    	SQLiteUtil.getInstance().execQuery(Config.DATABASE_NAME, sql);
	    	
	    	FoolUtil.showMsg(this, "Create table " + 
	    			Config.TABLE_PAYOUT + " successfully!");
    	}
    	else {
	    	FoolUtil.showMsg(this, "Table " + 
	    			Config.TABLE_PAYOUT + " already exists!");    		
    	}		
	}	

	//删除数据库
	private void doDrop() {
		// TODO Auto-generated method stub

		if(SQLiteUtil.getInstance().deleteDB(Config.DATABASE_NAME) ) {
		}
		else {
		}

		File dbFile = new File(Config.DATABASE_NAME);
		
		if(dbFile.exists() ) {
			dbFile.delete();
			
			FoolUtil.showMsg(this, "Drop database successfully!");
		}
		else {
			FoolUtil.showMsg(this, "Database not exists!");			
		}
	}
};
