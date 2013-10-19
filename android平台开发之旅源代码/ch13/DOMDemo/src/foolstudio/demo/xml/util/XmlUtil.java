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
	
	//����ģʽ
	private static XmlUtil mInstance = new XmlUtil();
	
	private XmlUtil() {
	}
	
	public static XmlUtil getInstance() {
		return (mInstance);
	}
	
	//-------------------------------------------------------------------------
	//���浽ָ���ļ�
	public void saveToFile(String file_path) throws Exception {
		//��ȡһ��DocumentBuilderFactoryʵ��
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//����һ��DocumentBuilderʵ��
		DocumentBuilder builder = factory.newDocumentBuilder();
		//��ȡһ�����ڹ���DOM����DOM����
		Document document = builder.newDocument();
		
		//����һ��ע�ͽڵ�
		Comment comment = document.createComment(
				"Copyright (C) 2009 Fool studio. All rights reserved.");
		document.appendChild(comment);		

		//�������ڵ�
		Element root = document.createElement("TableStructure");
		//��Ӹ��ڵ�
		document.appendChild(root);
		
		/*
		try {
			//���ע�ͽڵ�
			document.insertBefore(comment, root);
		}catch(DOMException e) {
			dumpDOMException(e);
		}
		*/

		//�����ṹ
		defineUserInfoTable1(document, root);
		defineDutyInfoTable1(document, root);

		//���浽XML�ļ�
		dumpToFile(document, file_path);
	}

	/*
	//���DOM�쳣
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
	//������û���Ϣ���ṹ
	private void defineUserInfoTable1(Document doc, Element root) {
		//�����ڵ�
		Element table = defineTable(doc, root, "user_info", "4", "ui_id");
		
		//������ֶ�
		defineField(doc, table, "ui_id", 32, false, "0", ""); //Id
		defineField(doc, table, "ui_password", 32, false, "pt950", ""); //����
		defineField(doc, table, "ui_sex", 8, true, "Male", ""); //�Ա�
		defineField(doc, table, "ui_hobby", 200, true, "RESERVED", "|"); //����

		//���ע��
		Comment comment = doc.createComment("User infomation table");
		root.insertBefore(comment, table);
	}

	//-------------------------------------------------------------------------
	//�����ְλ��Ϣ���ṹ
	private void defineDutyInfoTable1(Document doc, Element root) {
		//�����ڵ�
		Element table = defineTable(doc, root, "duty_info", "3", "di_id");
		
		//������ֶ�
		defineField(doc, table, "di_id", 4, false, "0", ""); //Id
		defineField(doc, table, "di_class", 4, false, "0", ""); //ְλ
		defineField(doc, table, "di_desc", 128, false, "RESERVED", "");//ְλ����

		//���ע��
		Comment comment = doc.createComment("Duty information table");
		root.insertBefore(comment, table);
	}

	//-------------------------------------------------------------------------
	//�������ݱ�
	private Element defineTable(Document doc, Element root, String name, 
			String fieldCount, String primaryKey) {
		Element table = doc.createElement("Table");
		root.appendChild(table);

		//��ӱ�����
		table.setAttribute("NAME", name);
		table.setAttribute("FIELD_COUNT", fieldCount);
		table.setAttribute("PRIMARY_KEY", primaryKey);

		return (table);
	}

	//-------------------------------------------------------------------------
	//�������ݱ��ֶ�
	private void defineField(Document doc, Element table, String name, 
			int maxSize, boolean isAllowNull, String defaultVal, String separator) {
		Element field = doc.createElement("Field");
		table.appendChild(field);

		//����ֶ�����
		field.setAttribute("NAME", name);
		field.setAttribute("MAX_SIZE", String.valueOf(maxSize) );
		field.setAttribute("NULL", String.valueOf(isAllowNull) );
		field.setAttribute("DEFAULT", defaultVal);
		field.setAttribute("SEPARATOR", separator);
	}

	//-------------------------------------------------------------------------
	//��DOM���ֵ��ڴ�洢��XML�ļ�
	public void dumpToFile(Document doc, String filePath) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new FileOutputStream(filePath) );
		
		//XML�ļ�ͷ
		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");		
		
		NodeList elements = doc.getChildNodes();
		for(int i = 0; i < elements.getLength(); ++i) {
			
			Node node = elements.item(i);
			dumpNode(node, out);
		}
		
		out.close();
	}

	//�����ڵ�����
	private void dumpNode(Node node, PrintWriter out) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		if(node.getNodeType() == Element.COMMENT_NODE) {
			Comment comment = (Comment)node;
			
			//���
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

	//�����ڵ�
	private void dumpElement(Element item, PrintWriter out) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		//ͷ���
		sb.append("<");
		sb.append(item.getTagName() );
		
		NamedNodeMap attrs = item.getAttributes();
		
		if(attrs.getLength() > 0) { //�ָ��ڵ��Ǻ�������
			sb.append(' ');
		}
		
		//����ֵ
		for(int i = 0; i < attrs.getLength(); ++i) {
			sb.append(attrs.item(i).getNodeName() );
			sb.append('=');
			sb.append('\"');
			sb.append(attrs.item(i).getNodeValue() );
			sb.append('\"');
			sb.append(' ');
		}
		
		//�ӽڵ�
		NodeList childElements = item.getChildNodes();
		if(childElements.getLength() > 0) { //���ӽڵ�
			sb.append(">");
			out.println(sb.toString() );
			
			//�ӽڵ�
			for(int j = 0; j < childElements.getLength(); ++j) {
				Node node = childElements.item(j);
				dumpNode(node, out);
			}
			
			//���
			sb.delete(0, sb.length() );
			//��ӽڵ��������
			sb.append("</");
			sb.append(item.getTagName() );
			sb.append('>');
		}
		else { //���ӽڵ�
			//��ӽڵ��н������
			sb.append("/>");
		}
		
		out.println(sb.toString() );
	}
};
