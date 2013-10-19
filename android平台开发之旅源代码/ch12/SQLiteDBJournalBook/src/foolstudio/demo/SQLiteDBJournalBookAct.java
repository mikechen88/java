package foolstudio.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SQLiteDBJournalBookAct extends Activity {
	static final int QUIT_DLG = 0;		
	static final int ABOUT_DLG = 1;	
	//
	private AlertDialog mQuitDlg = null;
	private Dialog mAboutDlg = null;
	
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
    	menu.findItem(R.id.MI_CONFIG).setIntent(
    			new Intent(this, DBConfigAct.class) );    	
    	//
    	menu.findItem(R.id.MI_APPEND).setIntent(
    			new Intent(this, AppendRecAct.class) );
    	menu.findItem(R.id.MI_REVIEW).setIntent(
    			new Intent(this, ReviewRecAct.class) );
    	menu.findItem(R.id.MI_LOOKUP).setIntent(
    			new Intent(this, LookupRecAct.class) );
    	//注意：必须要调用超类的方法，否则无法实现意图回调
    	return (super.onCreateOptionsMenu(menu) );
    }
    
    //--------------------------------------------------------------------------
	//初始化对话框
    protected Dialog onCreateDialog(int id) {
        switch(id) {
	        case QUIT_DLG:
	        	return (initQuitDlg() ); 
	        case ABOUT_DLG:
	        	return (initAboutDlg() );   	        	
	        default:
	            return null;
        }
    }    

	private Dialog initQuitDlg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//提示信息
		builder.setMessage("Exit program?");
		builder.setCancelable(false);
		//是按钮
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				SQLiteDBJournalBookAct.this.finish();
			}				
		});
		//否按钮
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
				
		});
		
		mQuitDlg = builder.create();
        return (mQuitDlg);   
	}
	
    private Dialog initAboutDlg() {
		// TODO Auto-generated method stub
    	mAboutDlg = new Dialog(this);
    	mAboutDlg.setContentView(R.layout.about_view);
    	mAboutDlg.setTitle("About JournalBook");
    	
		return (mAboutDlg);
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
    	showDialog(ABOUT_DLG);    	
    }    
    
    private void doQuit() {
    	showDialog(QUIT_DLG);
    }
};