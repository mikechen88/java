package foolstudio.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

//表结构查看活动
public class TableSchemaViewerAct extends ListActivity {
	//行显示组件ID数组
	private int[] mRowViewIds = {
		R.id.txtColIndex,
		R.id.txtColName,
		R.id.txtColType
	};
	
	private String[] mColumnNames = {
			"field_index",
			"field_name",
			"field_type"
		};	
	
	private List<Map<String,String>> mItems = null;	
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_schema_viewer_view);
        
        //获取参数
        Bundle extras = getIntent().getExtras();
        String table_name = 
        	extras.getString(DBManagerAct.INTENT_EXTRAS_NAME);
        //
        
        initList(table_name);
        
        //创建列表数据适配器
        ListAdapter adapter = new SimpleAdapter(TableSchemaViewerAct.this,
        		mItems,
        		R.layout.table_schema_row_view,        		
        		mColumnNames,
        		mRowViewIds);
        
        //设置数据适配器，绑定数据
        setListAdapter(adapter);        
    }
    
    //--------------------------------------------------------------------------
    //初始化列表项目
    private void initList(String table_name) {
        mItems = new ArrayList<Map<String, String>>();
        
        String[] columnNames = {"sql"};
        
        SQLiteDatabase db = 
        	SQLiteDatabase.openDatabase(SQLiteDBAct.DB_NAME, null, 
        											   SQLiteDatabase.OPEN_READONLY);        
        Cursor cursor = db.query("sqlite_master", columnNames, 
        		"(tbl_name='"+table_name+"')", null, null, null, null);
        
        if(cursor.getCount() == 1) {        	
        	//一定要游标复位，否则后续读取会抛出异常
            cursor.moveToFirst();
            
        	String sql = cursor.getString(0);        	
        	String[] allColumnNames = getTableColumnNames(db, table_name);
        	
        	SQLiteDBAct.printLog(this, "SQL=" + sql);
        	
        	List<String> sqlParts = parseSQL(sql);
        	
        	for(int i = 0; i < allColumnNames.length; ++i) {
        		String type= getColumnTypeByName(sqlParts, allColumnNames[i]);
        		
        		addItem("#"+i, allColumnNames[i], type);
        	}
        }
        else {
        	SQLiteDBAct.printLog(this, "Get " + columnNames[0] + 
        				" from table " +  table_name + " ocur exception");
        }
        
        cursor.close();        
        //一定要及时关闭数据库，否则会提示内存泄露
        db.close();
	}
    
    //--------------------------------------------------------------------------
    //获取指定表名的全部列名
    private String[] getTableColumnNames(SQLiteDatabase db, final String tableName) {
    	Cursor cursor = db.query(tableName, null, "(0>1)", null, null, null, null);
    	String[] columnNames = cursor.getColumnNames();
    	cursor.close();
    	
    	return(columnNames);
    }
    
    //--------------------------------------------------------------------------
    //解析SQL语句
    private List<String> parseSQL(final String sql) {    	
    	Pattern p = Pattern.compile("[A-Za-z0-9_]+");
    	Matcher m = p.matcher(sql);
    	List<String> sqlParts = new ArrayList<String>();
    	
    	while(m.find() ) {
    		sqlParts.add(m.group() );
    	}
    	
    	return (sqlParts);
    }
    
    //--------------------------------------------------------------------------
    //获取指定列的类型
    private String getColumnTypeByName(List<String> items, String columnName) {
    	int index = indexOf(items, columnName);
    	
    	if(index != -1) {
    		String type = items.get(index+1).toString();
    		if(type.toUpperCase().indexOf("CHAR") != -1) { //VARCHAR或CHAR的情形
    			type += ("(" + items.get(index+2)+")"); 
    		}
    		
    		System.out.println("Type of column " + columnName + " is " + type);    		
    		return (type);
    	}
    	
    	System.out.println("Get type of column " + columnName + " ocur exception");    	
    	return("");
    }    
    
    //--------------------------------------------------------------------------
    //获取指定字符串在字符串列表中的位置（基于0，不在列表中则返回-1）
    private int indexOf(List<String> items, final String str) {
    	int itemsCount = items.size();
    	
    	for(int i = 0; i < itemsCount; ++i) {
    		if(items.get(i).compareTo(str) == 0) {
    			return (i);
    		}
    	}    	
    	
    	return(-1);
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
