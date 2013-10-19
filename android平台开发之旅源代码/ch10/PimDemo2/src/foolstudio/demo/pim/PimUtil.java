package foolstudio.demo.pim;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PimUtil {
	//��Unixʱ���ת��������ʱ���ַ���
	public static String unixTimestamp2Str(long epoch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00") );
        Date theDate = new Date(epoch);
        return (sdf.format(theDate) );
	}
	
	//��ȡ�α��¼�����
	public static String getResult(Cursor cursor, String[] titles) {
		StringBuffer sb = new StringBuffer();		

		//��ʼ����¼�α�
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			
			for(int i = 0; i < titles.length; ++i) {
				sb.append(titles[i]+'='+cursor.getString(i) );
				
				if(titles[i].contains("����") ) {
					int type = Integer.parseInt(cursor.getString(i) );
					sb.append("("+getPhoneType(type)+")");
					//sb.append("("+getPhoneType(type)+")");
				}
				
				if(i < (titles.length-1) ) {
					sb.append(',');
				}
			}
			
			sb.append('\n');
			//��һ����¼
			cursor.moveToNext();
		}
		
		cursor.close();		
		return(sb.toString() );		
	}
	
	//��ȡ��֯����
	private static String getOrgsType(int type) {
		switch(type) {
			case Organization.TYPE_CUSTOM: {
				return("�Զ�������");
			}
			case Organization.TYPE_WORK: {
				return("��������");
			}			
			case Organization.TYPE_OTHER: {
				return("������֯");
			}
			default: {
				return("δ֪���ͣ�"+type);
			}
		}
	}	
	
	//��ȡ�绰��������
	private static String getPhoneType(int type) {
		switch(type) {
			case Phone.TYPE_HOME: {
				return("��ͥ����");
			}
			case Phone.TYPE_MOBILE: {
				return("�ֻ�����");
			}			
			case Phone.TYPE_WORK: {
				return("��������");
			}
			default: {
				return("δ֪���ͣ�"+type);
			}
		}
	}	
};
