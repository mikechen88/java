package foolstudio.demo;

import java.util.ArrayList;
import foolstudio.util.FoolUtil;
import foolstudio.util.Payout;
import foolstudio.util.SQLiteUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LookupRecAct extends Activity implements OnClickListener {
	
	public static final String EXTRA_NAME = "RECORD_SET";
	
	private Spinner mSpnColumns = null;
	private Spinner mSpnOperators = null;
	private EditText mTxtValue = null;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup_view);
        
        //获取输入控制对象
        mSpnColumns = (Spinner)findViewById(R.id.SPN_COLUMNS);
        mSpnOperators = (Spinner)findViewById(R.id.SPN_OPERATORS);
        mTxtValue = (EditText)findViewById(R.id.TEXT_QUERY_VALUE);        
        
        //设置按钮事件侦听
        Button btnQuery = (Button)findViewById(R.id.BTN_QUERY);
        Button btnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
        btnQuery.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				this.finish();
				break;
			}
			case R.id.BTN_QUERY: {
				doQuery();
				break;
			}
		}
	}
	
	//执行查询动作
	private void doQuery() {
		String condStr = makeConditonStr();
		
		Cursor cursor = SQLiteUtil.getInstance().openQuery(
				Config.DATABASE_NAME, 
				Config.TABLE_PAYOUT, condStr);
		int recordCount = cursor.getCount();
		
		if(recordCount  > 0) {
			ArrayList<Payout> recordSet = new ArrayList<Payout>(recordCount);
			
			while(!cursor.isAfterLast()) {
				Payout payout = new Payout(cursor.getString(0),
										   cursor.getString(1),
										   cursor.getDouble(2) );
				recordSet.add(payout);
				
				cursor.moveToNext();
			}
			
			cursor.close();
			
			Intent reportRecIntent = new Intent(this, ReportRecAct.class);
			reportRecIntent.putParcelableArrayListExtra(EXTRA_NAME, recordSet);
			this.startActivity(reportRecIntent);
		}
		else {
			FoolUtil.showMsg(this, "The result of query is null, pls try again!");
			return;
		}
	}
	
	//--------------------------------------------------------------------------
	//生成条件字符串
	private String makeConditonStr() {
		String columnName = mSpnColumns.getSelectedItem().toString();
		String operator = mSpnOperators.getSelectedItem().toString();
		String value = mTxtValue.getText().toString().trim();
		StringBuffer sb = new StringBuffer(columnName);
		
		sb.append(' ');
		sb.append(operator);
		sb.append(' ');		
		
		if( (columnName.compareToIgnoreCase("Timestamp") == 0) || 
			(columnName.compareToIgnoreCase("Comments") == 0) ) {			
			if(operator.compareToIgnoreCase("=") == 0) {
				sb.append('\'');
				sb.append(value);
				sb.append('\'');
			}
			else if(operator.compareToIgnoreCase("LIKE") == 0) {
				sb.append("\'%");
				sb.append(value);
				sb.append("%\'");
			}	
		}
		else { //Money
			sb.append(value);	
		}

		FoolUtil.printLog(this, sb.toString() );
		
		return (sb.toString() );
	}
};
