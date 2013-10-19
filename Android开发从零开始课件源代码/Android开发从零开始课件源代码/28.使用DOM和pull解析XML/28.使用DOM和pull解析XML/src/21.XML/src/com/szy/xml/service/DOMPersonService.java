package com.szy.xml.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.szy.xml.model.Person;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class DOMPersonService
{
	public static List<Person> readXml(InputStream inStream) throws Exception
	{
		List<Person> persons=new ArrayList<Person>();
		//实例化一个文档构建器工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//通过文档构建器工厂获取一个文档构建器
		DocumentBuilder builder = factory.newDocumentBuilder();
		//通过文档通过文档构建器构建一个文档实例
		Document document = builder.parse(inStream);
		Element root = document.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("pre:person");
		for(int i = 0 ;i < nodes.getLength();i++)
		{
			Element personElement = (Element)nodes.item(i);
			Person person =new Person();
			person.setId(Integer.valueOf(personElement.getAttribute("id")));
			NodeList childNodes = personElement.getChildNodes();
			for(int j = 0;j<childNodes.getLength();j++)
			{
				Node childNode = (Node)childNodes.item(j);
				if (childNode.getNodeType()==Node.ELEMENT_NODE)
				{
					Element childElement = (Element)childNode;
					if ("name".equals(childElement.getNodeName()))
					{
						person.setName(childElement.getFirstChild().getNodeValue());
					}
					else if (("age".equals(childElement.getNodeName())))
					{
						person.setAge(new Short(childElement.getFirstChild().getNodeValue()));
					}
				}
			}
			persons.add(person);
		}
		return persons;
	}
}

