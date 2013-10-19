package foolstudio.demo;

import java.util.ArrayList;

import foolstudio.util.Payout;
import foolstudio.util.SQLiteUtil;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReviewRecAct extends Activity implements OnClickListener {

	private TextView mPrevieTitle = null;
	private EditText mTxtTimestamp = null;
	private EditText mTxtComments = null;
	private EditText mTxtMoney = null;
	//按钮
	private Button mBtnNext = null;
	private Button mBtnPrev = null;
	//记录索引
	private int mRecordIndex = 0;
	private int mRecordCount = 0;
	//
	private ArrayList<Payout> mRecordSet = null; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_view);
        
        //获取输出控制对象
        mPrevieTitle = (TextView)findViewById(R.id.REVIEW_TITLE);
        mTxtTimestamp = (EditText)findViewById(R.id.TEXT_TIMESTAMP);
        mTxtComments = (EditText)findViewById(R.id.TEXT_COMMENTS);
        mTxtMoney = (EditText)findViewById(R.id.TEXT_MONEY);        
        
        //设置按钮事件侦听
        mBtnNext = (Button)findViewById(R.id.BTN_NEXT);
        mBtnPrev = (Button)findViewById(R.id.BTN_PREV);
        Button btnBack = (Button)findViewById(R.id.BTN_BACK);
        mBtnNext.setOnClickListener(this);
        mBtnPrev.setOnClickListener(this); 
        btnBack.setOnClickListener(this); 
        
        //初始化数据集
        initDataSet();
		initShow();
    }
    
    //初始化数据集
    private void initDataSet() {
    	Cursor cursor = 
			SQLiteUtil.getInstance().openQuery(Config.DATABASE_NAME, 
    										   Config.TABLE_PAYOUT, null);
    	mRecordCount = cursor.getCount();
    	
    	if(mRecordCount > 0) {
    		
    		mRecordSet = new ArrayList<Payout>(mRecordCount);
    		
			while(!cursor.isAfterLast()) {
				Payout payout = new Payout(cursor.getString(0),
										   cursor.getString(1),
										   cursor.getDouble(2) );
				mRecordSet.add(payout);
				
				cursor.moveToNext();
			}
    	}
    	
    	cursor.close();
	}
    
	//初始化显示
	private void initShow() {		
    	if(mRecordCount > 1) {   		
    		mBtnNext.setEnabled(true);
    		mBtnPrev.setEnabled(false);
    	}
    	else {   		
    		mBtnNext.setEnabled(false);
    		mBtnPrev.setEnabled(false);    		
    	}
    	
    	if(mRecordCount > 0) { //初始化显示
    		showRecord();
    	}
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_BACK: {
				this.finish();
				break;
			}		
			case R.id.BTN_NEXT: {
				showRecord(1);
				break;
			}
			case R.id.BTN_PREV: {
				showRecord(-1);
				break;
			}
		}
	}
	
	//按照顺序增量显示记录
	private void showRecord(int delta) {
		if(delta > 0) { //Next
			mRecordIndex++;
			
			if(mRecordIndex == (mRecordCount-1) ) { //Last record
				mBtnNext.setEnabled(false);
			}
			
			mBtnPrev.setEnabled(true);
		}
		else { //Prev
			mRecordIndex--;
			
			if(mRecordIndex == 0) { //First record
				mBtnPrev.setEnabled(false);
			}
			
			mBtnNext.setEnabled(true);			
		}
		
		showRecord();
	}
	
	//显示记录内容
	private void showRecord() {
		Payout payout = mRecordSet.get(mRecordIndex);
		
		mPrevieTitle.setText("Review record (" + 
				(mRecordIndex+1) + "/" + mRecordCount + ")");
		mTxtTimestamp.setText(payout.getTimestamp() );
		mTxtComments.setText(payout.getComments() );
		mTxtMoney.setText(String.valueOf(payout.getMoney()) );		
	}
};
