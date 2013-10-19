package foolstudio.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.app.Application;
import android.content.Context;
import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.Display;

public class SimpleActivity extends Activity {	
	private static final String TAG = "SIMPLE_ACT";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.i(TAG, "Activity creating...");
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	
    	Log.i(TAG, "Activity starting...");
    	
    	showInfo();
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	
    	Log.i(TAG, "Activity stopping...");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();

    	Log.i(TAG, "Activity destroying...");
    }
    
    //��ʾ��Ϣ
    private void showInfo() {
    	Log.i(TAG, "Title: " + this.getTitle().toString() );
    	Log.i(TAG, "Calling activity: " + this.getCallingPackage() );
    	
    	//��ȡӦ�ó���ʵ��
    	Application app = this.getApplication();
    	Log.i(TAG, "Package:" + app.getPackageName() );
    	//��ȡӦ�ó���������ʵ��
    	Context appContext = this.getApplicationContext();
    	Log.i(TAG, "AppContext: " + appContext.toString() );
    	//��ȡ�ʲ�������
    	AssetManager am = this.getAssets();
    	Log.i(TAG, "AssetManager: " + am.getLocales()[1]);
    	//��ȡ����������
    	Context baseContext = this.getBaseContext();
    	Log.i(TAG, "BaseContext: " + baseContext.toString() );
    	//���ݽ����
    	ContentResolver resolver = this.getContentResolver();
    	Log.i(TAG, "Resolver: " + resolver.toString() );
    	//���������
    	LayoutInflater inflater = this.getLayoutInflater();
    	Log.i(TAG, "LayoutInflater: " + inflater.toString() );
    	//�˵������
    	MenuInflater inflater2 = this.getMenuInflater();
    	Log.i(TAG, "MenuInflater: " + inflater2.toString() );
    	//��Դ������
    	Resources resources = this.getResources();
    	Log.i(TAG, "Resources: " + resources.toString() );
    	//����
    	Drawable wallpaper = this.getWallpaper();
    	Log.i(TAG, "Wallpaper: " + wallpaper.toString() );
    	//��ȡ����ʵ��
    	Window window = this.getWindow();
    	LayoutParams layoutParams = window.getAttributes();
    	
    	Log.i(TAG, "Layout height: " + layoutParams.height + 
    				", width: " + layoutParams.width);
    	//��ȡ���������
    	WindowManager wm = this.getWindowManager();
    	Display display = wm.getDefaultDisplay();
    	Log.i(TAG, "Window height: " + display.getHeight() +
    				", width: " + display.getWidth() );
    }
};