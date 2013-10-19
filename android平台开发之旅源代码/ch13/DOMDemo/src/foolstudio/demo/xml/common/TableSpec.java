package foolstudio.demo.xml.common;

import java.util.ArrayList;

//表示数据表规范
public class TableSpec {
	
	private String name = null;
	private int fieldCount = 0;
	private String primaryKey = null;
	private ArrayList<FieldSpec> fields = new ArrayList<FieldSpec>();
	
	public TableSpec() {
		// TODO Auto-generated constructor stub
	}	
	
	public TableSpec(String name, int fieldCount, String primaryKey) {
		super();
		this.name = name;
		this.fieldCount = fieldCount;
		this.primaryKey = primaryKey;
	}
	//
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//
	public int getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}	
	//
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	//
	public ArrayList<FieldSpec> getFields() {
		return fields;
	}

	public void addField(FieldSpec field) {
		this.fields.add(field);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("Table:");
		sb.append(this.name);
		sb.append(",FieldCount:");
		sb.append(this.fieldCount);
		sb.append(",PrimaryKey:");
		sb.append(this.primaryKey);
		
		return (sb.toString() );
	}
};
