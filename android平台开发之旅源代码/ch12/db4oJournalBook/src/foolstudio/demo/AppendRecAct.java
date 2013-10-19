package foolstudio.demo;

import foolstudio.util.FoolUtil;
import foolstudio.util.OdbUtil;
import foolstudio.util.Payout;
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
	
	private void doSubmit() {
		//提交检查
		if(submitCheck() == false) {
			return;
		}
        //构造支付对象
		Payout payout = new Payout(mTxtTimestamp.getText().toString().trim(),
				mTxtComments.getText().toString().trim(),
				Double.parseDouble(mTxtMoney.getText().toString().trim()) );
		//调用对象数据库工具类执行添加对象
		OdbUtil.getInstance().appendObject(db4oJournalBookAct.DATABASE_NAME, payout);
		
		FoolUtil.showMsg(this, "对象存储成功！");
		this.finish();
	}
	
	private boolean submitCheck() {
		if(mTxtTimestamp.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "时间戳不能为空！");
			return (false);
		}
		else if(mTxtComments.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "备注不能为空！");
			return (false);
		}
		else if(mTxtMoney.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "金额不能为空！");
			return (false);
		}
		else {
			double money = 
				Double.parseDouble(mTxtMoney.getText().toString().trim() );
			
			if(money == 0.0D) {
				FoolUtil.showMsg(this, "金额不能为零！");
				return (false);
			}
		}
		
		return (true);
	}
};
