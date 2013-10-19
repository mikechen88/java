package foolstudio.demo.xml.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil {
	
	//单例模式
	private static XmlUtil mInstance = new XmlUtil();
	
	private XmlUtil() {
	}
	
	public static XmlUtil getInstance() {
		return (mInstance);
	}
	
	//-------------------------------------------------------------------------
	//保存到指定文件
	public void saveToFile(String file_path) throws Exception {
		//获取一个DocumentBuilderFactory实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//创建一个DocumentBuilder实例
		DocumentBuilder builder = factory.newDocumentBuilder();
		//获取一个用于构建DOM树的DOM对象
		Document document = builder.newDocument();
		
		//创建一个注释节点
		Comment comment = document.createComment(
				"Copyright (C) 2009 Fool studio. All rights reserved.");
		document.appendChild(comment);		

		//创建根节点
		Element root = document.createElement("TableStructure");
		//添加根节点
		document.appendChild(root);
		
		/*
		try {
			//添加注释节点
			document.insertBefore(comment, root);
		}catch(DOMException e) {
			dumpDOMException(e);
		}
		*/

		//定义表结构
		defineUserInfoTable1(document, root);
		defineDutyInfoTable1(document, root);

		//保存到XML文件
		dumpToFile(document, file_path);
	}

	/*
	//输出DOM异常
	private void dumpDOMException(DOMException e) {
		// TODO Auto-generated method stub
		switch(e.code) {
			case DOMException.HIERARCHY_REQUEST_ERR: {
				Log.d(this.getClass().getName(), "HIERARCHY_REQUEST_ERR");
				break;
			}
			case DOMException.WRONG_DOCUMENT_ERR: {
				Log.d(this.getClass().getName(), "WRONG_DOCUMENT_ERR");
				break;
			}
			case DOMException.NO_MODIFICATION_ALLOWED_ERR: {
				Log.d(this.getClass().getName(), "NO_MODIFICATION_ALLOWED_ERR");
				break;
			}
			case DOMException.NOT_FOUND_ERR: {
				Log.d(this.getClass().getName(), "NOT_FOUND_ERR");
				break;
			}
			default: {
				Log.d(this.getClass().getName(), "Unknown DOMException");
				break;
			}			
		}
	}
	*/

	//-------------------------------------------------------------------------
	//定义表【用户信息】结构
	private void defineUserInfoTable1(Document doc, Element root) {
		//定义表节点
		Element table = defineTable(doc, root, "user_info", "4", "ui_id");
		
		//定义表字段
		defineField(doc, table, "ui_id", 32, false, "0", ""); //Id
		defineField(doc, table, "ui_password", 32, false, "pt950", ""); //密码
		defineField(doc, table, "ui_sex", 8, true, "Male", ""); //性别
		defineField(doc, table, "ui_hobby", 200, true, "RESERVED", "|"); //爱好

		//添加注释
		Comment comment = doc.createComment("User infomation table");
		root.insertBefore(comment, table);
	}

	//-------------------------------------------------------------------------
	//定义表【职位信息】结构
	private void defineDutyInfoTable1(Document doc, Element root) {
		//定义表节点
		Element table = defineTable(doc, root, "duty_info", "3", "di_id");
		
		//定义表字段
		defineField(doc, table, "di_id", 4, false, "0", ""); //Id
		defineField(doc, table, "di_class", 4, false, "0", ""); //职位
		defineField(doc, table, "di_desc", 128, false, "RESERVED", "");//职位描述

		//添加注释
		Comment comment = doc.createComment("Duty information table");
		root.insertBefore(comment, table);
	}

	//-------------------------------------------------------------------------
	//定义数据表
	private Element defineTable(Document doc, Element root, String name, 
			String fieldCount, String primaryKey) {
		Element table = doc.createElement("Table");
		root.appendChild(table);

		//添加表属性
		table.setAttribute("NAME", name);
		table.setAttribute("FIELD_COUNT", fieldCount);
		table.setAttribute("PRIMARY_KEY", primaryKey);

		return (table);
	}

	//-------------------------------------------------------------------------
	//定义数据表字段
	private void defineField(Document doc, Element table, String name, 
			int maxSize, boolean isAllowNull, String defaultVal, String separator) {
		Element field = doc.createElement("Field");
		table.appendChild(field);

		//添加字段属性
		field.setAttribute("NAME", name);
		field.setAttribute("MAX_SIZE", String.valueOf(maxSize) );
		field.setAttribute("NULL", String.valueOf(isAllowNull) );
		field.setAttribute("DEFAULT", defaultVal);
		field.setAttribute("SEPARATOR", separator);
	}

	//-------------------------------------------------------------------------
	//将DOM树种的内存存储到XML文件
	public void dumpToFile(Document doc, String filePath) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath) );
		
		//XML文件头
		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");		
		
		NodeList elements = doc.getChildNodes();
		for(int i = 0; i < elements.getLength(); ++i) {
			
			Node node = elements.item(i);
			dumpNode(node, out);
		}
		
		out.close();
	}

	//导出节点内容
	private void dumpNode(Node node, PrintWriter out) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		if(node.getNodeType() == Element.COMMENT_NODE) {
			Comment comment = (Comment)node;
			
			//清空
			sb.delete(0, sb.length() );
			sb.append("<!--");
			sb.append(comment.getData() );
			sb.append("-->");
			out.println(sb.toString() );
		}
		else {
			Element element = (Element)node;			
			dumpElement(element, out);
		}		
	}

	//导出节点
	private void dumpElement(Element item, PrintWriter out) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		//头标记
		sb.append("<");
		sb.append(item.getTagName() );
		
		NamedNodeMap attrs = item.getAttributes();
		
		if(attrs.getLength() > 0) { //分隔节点标记和属性项
			sb.append(' ');
		}
		
		//属性值
		for(int i = 0; i < attrs.getLength(); ++i) {
			sb.append(attrs.item(i).getNodeName() );
			sb.append('=');
			sb.append('\"');
			sb.append(attrs.item(i).getNodeValue() );
			sb.append('\"');
			sb.append(' ');
		}
		
		//子节点
		NodeList childElements = item.getChildNodes();
		if(childElements.getLength() > 0) { //有子节点
			sb.append(">");
			out.println(sb.toString() );
			
			//子节点
			for(int j = 0; j < childElements.getLength(); ++j) {
				Node node = childElements.item(j);
				dumpNode(node, out);
			}
			
			//清空
			sb.delete(0, sb.length() );
			//添加节点块结束标记
			sb.append("</");
			sb.append(item.getTagName() );
			sb.append('>');
		}
		else { //无子节点
			//添加节点行结束标记
			sb.append("/>");
		}
		
		out.println(sb.toString() );
	}
};
