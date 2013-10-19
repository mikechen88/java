package foolstudio.demo.xml.common;

//表示数据字段规范
public class FieldSpec {

	private String name = null;
	private int maxSize = 256;
	private boolean isAllowNull = true;
	private String defaulVal = null;
	private String separator = null;
	
	public FieldSpec() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public FieldSpec(String name, int maxSize, boolean isAllowNull,
			String defaulVal, String separator) {
		super();
		this.name = name;
		this.maxSize = maxSize;
		this.isAllowNull = isAllowNull;
		this.defaulVal = defaulVal;
		this.separator = separator;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	//
	public boolean isAllowNull() {
		return isAllowNull;
	}
	public void setAllowNull(boolean isAllowNull) {
		this.isAllowNull = isAllowNull;
	}
	//
	public String getDefaulVal() {
		return defaulVal;
	}
	public void setDefaulVal(String defaulVal) {
		this.defaulVal = defaulVal;
	}
	//
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("Field:");
		sb.append(this.name);
		sb.append(",MaxSize:");
		sb.append(this.maxSize);
		sb.append(",IsAllowNull:");
		sb.append(this.isAllowNull);	
		sb.append(",DefaulVal:");
		sb.append(this.defaulVal);
		sb.append(",Separator:");
		sb.append(this.separator);		
		
		return (sb.toString() );
	}
};
