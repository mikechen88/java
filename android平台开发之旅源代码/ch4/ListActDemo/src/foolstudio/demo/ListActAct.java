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
        
        //��ʼ���б���Ŀ
        mItems = new ArrayList<HashMap<String,String>>();
        addItem("Paul Wang", "Male", "29");
        addItem("Leo Tang", "Female", "27");
        addItem("Jack Chou", "Male", "30");
        addItem("Jerry Chen", "Female", "28");        
        
        //�����б�����������
        ListAdapter adapter = new SimpleAdapter(ListActAct.this,
        		mItems,
        		R.layout.row_ui,
        		mColumnNames,
        		mViewIds);
        
        //����ҳü��ҳ��
        this.getListView().addHeaderView(
        		this.getLayoutInflater().inflate(R.layout.header_view, null), 
        		null, false);      
        this.getListView().addFooterView(
        		this.getLayoutInflater().inflate(R.layout.footer_view, null), 
        		null, false);        
        
        //����������������������
        setListAdapter(adapter);       
        
        mTxtFooter = (TextView)findViewById(R.id.TXT_FOOTER);
    }
    
    //--------------------------------------------------------------------------
    //��Ӽ�¼
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
    	//֪ͨ��ʽ1
    	Toast.makeText(this, hint, Toast.LENGTH_SHORT).show();
    	
    	//֪ͨ��ʽ2    	
    	mTxtFooter.setText(hint);
    }
    
    //--------------------------------------------------------------------------    
};