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
        
        //��ȡ������ƶ���
        mTxtTimestamp = (EditText)findViewById(R.id.TEXT_TIMESTAMP);
        mTxtComments = (EditText)findViewById(R.id.TEXT_COMMENTS);
        mTxtMoney = (EditText)findViewById(R.id.TEXT_MONEY);        
        
        //���ð�ť�¼�����
        Button btnDiscard = (Button)findViewById(R.id.BTN_DISCARD);
        Button btnSubmit = (Button)findViewById(R.id.BTN_SUBMIT);
        btnDiscard.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        
        //��ʼ������
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
		//�ύ���
		if(submitCheck() == false) {
			return;
		}
        //����֧������
		Payout payout = new Payout(mTxtTimestamp.getText().toString().trim(),
				mTxtComments.getText().toString().trim(),
				Double.parseDouble(mTxtMoney.getText().toString().trim()) );
		//���ö������ݿ⹤����ִ����Ӷ���
		OdbUtil.getInstance().appendObject(db4oJournalBookAct.DATABASE_NAME, payout);
		
		FoolUtil.showMsg(this, "����洢�ɹ���");
		this.finish();
	}
	
	private boolean submitCheck() {
		if(mTxtTimestamp.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "ʱ�������Ϊ�գ�");
			return (false);
		}
		else if(mTxtComments.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "��ע����Ϊ�գ�");
			return (false);
		}
		else if(mTxtMoney.getText().toString().trim().length() < 1) {
			FoolUtil.showMsg(this, "����Ϊ�գ�");
			return (false);
		}
		else {
			double money = 
				Double.parseDouble(mTxtMoney.getText().toString().trim() );
			
			if(money == 0.0D) {
				FoolUtil.showMsg(this, "����Ϊ�㣡");
				return (false);
			}
		}
		
		return (true);
	}
};
