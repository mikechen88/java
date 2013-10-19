package foolstudio.demo;

import foolstudio.util.FoolUtil;
import foolstudio.util.SQLiteUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AppendRecAct extends Activity implements OnClickListener {
	
	private EditText mTxtTimestamp = null;
	private EditText mTxtComments = null;
	private EditText mTxtMoney = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.append_view);
        
        //获取输入控制对象
        mTxtTimestamp = (EditText)findViewById(R.id.TEXT_TIMESTAMP);
        mTxtComments = (EditText)findViewById(R.id.TEXT_COMMENTS);
        mTxtMoney = (EditText)findViewById(R.id.TEXT_MONEY);        
        
        //设置按钮事件侦听
        Button btnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
        Button btnSubmit = (Button)findViewById(R.id.BTN_SUBMIT);
        btnDiscard.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        
        //初始化界面
        mTxtTimestamp.setText(FoolUtil.getDateTimeString() );
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_DISCARD: {
				this.finish();
				break;
			}
			case R.id.BTN_SUBMIT: {
				doSubmit();
				break;
			}
		}
	}
	
	//提交记录
	private void doSubmit() {
		if(submitCheck() == false) {
			return;
		}
		
		String sql = "insert into " + Config.TABLE_PAYOUT + 
			"(timestamp,comments,money) values('" +
			mTxtTimestamp.getText().toString().trim() + "','" +
			mTxtComments.getText().toString().trim() + "'," +
			mTxtMoney.getText().toString().trim() + ")";
		SQLiteUtil.getInstance().execQuery(Config.DATABASE_NAME, sql);
		
		FoolUtil.showMsg(this, "Store record successfully!");
		this.finish();
	}
	
	//提交检查
	private boolean submitCheck() {
		if(mTxtTimestamp.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "Timestamp can't empty!");
			return (false);
		}
		else if(mTxtComments.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "Comments can't empty!");
			return (false);
		}
		else if(mTxtMoney.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "Money can't empty!");
			return (false);
		}
		else {
			double money = 
				Double.parseDouble(mTxtMoney.getText().toString().trim() );
			
			if(money == 0.0D) {
				FoolUtil.showMsg(this, "Money can't be zero!");
				return (false);
			}
		}
		
		return (true);
	}
};
