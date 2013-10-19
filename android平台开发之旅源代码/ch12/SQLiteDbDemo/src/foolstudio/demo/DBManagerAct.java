package foolstudio.demo;

//����
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

//���ݿ����
public class DBManagerAct extends ListActivity {
	//����ʾ���ID����
	private int[] mRowViewIds = {
		R.id.txtType,
		R.id.txtName
	};
	
	private String[] mColumnNames = {
		"type",
		"tbl_name"
	};
	
	public static final String INTENT_EXTRAS_NAME = "tbl_name";
	
	private List<Map<String,String>> mItems = null;
	
	private SQLiteDatabase mDB = null;
	
    //--------------------------------------------------------------------------	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_man_view);
        
        initList();
               
        //�����б�����������
        ListAdapter adapter = new SimpleAdapter(DBManagerAct.this,
        		mItems,
        		R.layout.db_schema_row_view,        		
        		mColumnNames,
        		mRowViewIds);
        
        //����������������������
        setListAdapter(adapter);
        
        //���������Ĳ˵�
        registerForContextMenu(getListView() );
    }
    
    //--------------------------------------------------------------------------
    //��ʼ���б���Ŀ
    private void initList() {
        mItems = new ArrayList<Map<String,String>>();
        
        //�򿪻��ߴ�����
        mDB = SQLiteDatabase.openOrCreateDatabase(SQLiteDBAct.DB_NAME, null);
        
        Cursor c = mDB.query("sqlite_master", null, "(0>1)", null, null, null, null);
    	String[] columnNames = c.getColumnNames();
    	c.close();
    	
    	for(int i = 0; i < columnNames.length; ++i) {
    		Log.d("#"+i, columnNames[i]);
    	}

        
        Cursor cursor = mDB.query("sqlite_master", mColumnNames, 
        									  null, null, null, null, null);
        
        if( (cursor == null) || (cursor.getCount() < 1) ) {
        	return;
        }        
        
        //һ��Ҫ�α긴λ�����������ȡ���׳��쳣
        cursor.moveToFirst();
        
        while(!cursor.isAfterLast()) {
        	addItem(cursor.getString(0), cursor.getString(1));
        	cursor.moveToNext();
        }
        
        cursor.close();
	}
    
    //--------------------------------------------------------------------------
    @Override
    public void onDestroy() {
    	//һ��Ҫ���ó���ķ�����������׳��쳣
    	super.onDestroy();
    	
    	if(mDB.isOpen() ) {
    		mDB.close();
    	}
    }
    
    //--------------------------------------------------------------------------
    //��Ӽ�¼
    private void addItem(String type, String name) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(mColumnNames[0], type);
        item.put(mColumnNames[1], name);     
        mItems.add(item);    	
    }    
    
    //--------------------------------------------------------------------------
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Map<String,String> map = (HashMap<String,String>)(mItems.get(position) );
    	
    	String type =  map.get(mColumnNames[0]).toString();
    	String name = map.get(mColumnNames[1]).toString();
    	
    	/*
    	//������
    	Toast toast = Toast.makeText(this, 
    			map.get(mColumnNames[0]).toString() + ", " + 
    			map.get(mColumnNames[1]).toString(), 
    			Toast.LENGTH_LONG);
    	toast.show();
    	*/
    	
    	if(type.equalsIgnoreCase("table")) {
    		Intent intent = new Intent(DBManagerAct.this, 
    												TableSchemaViewerAct.class);
    		intent.putExtra(INTENT_EXTRAS_NAME, name);
    		startActivity(intent);
    	}
    }
    
	//--------------------------------------------------------------------------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, 
    												  ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.db_man_context_menu, menu);
    }
    
	//--------------------------------------------------------------------------
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = 
    		(AdapterContextMenuInfo)item.getMenuInfo();
    	switch (item.getItemId()) {
    		case R.id.itemCreate:
    			createTable();
	    		return true;
	    	case R.id.itemDrop:
	    	    dropTable(info.position);
	    	    return true;
	    	default:
	    	    return super.onContextItemSelected(item);    			
    	}
    }    
    
	//--------------------------------------------------------------------------
    //ɾ�������Ĳ˵���ָ�������ݱ�
    private void dropTable(int position) {
    	Map<String,String> map = 
    			(HashMap<String,String>)(mItems.get(position) );
    	
    	String type =  map.get(mColumnNames[0]).toString();
    	String name = map.get(mColumnNames[1]).toString();
    	
    	if(type.equalsIgnoreCase("table")) {
    		/*
        	Toast toast = Toast.makeText(this, "Drop table" + name + "?", 
        								 Toast.LENGTH_LONG);
        	toast.show();
        	*/
    		mDB.execSQL("drop table " + name);
    		//Refresh
    		DBManagerAct.this.finish();
    	}    	
    }
    
	//--------------------------------------------------------------------------
    //�������ݱ�
    private void createTable() {
    	Intent intent = new Intent(DBManagerAct.this, TableCreatorAct.class);
    	startActivity(intent);
    }
	
	//--------------------------------------------------------------------------	
};