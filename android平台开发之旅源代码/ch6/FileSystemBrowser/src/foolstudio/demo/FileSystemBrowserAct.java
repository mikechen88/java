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
        //̧ͷ
        mTitle = (TextView)findViewById(R.id.LAB_TITLE);        
        mTitle.setText("Contents of file system");
        
        //��ʼ�����ݼ�
        mContents.add(new FileInfo(ROOT_DIR, true) );       
        
        //����������
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
		
		if(path.endsWith(PARENT_DIR) ) { //�����ϲ��ļ���
			if(mCurDir.endsWith(ROOT_DIR) ) { //�Ѿ��Ǹ�Ŀ¼
				mCurDir = "";
			}
			else {
				int index = mCurDir.lastIndexOf(File.separator);
				
				if(index == 0) { //��һ��Ϊ���ļ���
					mCurDir = ROOT_DIR;					
				}
				else { //��һ��Ҳ�Ǹ��ļ���
					mCurDir = mCurDir.substring(0, index);					
				}				
			}
			
			updateNotify(l);
		}
		else if(path == ROOT_DIR) { //���ļ���
			mCurDir = mCurDir + path;
			updateNotify(l);					
		}
		else {			
			File aFile = new File(mCurDir + File.separator + path);
			
			if(aFile.isDirectory() ) { //���ļ���
				
				if(mCurDir.endsWith(File.separator) ) {
					mCurDir = mCurDir + path;
				}
				else {		
					mCurDir = mCurDir + File.separator + path;
				}
				updateNotify(l);
			}
			else { //�ļ�
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
    	if(mCurDir == "") { //�ļ�ϵͳ
    		mContents.clear();
    		mContents.add(new FileInfo(ROOT_DIR, true) );
    	}
    	else {
			listFolder(mCurDir);
			mContents.add(0, new FileInfo(PARENT_DIR, true) );
		}
		
		//���ݼ�������ʾ
		((ArrayAdapter)l.getAdapter()).notifyDataSetChanged();
		mTitle.setText("Contents of " + 
				((mCurDir.length() < 1) ? "file system" : mCurDir) ); 	
    }

	//--------------------------------------------------------------------------
	//����ָ��Ŀ¼�е����ݣ����������ļ��У�
	private void listFolder(String path) {
		File aFile = new File(path);
		
		if(aFile.exists() == false) {
			Log.d(this.getClass().getName(), "�ļ� " + path + "�����ڣ�");
			return;
		}
		
		//��ʼ�����ݼ�
		mContents.clear();
		
		if(aFile.isDirectory() ) { //�ļ���
			Log.d(this.getClass().getName(), "Contents of " + aFile.getName() );
			
			String files[] = aFile.list();
			for(int i = 0; i < files.length; ++i) {
				File subFile = null;
				
				if(path.endsWith(ROOT_DIR) == true) { //���ļ���
					subFile = new File(path + files[i]);
				}
				else { //���ļ���
					subFile = new File(path + File.separator + files[i]);
					
					//������
					System.out.println(path + File.separator + files[i] + 
							" is dir=" + subFile.isDirectory() );
				}
				
				mContents.add(new FileInfo(files[i], subFile.isDirectory()) );
			}
		}
		else if(aFile.isFile() ) { //�ļ�
			Log.d(this.getClass().getName(), aFile.getName() );
			
			mContents.add(new FileInfo(aFile.getName(), false) );
		}
	} 
	
    //--------------------------------------------------------------------------	
};