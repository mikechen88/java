package foolstudio.demo;

import java.util.ArrayList;
import java.util.HashMap;
import foolstudio.util.FoolUtil;
import foolstudio.util.Payout;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ReportRecAct extends ListActivity {
	
	private String mColumnNames[] = null;	

	private final int[] mViewIds = {
		R.id.LAB_TIMESTAMP,
		R.id.LAB_COMMENTS,
		R.id.LAB_MONEY
	};
	
	private TextView mTxtFooter = null;
	
	private ArrayList<HashMap<String,String>> mItems = null;	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_view);
        
        mColumnNames = getResources().getStringArray(R.array.columns);
        
        //初始化列表项目
        mItems = new ArrayList<HashMap<String,String>>();
        
        //获取参数
        Intent intent = getIntent();
        ArrayList<Payout> recordSet = 
        	intent.getParcelableArrayListExtra(LookupRecAct.EXTRA_NAME);

        for(int i = 0; i < recordSet.size(); ++i) {
        	addRecord(recordSet.get(i) );
        }
        
        //创建列表数据适配器
        ListAdapter adapter = new SimpleAdapter(this,
        		mItems,
        		R.layout.report_row_view,
        		mColumnNames,
        		mViewIds);
        
        //设置页眉和页脚
        this.getListView().addHeaderView(
        		this.getLayoutInflater().inflate(R.layout.header_view, null), 
        		null, false);      
        this.getListView().addFooterView(
        		this.getLayoutInflater().inflate(R.layout.footer_view, null), 
        		null, false);        
        
        //设置数据适配器，绑定数据
        setListAdapter(adapter);          
        
        mTxtFooter = (TextView)findViewById(R.id.TXT_FOOTER);        
    }

    //--------------------------------------------------------------------------
    //添加记录
    private void addRecord(Payout payout) {
        HashMap<String,String> item = new HashMap<String,String>();
        item.put(mColumnNames[0], payout.getTimestamp() );
        item.put(mColumnNames[1], payout.getComments() );
        item.put(mColumnNames[2], String.valueOf(payout.getMoney()) );      
        mItems.add(item);    	
    }
    
    //--------------------------------------------------------------------------
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
    	HashMap<String,String> item = (HashMap<String,String>)(mItems.get(pos-1) );
    	
    	String hint = item.get(mColumnNames[0]).toString()+" "+
			item.get(mColumnNames[1]).toString()+" "+
			item.get(mColumnNames[2]).toString();

    	//通知方式1
    	FoolUtil.showMsg(this, hint);

    	//通知方式2    	
    	mTxtFooter.setText(hint);
    }
    
    //--------------------------------------------------------------------------      
};
