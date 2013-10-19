package com.szy.xml.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.szy.xml.model.Person;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class PullPersonService
{
	public static List<Person> readXml(InputStream inStream) throws Exception
	{
		List<Person> persons=null;
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		int eventCode=parser.getEventType();
		Person person=null;
		while(eventCode!=XmlPullParser.END_DOCUMENT)
		{
			switch (eventCode)
			{
			case XmlPullParser.START_DOCUMENT: //0 文档开始事件
				persons=new ArrayList<Person>();
				break;
			case XmlPullParser.START_TAG:  //2 开始元素
				if ("person".equals(parser.getName()))
				{
					person=new Person();
					person.setId(new Integer(parser.getAttributeValue(0)));
				}
				else if(null!=person)
				{
					if ("name".equals(parser.getName()))
					{
						person.setName(parser.nextText());
					}
					else if("age".equals(parser.getName()))
					{
						person.setAge(new Short(parser.nextText()));
					}
				}
				break;
			case XmlPullParser.END_TAG: //结束元素
				if ("person".equals(parser.getName())&&person!=null)
				{
					persons.add(person);
					person=null;
				}
				break;
			}
			eventCode=parser.next();
		}
		return persons;
	}
}
