package com.szy.xml.service;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.szy.xml.handler.XMLContentHandler;
import com.szy.xml.model.Person;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class SAXPersonService
{
	public static List<Person> readXml(InputStream inStream) throws Exception
	{
		SAXParserFactory spf=SAXParserFactory.newInstance();
		SAXParser saxParser=spf.newSAXParser();//´´½¨½âÎöÆ÷
		XMLContentHandler handler=new XMLContentHandler();
		saxParser.parse(inStream, handler);
		inStream.close();
		return handler.getPersons();
	}
}
