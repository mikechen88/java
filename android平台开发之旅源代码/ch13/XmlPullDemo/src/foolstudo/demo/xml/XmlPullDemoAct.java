package foolstudo.demo.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class XmlPullDemoAct extends Activity {
	
	private static final String XML_FILE_PATH = "/sdcard/TS.xml";
	
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser = factory.newPullParser();
			
			FileInputStream is = new FileInputStream(XML_FILE_PATH);
			parser.setInput(is, "utf-8");
			
			doParse(parser);
			
			is.close();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//执行解析行为
	private void doParse(XmlPullParser parser) throws XmlPullParserException, 
													  IOException {
		// TODO Auto-generated method stub
        int eventType = parser.getEventType();
        
        while (true) {
        	switch(eventType) {
	        	case XmlPullParser.END_DOCUMENT: {
	        		printLog("End document");
	        		return;
	        		//break;
	        	}        	
	        	case XmlPullParser.END_TAG: {
	        		printText("</"+parser.getName()+">");
	        		break;
	        	}
	        	case XmlPullParser.START_DOCUMENT: {
	        		printLog("Start document");
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

	//打印文本
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
	
	//输入日志
	private void printLog(String text) {
		Log.d(this.getClass().getName(), text);
	}	
	
	//--------------------------------------------------------------------------
};