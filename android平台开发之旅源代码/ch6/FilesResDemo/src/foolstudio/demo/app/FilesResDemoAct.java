package foolstudio.demo.app;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FilesResDemoAct extends Activity implements OnClickListener {
	
	private Button mBtnRaw = null;
	private Button mBtnXml = null;
	private EditText mTxtContents = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnRaw = (Button)findViewById(R.id.BTN_INIT);
        mBtnXml = (Button)findViewById(R.id.BTN_ACTION);
        //
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);        
        
        mBtnRaw.setOnClickListener(this);
        mBtnXml.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_INIT: {
				clearText();
				doRaw();
				break;
			}	
			case R.id.BTN_ACTION: {
				clearText();
				doXml();
				break;
			}		
		}
	}

	//读取原始文件	
	private void doRaw() {
		// TODO Auto-generated method stub
		InputStream is = this.getResources().openRawResource(R.raw.demo);
		try {
			doRead(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//通过输入流进行读取
	private void doRead(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		DataInputStream dis = new DataInputStream(is);
		byte[] buffer = new byte[is.available()];
		dis.readFully(buffer);
		
		printText(new String(buffer, "GBK"));

		dis.close();
		is.close();
	}

	private void doXml() {
		// TODO Auto-generated method stub
		XmlResourceParser parser = this.getResources().getXml(R.xml.db_setting);
		try {
			doParse(parser);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//通过XML解析器进行解析
	private void doParse(XmlPullParser parser) throws XmlPullParserException, IOException {
		// TODO Auto-generated method stub
        int eventType = parser.getEventType();
        
        while (true) {
        	switch(eventType) {
	        	case XmlPullParser.END_DOCUMENT: {
	        		printText("End document");
	        		return;
	        		//break;
	        	}        	
	        	case XmlPullParser.END_TAG: {
	        		printText("</"+parser.getName()+">");
	        		break;
	        	}
	        	case XmlPullParser.START_DOCUMENT: {
	        		printText("Start document");
	        		break;
	        	}
	        	case XmlPullParser.START_TAG: {
	        		parserTag(parser);	        		
	        		break;
	        	}
	        	case XmlPullParser.COMMENT: {
	        		parseComment(parser);
	        		break;
	        	}
	        	case XmlPullParser.TEXT: {
	        		if(parser.isWhitespace() ) { //掠过空白
	        			break;
	        		}
	        		else {
	        			printText(parser.getText() );
	        		}
	        		break;
	        	}
        	}
        	
        	eventType = parser.nextToken();
        }
	}
	
	//解析注释
	private void parseComment(XmlPullParser parser) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("<!--");
		sb.append(parser.getText() );
		sb.append("-->");
		
		printText(sb.toString() );
	}

	//解析标记
	private void parserTag(XmlPullParser parser) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append('<');
		sb.append(parser.getName() );
		
		int attrsCount = parser.getAttributeCount();
		for(int i = 0; i < attrsCount; ++i) {
			sb.append(' ');
			sb.append(parser.getAttributeName(i) );
			sb.append("=\"");
			sb.append(parser.getAttributeValue(i) );
			sb.append("\"");
		}
		
		sb.append('>');
		
		printText(sb.toString() );
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