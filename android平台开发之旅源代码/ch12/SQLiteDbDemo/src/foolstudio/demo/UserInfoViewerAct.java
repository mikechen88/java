package foolstudio.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

//��ṹ�鿴�
public class UserInfoViewerAct extends ListActivity {
	//����ʾ���ID����
	private int[] mRowViewIds = {
		R.id.txtColIndex,
		R.id.txtColName,
		R.id.txtColContent
	};
	
	private String[] mColumnNames = {
			"field_index",
			"field_name",
			"field_content"
		};	
	
	private List<Map<String,String>> mItems = null;	
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_viewer_view);
        
        //��ȡ����
        Bundle extras = getIntent().getExtras();
        String userID = extras.getString(LoginAct.INTENT_EXTRAS_NAME);
        //
        
        initList(userID);
        
        //�����б�����������
        ListAdapter adapter = new SimpleAdapter(UserInfoViewerAct.this,
        		mItems,
        		R.layout.user_info_row_view,        		
        		mColumnNames,
        		mRowViewIds);
        
        //����������������������
        setListAdapter(adapter);        
    }
    
    //--------------------------------------------------------------------------
    //��ʼ���б���Ŀ
    private void initList(String userID) {
        mItems = new ArrayList<Map<String, String>>();
        
        SQLiteDatabase db = 
        	SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
        											SQLiteDatabase.OPEN_READONLY);        
        Cursor cursor = db.query("user_info", null, 
        		"(ui_id='"+userID+"')", null, null, null, null);
        
        if(cursor.getCount() == 1) {        	
        	//һ��Ҫ�α긴λ�����������ȡ���׳��쳣
            cursor.moveToFirst();
                	
        	String[] allColumnNames = cursor.getColumnNames();
        	
        	for(int i = 0; i < allColumnNames.length; ++i) {
        		String content = cursor.getString(i);
        		
        		addItem("#"+i, allColumnNames[i], content);
        	}
        }
        else {
        	SQLiteDBAct.printLog(this, "Get " + userID + 
        			"'s record from table user_info ocur exception");
        }
        
        cursor.close();        
        //һ��Ҫ��ʱ�ر����ݿ⣬�������ʾ�ڴ�й¶
        db.close();
	}
    
    //--------------------------------------------------------------------------
    //��Ӽ�¼
    private void addItem(String index, String name, String type) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(mColumnNames[0], index);
        item.put(mColumnNames[1], name);
        item.put(mColumnNames[2], type);
        mItems.add(item);    	
        
        SQLiteDBAct.printLog(this, "Add item: " + index + "," + name + "," + type);
    }      
    
    //--------------------------------------------------------------------------    
};