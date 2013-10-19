package foolstudio.demo.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LayoutInflaterServiceDemoAct extends Activity implements 
														   OnClickListener {
	private Button mBtnHandler = null;
	private Button mBtnService = null;
	private Button mBtnRes = null;
	private Button mBtnFs = null;
	private ViewGroup mContentView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mContentView = (ViewGroup)findViewById(R.id.LAY_CONTENT);
        mBtnHandler = (Button)findViewById(R.id.BTN_HANDLER);
        mBtnService = (Button)findViewById(R.id.BTN_SERVICE);  
        mBtnRes = (Button)findViewById(R.id.BTN_RES);
        mBtnFs = (Button)findViewById(R.id.BTN_FS);
        
        mBtnHandler.setOnClickListener(this);
        mBtnService.setOnClickListener(this);
        mBtnRes.setOnClickListener(this);
        mBtnFs.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_HANDLER: {
				byHandler();
				break;
			}	
			case R.id.BTN_SERVICE: {
				byService();
				break;
			}		
			case R.id.BTN_RES: {
				viaRes();
				break;
			}	
			case R.id.BTN_FS: {
				try {
					viaFs();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				break;
			}			
			//子面板的命令相应
			case R.id.BTN_ACTION: {
				doAction((String)v.getTag() );
				break;
			}
			case R.id.BTN_HIDE: {
				doHide();
				break;
			}			
		}
	}

	//通过文件系统
	private void viaFs() throws XmlPullParserException, IOException {
		// TODO Auto-generated method stub
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		StringReader sr = new StringReader(getTexts() );
		parser.setInput(sr);
		
		LayoutInflater inflater = getLayoutInflater();
		inflater.inflate(parser, null);		
		
		View panel = inflater.inflate(parser, null);
		
		sr.close();		
		
		if( (panel != null) && !isAdded() ) {
			addSubPanel(panel, "viaFileSystem");
		}		
	}

	private String getTexts() throws IOException {
		// TODO Auto-generated method stub
		InputStream is = this.getResources().openRawResource(R.raw.sample);		
		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while((line=br.readLine()) != null) {
			sb.append(line);
		}
		
		br.close();
		is.close();
		
		return (sb.toString() );
	}

	//通过资源
	private void viaRes() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = getLayoutInflater();
		XmlResourceParser parser = this.getResources().getXml(R.layout.widgets);
		
		View panel = inflater.inflate(parser, null);
		
		if( (panel != null) && !isAdded() ) {
			addSubPanel(panel, "viaResource");
		}		
	}

	private void doHide() {
		// TODO Auto-generated method stub
		if(isAdded() ) {
			mContentView.removeView(findViewById(R.id.LAY_SUB_PANEL) );
		}
	}

	private void doAction(String tag) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Hello, "+tag+"!", Toast.LENGTH_LONG).show();
	}

	//通过处理器
	private void byHandler() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = this.getLayoutInflater();
		View panel = inflater.inflate(R.layout.widgets, null);	
		if( (panel != null) && !isAdded() ) {
			addSubPanel(panel, "byHandler");
		}
	}
	
	//判断是否已经添加
	private boolean isAdded() {
		// TODO Auto-generated method stub
		View view = findViewById(R.id.LAY_SUB_PANEL);
		return(view != null);
	}
	
	//添加子面板
	private void addSubPanel(View panel, String tag) {
		mContentView.addView(panel);
		//必须要等其父视图添加之后才能find子视图
		Button btnAction =(Button)findViewById(R.id.BTN_ACTION);
		Button btnHide = (Button)findViewById(R.id.BTN_HIDE);
		//
		if(btnAction != null) {
			btnAction.setOnClickListener(this);
			btnAction.setTag(tag);
		}
		if(btnHide != null) {
			btnHide.setOnClickListener(this);
		}			
	}

	//通过服务
	private void byService() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = 
			(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View panel = 
			inflater.inflate(getResources().getLayout(R.layout.widgets), null);
		if( (panel != null) && !isAdded() ) {
			addSubPanel(panel, "byService");
		}
	}	
};