package foolstudio.demo.xml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import foolstudio.demo.xml.common.FieldSpec;
import foolstudio.demo.xml.common.TableSpec;
import foolstudio.demo.xml.util.XmlUtil;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DOMDemoAct extends Activity implements OnClickListener {
	
	private static final String XML_FILE_PATH = "/sdcard/TS.xml";
	
	private Button mBtnCreate = null;
	private Button mBtnRead = null;
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnCreate = (Button)findViewById(R.id.BTN_CREATE);
        mBtnRead = (Button)findViewById(R.id.BTN_READ);
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        mBtnCreate.setOnClickListener(this);
        mBtnRead.setOnClickListener(this);    
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_CREATE: {
				doCreate();
				break;
			}
			case R.id.BTN_READ: {
				doRead();
				break;
			}			
		}
	}

	//执行读取行为
	private void doRead() {
		// TODO Auto-generated method stub
		try {
			//parseXml(this.getResources().openRawResource(R.raw.cities) );
			FileInputStream is = new FileInputStream(XML_FILE_PATH);
			resetContents();
			parseXml(is);
			is.close();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//通过DOM方式解析XML文件
	private void parseXml(InputStream is) throws ParserConfigurationException, 
												 SAXException, 
												 IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		NodeList elements = document.getElementsByTagName("*");
		
		ArrayList<TableSpec> tables = new ArrayList<TableSpec>();
		
		for(int i = 0; i < elements.getLength(); ++i) {
			Element element = (Element)elements.item(i);			
			String tagName = element.getTagName();
			
			if(tagName.equalsIgnoreCase("Table") ) { //遇到表定义				
				//添加表定义（表字段未设置）
				TableSpec tableSpec = new TableSpec();
				//
				tableSpec.setName(element.getAttribute("NAME") );
				tableSpec.setFieldCount(
						Integer.parseInt(element.getAttribute("FIELD_COUNT")) );
				tableSpec.setPrimaryKey(element.getAttribute("PRIMARY_KEY") );
				
				tables.add(tableSpec);
			}
			else if(tagName.equalsIgnoreCase("Field") ) { //遇到字段定义
				FieldSpec fieldSpec = new FieldSpec();
				//
				fieldSpec.setName(element.getAttribute("NAME") );
				fieldSpec.setMaxSize(
						Integer.parseInt(element.getAttribute("MAX_SIZE") ) );
				fieldSpec.setAllowNull(
						element.getAttribute("NULL").equalsIgnoreCase("TRUE") );
				fieldSpec.setDefaulVal(element.getAttribute("DEFAULT") );
				fieldSpec.setSeparator(element.getAttribute("SEPARATOR") );
				
				tables.get(tables.size()-1).addField(fieldSpec);
			}
			else {
				Log.d(this.getClass().getName(), tagName);
			}
		}
		
		for(int j = 0; j < tables.size(); ++j) {
			TableSpec tableSpec = tables.get(j);
			printText(tableSpec.toString() );
			
			for(int k = 0; k < tableSpec.getFields().size(); ++k) {
				printText(tableSpec.getFields().get(k).toString() );
			}
		}
	}

	//执行创建行为
	private void doCreate() {
		// TODO Auto-generated method stub
		try {
			XmlUtil.getInstance().saveToFile(XML_FILE_PATH);
			resetContents();
			loadFile(XML_FILE_PATH);
			Toast.makeText(this, "Save " + XML_FILE_PATH + " OK!", 
						   Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	//载入指定的XML文件
	private void loadFile(String xmlFilePath) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(
											new FileInputStream(xmlFilePath)));
		String line = null;
		
		while((line=in.readLine())!= null) {
			printText(line);
		}
		
		in.close();
	}
	
	//清空内容
	private void resetContents() {
		// TODO Auto-generated method stub
		mTxtContents.setText("");
	}	

	//打印文本
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}	
};