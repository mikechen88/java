package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class db4oJournalBookAct extends Activity {
	
	public static final String DATABASE_NAME = "/sdcard/JournalBook.yap";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//充实菜单
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.options_menu, menu);
    	
    	//设置菜单意图
    	menu.findItem(R.id.MI_APPEND).setIntent(new Intent(this, AppendRecAct.class) );
    	menu.findItem(R.id.MI_REVIEW).setIntent(new Intent(this, ReviewRecAct.class) );
    	menu.findItem(R.id.MI_LOOKUP).setIntent(new Intent(this, LookupRecAct.class) );
    	//注意：必须要调用超类的方法，否则无法实现意图回调
    	return (super.onCreateOptionsMenu(menu) );
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {  	
    	switch(item.getItemId() ) {
	    	case R.id.MI_ABOUT: {
	    		doAbout();
	    		break;
	    	}        	
	    	case R.id.MI_QUIT: {
	    		doQuit();
	    		break;
	    	}
    	}
    	
		return (super.onOptionsItemSelected(item) );    	
    }
    
    private void doAbout() {
    	this.finish();
    }    
    
    private void doQuit() {
    	this.finish();
    }
};