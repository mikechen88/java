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

//表结构查看活动
public class UserInfoViewerAct extends ListActivity {
	//行显示组件ID数组
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
        
        //获取参数
        Bundle extras = getIntent().getExtras();
        String userID = extras.getString(LoginAct.INTENT_EXTRAS_NAME);
        //
        
        initList(userID);
        
        //创建列表数据适配器
        ListAdapter adapter = new SimpleAdapter(UserInfoViewerAct.this,
        		mItems,
        		R.layout.user_info_row_view,        		
        		mColumnNames,
        		mRowViewIds);
        
        //设置数据适配器，绑定数据
        setListAdapter(adapter);        
    }
    
    //--------------------------------------------------------------------------
    //初始化列表项目
    private void initList(String userID) {
        mItems = new ArrayList<Map<String, String>>();
        
        SQLiteDatabase db = 
        	SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
        											SQLiteDatabase.OPEN_READONLY);        
        Cursor cursor = db.query("user_info", null, 
        		"(ui_id='"+userID+"')", null, null, null, null);
        
        if(cursor.getCount() == 1) {        	
        	//一定要游标复位，否则后续读取会抛出异常
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
        //一定要及时关闭数据库，否则会提示内存泄露
        db.close();
	}
    
    //--------------------------------------------------------------------------
    //添加记录
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