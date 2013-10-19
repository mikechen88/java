package foolstudio.demo;

import org.apache.derby.jdbc.EmbeddedSimpleDataSource;

public class EmbeddedDataSource {
	
    public final String TEST_DB_NAME = "FOOL_DB";	
    private static EmbeddedDataSource mInstance = new EmbeddedDataSource();
	private static EmbeddedSimpleDataSource mDS = null;
	
	//��ֹ�ⲿ����
	private EmbeddedDataSource() {		
	}
	
	//��ȡ��ʵ��
	public static EmbeddedDataSource getInstance() {
		return (mInstance);
	}
	
	//��ȡ����Դ
	public EmbeddedSimpleDataSource getDataSource() {
		if(mDS == null) {
			mDS = new EmbeddedSimpleDataSource();
			mDS.setDatabaseName(TEST_DB_NAME);
			//����������ָ�������ݿ�
			mDS.setCreateDatabase("create");			
		}
		
		return (mDS);
	}
};
