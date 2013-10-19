package foolstudio.demo.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class FooContentHandler implements ContentHandler {
	
	private Handler mHandler = null;

	public FooContentHandler(Handler handler) {
		// TODO Auto-generated constructor stub
		this.mHandler = handler;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		
		if(!localName.equalsIgnoreCase("Score") ) { //过滤节点
			return;
		}

		//输出分数信息
		for(int i = 0; i < atts.getLength(); ++i) {
			showResponse(atts.getLocalName(i)+"="+atts.getValue(i) );
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}
	
	//发送消息到界面线程消息队列中
	public void showResponse(String data) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		//bundle.putCharSequence("Sender", "FooContentHandler");
		bundle.putString("MSG", data);
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);		
	}
};
