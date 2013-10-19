package foolstudio.demo;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListActAct extends ListActivity {
	private ArrayList<HashMap<String,String>> mItems = null;
	
	private final String[] mColumnNames = {
		"Name",
		"Sex",
		"Age"
	};
	
	private final int[] mViewIds = {
		R.id.txtName,
		R.id.txtSex,
		R.id.txtAge
	};	
	
	private TextView mTxtFooter = null;
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        //初始化列表项目
        mItems = new ArrayList<HashMap<String,String>>();
        addItem("Paul Wang", "Male", "29");
        addItem("Leo Tang", "Female", "27");
        addItem("Jack Chou", "Male", "30");
        addItem("Jerry Chen", "Female", "28");        
        
        //创建列表数据适配器
        ListAdapter adapter = new SimpleAdapter(ListActAct.this,
        		mItems,
        		R.layout.row_ui,
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
    private void addItem(String name, String sex, String age) {
        HashMap<String,String> item = new HashMap<String,String>();
        item.put(mColumnNames[0], name);
        item.put(mColumnNames[1], sex);
        item.put(mColumnNames[2], age);      
        mItems.add(item);    	
    }
    
    //--------------------------------------------------------------------------
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
    	HashMap<String,String> item = (HashMap<String,String>)(mItems.get(pos-1) );
    	
    	String hint = "Current selected: " +
    				item.get(mColumnNames[0]).toString()+" "+
    				item.get(mColumnNames[1]).toString()+" "+
					item.get(mColumnNames[2]).toString();
    	//通知方式1
    	Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
    	
    	//通知方式2    	
    	mTxtFooter.setText(hint);
    }
    
    //--------------------------------------------------------------------------    
};