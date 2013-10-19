package foolstudio.demo.xml;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FooXmlParser {
	
	private XmlResourceParser mParser = null;
	private Handler mHandler = null;	

	public FooXmlParser(XmlResourceParser parser, Handler handler) {
		// TODO Auto-generated constructor stub
		this.mHandler = handler;
		this.mParser = parser;
	}

	public void parse() throws XmlPullParserException, IOException {
		// TODO Auto-generated method stub
        int eventType = mParser.getEventType();
        
        while (true) {
        	switch(eventType) { //�ַ������¼�
	        	case XmlPullParser.END_DOCUMENT: {
	        		//showResponse("End document");
	        		return;
	        		//break;
	        	}        	
	        	case XmlPullParser.END_TAG: {
	        		showResponse("</"+mParser.getName()+">");
	        		break;
	        	}
	        	case XmlPullParser.START_DOCUMENT: {
	        		//showResponse("Start document");
	        		break;
	        	}
	        	case XmlPullParser.START_TAG: {
	        		parserTag(mParser);	        		
	        		break;
	        	}
	        	case XmlPullParser.COMMENT: {
	        		parseComment(mParser);
	        		break;
	        	}
	        	case XmlPullParser.TEXT: {
	        		if(mParser.isWhitespace() ) { //�ӹ��հ�
	        			break;
	        		}
	        		else {
	        			showResponse(mParser.getText() );
	        		}
	        		break;
	        	}
        	}
        	
        	eventType = mParser.nextToken();
        }		
	}
	
	//����ע�ͽڵ�
	private void parseComment(XmlPullParser parser) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("<!--");
		sb.append(parser.getText() );
		sb.append("-->");
		
		showResponse(sb.toString() );
	}

	//������ͨ�ڵ�
	private void parserTag(XmlPullParser parser) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append('<');
		sb.append(parser.getName() );
		
		int attrsCount = parser.getAttributeCount();
		for(int i = 0; i < attrsCount; ++i) { //��������
			sb.append(' ');
			sb.append(parser.getAttributeName(i) );
			sb.append("=\"");
			sb.append(parser.getAttributeValue(i) );
			sb.append("\"");
		}
		
		sb.append('>');
		
		showResponse(sb.toString() );
	}	
	
	//������Ϣ�������߳���Ϣ������
	public void showResponse(String data) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		//bundle.putCharSequence("Sender", "FooXmlParser");
		bundle.putString("MSG", data);
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);		
	}	

}
