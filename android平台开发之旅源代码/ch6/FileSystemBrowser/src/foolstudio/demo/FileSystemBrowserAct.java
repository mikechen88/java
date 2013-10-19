package foolstudio.demo;

import java.io.File;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FileSystemBrowserAct extends ListActivity {
	
	public static final String ROOT_DIR = File.separator;
	public static final String PARENT_DIR = "..";
	
	private TextView mTitle = null;
	private String mCurDir = "";
	private ArrayList<FileInfo> mContents = new ArrayList<FileInfo>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        //抬头
        mTitle = (TextView)findViewById(R.id.LAB_TITLE);        
        mTitle.setText("Contents of file system");
        
        //初始化数据集
        mContents.add(new FileInfo(ROOT_DIR, true) );       
        
        //设置适配器
        //ListAdapter adapter = new ArrayAdapter<FileInfo>(this, 
        //		R.layout.item_view, R.id.LAB_FILE_NAME, mContents);
        ListAdapter adapter = new FileArrayAdapter(this, 
        		R.layout.item_view, R.id.LAB_FILE_NAME, mContents);
        this.setListAdapter(adapter);
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String path = l.getAdapter().getItem(position).toString();
		
		if(path.endsWith(PARENT_DIR) ) { //返回上层文件夹
			if(mCurDir.endsWith(ROOT_DIR) ) { //已经是根目录
				mCurDir = "";
			}
			else {
				int index = mCurDir.lastIndexOf(File.separator);
				
				if(index == 0) { //上一级为根文件夹
					mCurDir = ROOT_DIR;					
				}
				else { //上一级也非根文件夹
					mCurDir = mCurDir.substring(0, index);					
				}				
			}
			
			updateNotify(l);
		}
		else if(path == ROOT_DIR) { //根文件夹
			mCurDir = mCurDir + path;
			updateNotify(l);					
		}
		else {			
			File aFile = new File(mCurDir + File.separator + path);
			
			if(aFile.isDirectory() ) { //子文件夹
				
				if(mCurDir.endsWith(File.separator) ) {
					mCurDir = mCurDir + path;
				}
				else {		
					mCurDir = mCurDir + File.separator + path;
				}
				updateNotify(l);
			}
			else { //文件
				if(mCurDir.endsWith(File.separator) ) {
					Toast.makeText(this, mCurDir+path, Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(this, 
							mCurDir+File.separator+path, Toast.LENGTH_LONG).show();
				}
			}
		}
	}
    
	//--------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
	private void updateNotify(ListView l) {
    	if(mCurDir == "") { //文件系统
    		mContents.clear();
    		mContents.add(new FileInfo(ROOT_DIR, true) );
    	}
    	else {
			listFolder(mCurDir);
			mContents.add(0, new FileInfo(PARENT_DIR, true) );
		}
		
		//数据集更新提示
		((ArrayAdapter)l.getAdapter()).notifyDataSetChanged();
		mTitle.setText("Contents of " + 
				((mCurDir.length() < 1) ? "file system" : mCurDir) ); 	
    }

	//--------------------------------------------------------------------------
	//遍历指定目录中的内容（不包括子文件夹）
	private void listFolder(String path) {
		File aFile = new File(path);
		
		if(aFile.exists() == false) {
			Log.d(this.getClass().getName(), "文件 " + path + "不存在！");
			return;
		}
		
		//初始化数据集
		mContents.clear();
		
		if(aFile.isDirectory() ) { //文件夹
			Log.d(this.getClass().getName(), "Contents of " + aFile.getName() );
			
			String files[] = aFile.list();
			for(int i = 0; i < files.length; ++i) {
				File subFile = null;
				
				if(path.endsWith(ROOT_DIR) == true) { //根文件夹
					subFile = new File(path + files[i]);
				}
				else { //子文件夹
					subFile = new File(path + File.separator + files[i]);
					
					//调试用
					System.out.println(path + File.separator + files[i] + 
							" is dir=" + subFile.isDirectory() );
				}
				
				mContents.add(new FileInfo(files[i], subFile.isDirectory()) );
			}
		}
		else if(aFile.isFile() ) { //文件
			Log.d(this.getClass().getName(), aFile.getName() );
			
			mContents.add(new FileInfo(aFile.getName(), false) );
		}
	} 
	
    //--------------------------------------------------------------------------	
};