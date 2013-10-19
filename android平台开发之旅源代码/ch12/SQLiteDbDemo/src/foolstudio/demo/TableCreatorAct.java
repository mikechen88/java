package foolstudio.demo;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

//创建数据库的活动
public class TableCreatorAct extends Activity implements OnClickListener {
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_creator_act_view);
        
        Button btnDiscard = (Button)findViewById(R.id.btnDiscard);
        Button btnCommit = (Button)findViewById(R.id.btnCommit);
        
        btnDiscard.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    //--------------------------------------------------------------------------    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btnDiscard: {
			this.finish();
			break;
		}
		case R.id.btnCommit: {
			execCreateTableSQL();
			break;
		}
		}
	}	
	
    //--------------------------------------------------------------------------
	private void execCreateTableSQL() {
        String tableName = 
        	((EditText)findViewById(R.id.txtTableName)).getText().toString().trim();       
        if(tableName.length() < 1) {
        	SQLiteDBAct.showMessage(this, "Table name can't empty!");
        	return;
        }
        
        String sql = "CREATE TABLE " + tableName + "(_id INTEGER PRIMARY KEY,";        
        
        String column1Name = 
        	((EditText)findViewById(R.id.txtColumn1Name)).getText().toString().trim();
        if(column1Name.length() < 1) {
        	SQLiteDBAct.showMessage(this, "At least one columen to be defined!");
        	return;
        }
        else {
        	Spinner spinner = (Spinner)findViewById(R.id.txtColumn1Type);
        	String column1Type = spinner.getSelectedItem().toString();
        	
        	sql += column1Name + " " + column1Type;
        }        
        
        String column2Name = 
        	((EditText)findViewById(R.id.txtColumn2Name)).getText().toString().trim();
        if(column2Name.length() > 0) {
        	Spinner spinner = (Spinner)findViewById(R.id.txtColumn2Type);
        	String column2Type = spinner.getSelectedItem().toString();
        	
        	sql += "," + column2Name + " " + column2Type;
        }
        
        String column3Name = 
        	((EditText)findViewById(R.id.txtColumn3Name)).getText().toString().trim();
        if(column3Name.length() > 0) {
        	Spinner spinner = (Spinner)findViewById(R.id.txtColumn3Type);
        	String column3Type = spinner.getSelectedItem().toString();
        	
        	sql += "," + column3Name + " " + column3Type;
        }
        
        String column4Name = 
        	((EditText)findViewById(R.id.txtColumn4Name)).getText().toString().trim();
        if(column4Name.length() > 0) {
        	Spinner spinner = (Spinner)findViewById(R.id.txtColumn4Type);
        	String column4Type = spinner.getSelectedItem().toString();
        	
        	sql += "," + column4Name + " " + column4Type;
        }
        
        sql += ")";
        
        SQLiteDBAct.printLog(this, "SQL=" + sql);
        
        //
        SQLiteDatabase db = 
        	SQLiteDatabase.openOrCreateDatabase(SQLiteDBAct.DB_NAME, null);
        db.execSQL(sql);        
        db.close();
        //
        this.finish();
	}
    
    //--------------------------------------------------------------------------    
};
