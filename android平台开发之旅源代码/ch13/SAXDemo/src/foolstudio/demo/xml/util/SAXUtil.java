package foolstudio.demo.xml.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import foolstudio.demo.xml.common.District;

public class SAXUtil {
	private static SAXUtil mInstance = new SAXUtil();
	
	private SAXUtil() {
	}
	
	public static SAXUtil getInstance() {
		return (mInstance);
	}
	
	public void parse(InputStream is, District district) {
		//获取解析工厂实例和ＳＡＸ解析实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        
        try {
	        SAXParser parser = factory.newSAXParser();
	        parser.parse(is, new ParseHandler(district) );
        } catch(SAXException e) {
        	e.printStackTrace();
        } catch(ParserConfigurationException e) {
        	e.printStackTrace();
        } catch(IOException e) {
        	e.printStackTrace();
        }        
	}
};
