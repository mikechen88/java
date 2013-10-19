package foolstudio.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FileSystemDemoAct extends Activity implements OnClickListener {
	
	private final String BASE_DIR = "/sdcard";
	private final String NEW_DIR = "foolstudio";
	private final String NEW_FILE_NAME = "readme.txt";
	
	private final String DEST_DIR = BASE_DIR + File.separator + NEW_DIR;
	private final String DEST_FILE_NAME = DEST_DIR + File.separator + NEW_FILE_NAME;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnCreateFolder = (Button)findViewById(R.id.BTN_CREATE_FOLDER);
        Button btnCreateFile = (Button)findViewById(R.id.BTN_CREATE_FILE);
        Button btnReadFile = (Button)findViewById(R.id.BTN_READ_FILE);
        Button btnListFolder = (Button)findViewById(R.id.BTN_LIST_FILES);
        //
        btnCreateFolder.setOnClickListener(this);
        btnCreateFile.setOnClickListener(this);
        btnReadFile.setOnClickListener(this);
        btnListFolder.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_CREATE_FOLDER: {
				doCreateFolder();
				break;
			}
			case R.id.BTN_CREATE_FILE: {
				doCreateFile();
				break;
			}
			case R.id.BTN_READ_FILE: {
				doReadFile();
				break;
			}
			case R.id.BTN_LIST_FILES: {
				doListFiles();
				break;
			}			
		}
	}

	//创建文件夹
	private void doCreateFolder() {
		// TODO Auto-generated method stub
		File file = new File(DEST_DIR);
		
		if(file.exists() == false) { //不存在
			file.mkdir();
			
			Log.d(getClass().getName(), "Create Folder "+DEST_DIR+" OK!");			
		}
		else {
			Log.d(getClass().getName(), "Folder "+DEST_DIR+" already exists!");			
		}
	}

	//创建文件
	private void doCreateFile() {
		// TODO Auto-generated method stub
		File aFile = new File(DEST_FILE_NAME);
		
		if(aFile.exists() == false) { //不存在
			try {
				aFile.createNewFile();				
				writeToFile(aFile);				
				
				Log.d(getClass().getName(), "Create file "+DEST_FILE_NAME+" OK!");				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else {
			Log.d(getClass().getName(), "File "+DEST_FILE_NAME+" already exists!");			
		}
	}	

	//写文件
	private void writeToFile(File file) {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("This is a demonstration of Android file system.");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//读取文件
	private void doReadFile() {
		// TODO Auto-generated method stub
		File aFile = new File(DEST_FILE_NAME);
		
		if(aFile.exists() == true) { //存在
			try {
				FileReader fr = new FileReader(aFile);
				BufferedReader br = new BufferedReader(fr);
				String firstLine = br.readLine();
				br.close();
				fr.close();
				
				Log.d(getClass().getName(), "Contents of file "+aFile.getName()+
											": "+firstLine);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Log.d(getClass().getName(), "File "+DEST_FILE_NAME+" not exists!");			
		}		
	}

	//列举指定文件夹中的内容
	private void doListFiles() {
		// TODO Auto-generated method stub		
		listFolder(DEST_DIR);
	}
	
	//遍历指定目录中的内容（包括子文件夹）
	private void listFolder(String path) {
		File aFile = new File(path);
		
		if(aFile.exists() == false) {
			Log.d(getClass().getName(), "Path "+path+" invalid!");
			return;
		}
		
		if(aFile.isDirectory() ) { //文件夹
			Log.d(getClass().getName(), "Contents of file "+aFile.getName()+": ");
			
			String fileNames[] = aFile.list();
			
			for(int i = 0; i < fileNames.length; ++i) {
				listFolder(aFile.getPath() + File.separator + fileNames[i]);
			}
		}
		else if(aFile.isFile() ) { //文件
			Log.d(getClass().getName(), aFile.getName() );
		}
	}
	
	//--------------------------------------------------------------------------
};