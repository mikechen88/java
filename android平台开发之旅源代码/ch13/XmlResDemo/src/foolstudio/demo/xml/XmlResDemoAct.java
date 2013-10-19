package foolstudio.demo.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XmlResDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnInit = null;
	private Button mBtnAction = null;
	private Button mBtnUninit = null;	
	private EditText mTxtContents = null;	
	//
	private Handler mHandler = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnInit = (Button)findViewById(R.id.BTN_INIT);
        mBtnAction = (Button)findViewById(R.id.BTN_ACTION);
        mBtnUninit = (Button)findViewById(R.id.BTN_UNINIT);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnInit.setOnClickListener(this);
        mBtnAction.setOnClickListener(this);
        mBtnUninit.setOnClickListener(this);  
        
        mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Bundle bundle = msg.getData();
				//String sender = bundle.getString("Sender");
				String data = bundle.getString("MSG"); 
				
				printText(//sender+"|"+
						  data);		
				
				super.handleMessage(msg);
			}
        };
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
				doLayoutXml();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doRawXml();
				break;
			}			
			case R.id.BTN_UNINIT: {
				clearText();
				doGenericXml();
				break;
			}		
		}
	}

	//执行布局XML文件操作
	private void doLayoutXml() {
		// TODO Auto-generated method stub
		//通过布局填充器来充实所定义的布局XML
		View v = this.getLayoutInflater().inflate(R.layout.sample, null);
		Button btnAction = (Button)v.findViewById(R.id.BTN_ACTION);
		//创建对话框并设定其内容视图
		final Dialog dlg = createDialogBy(v);
		btnAction.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});
		dlg.show();		
	}

	//使用指定视图来创建对话框
	private Dialog createDialogBy(View v) {
		// TODO Auto-generated method stub
		Dialog dlg = new Dialog(this);
		dlg.setTitle("Custom Dialog");
		dlg.setCancelable(false);
		dlg.setContentView(v);
		
		return (dlg);
	}

	//处理XML原文件资源
	private void doRawXml() {
		// TODO Auto-generated method stub
		InputStream is = this.getResources().openRawResource(R.raw.sample);
		try {
			//通过指定的内容处理器来解析XML
			Xml.parse(is, Xml.Encoding.UTF_8, new FooContentHandler(mHandler));
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//处理通常XML资源
	private void doGenericXml() {
		// TODO Auto-generated method stub
		XmlResourceParser parser = this.getResources().getXml(R.xml.sample);
		
		try {
			new FooXmlParser(parser, mHandler).parse();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void clearText() {
		mTxtContents.setText("");
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	

	//--------------------------------------------------------------------------
};