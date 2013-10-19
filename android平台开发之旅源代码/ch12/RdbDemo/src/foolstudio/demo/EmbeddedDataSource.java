package foolstudio.demo;

import org.apache.derby.jdbc.EmbeddedSimpleDataSource;

public class EmbeddedDataSource {
	
    public final String TEST_DB_NAME = "FOOL_DB";	
    private static EmbeddedDataSource mInstance = new EmbeddedDataSource();
	private static EmbeddedSimpleDataSource mDS = null;
	
	//禁止外部调用
	private EmbeddedDataSource() {		
	}
	
	//获取类实例
	public static EmbeddedDataSource getInstance() {
		return (mInstance);
	}
	
	//获取数据源
	public EmbeddedSimpleDataSource getDataSource() {
		if(mDS == null) {
			mDS = new EmbeddedSimpleDataSource();
			mDS.setDatabaseName(TEST_DB_NAME);
			//创建或启动指定的数据库
			mDS.setCreateDatabase("create");			
		}
		
		return (mDS);
	}
};
