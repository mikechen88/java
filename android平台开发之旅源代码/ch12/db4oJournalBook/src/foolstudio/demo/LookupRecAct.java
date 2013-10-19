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
        
        //��ȡ������ƶ���
        mSpnColumns = (Spinner)findViewById(R.id.SPN_COLUMNS);
        mSpnOperators = (Spinner)findViewById(R.id.SPN_OPERATORS);
        mTxtValue = (EditText)findViewById(R.id.TEXT_QUERY_VALUE);        
        
        //���ð�ť�¼�����
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
		
		//������ѯν��ʵ��
		Predicate<Payout> predicate = 
			new JournalBookPredicate(columnName, operator, value);		
		//ִ�в�ѯ����
		ArrayList recordSet = 
			OdbUtil.getInstance().queryObjects(db4oJournalBookAct.DATABASE_NAME, 
											   predicate);
		if(recordSet.size() > 0) {
			Intent reportRecIntent = new Intent(this, ReportRecAct.class);
			reportRecIntent.putParcelableArrayListExtra(EXTRA_NAME, recordSet);
			this.startActivity(reportRecIntent);
		}
		else {
			FoolUtil.showMsg(this, "��ѯ���Ϊ�գ������ԣ�");
			return;
		}
	}
};
