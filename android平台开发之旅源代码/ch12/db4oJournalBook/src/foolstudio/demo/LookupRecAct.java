package foolstudio.demo;

import java.util.ArrayList;
import com.db4o.query.Predicate;

import foolstudio.util.FoolUtil;
import foolstudio.util.OdbUtil;
import foolstudio.util.Payout;

import android.app.Activity;
import android.content.Intent;
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
	
	private void doQuery() {
		String columnName = mSpnColumns.getSelectedItem().toString();
		String operator = mSpnOperators.getSelectedItem().toString();
		String value = mTxtValue.getText().toString().trim();
		
		//创建查询谓词实例
		Predicate<Payout> predicate = 
			new JournalBookPredicate(columnName, operator, value);		
		//执行查询操作
		ArrayList recordSet = 
			OdbUtil.getInstance().queryObjects(db4oJournalBookAct.DATABASE_NAME, 
											   predicate);
		if(recordSet.size() > 0) {
			Intent reportRecIntent = new Intent(this, ReportRecAct.class);
			reportRecIntent.putParcelableArrayListExtra(EXTRA_NAME, recordSet);
			this.startActivity(reportRecIntent);
		}
		else {
			FoolUtil.showMsg(this, "查询结果为空，请重试！");
			return;
		}
	}
};
