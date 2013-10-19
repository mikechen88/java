package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;

public class OrganizationsUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {
				Contacts.OrganizationColumns.COMPANY, //��˾
				Contacts.OrganizationColumns.ISPRIMARY, //�Ƿ���Ҫ
				Contacts.OrganizationColumns.LABEL, //��ǩ
				Contacts.OrganizationColumns.PERSON_ID,
				Contacts.OrganizationColumns.TITLE, //ְλ
				Contacts.OrganizationColumns.TYPE //����
		};
		String[] titles = new String[] {
				"��˾", "�Ƿ���Ҫ", "��ǩ", "��ϵ��ID", "ְλ", "����" 
		};			
		//����֯��Ϣ���ݱ�
		Cursor cursor = contentResolver.query(Contacts.Organizations.CONTENT_URI, 
											  columns, null, null, null);
		//��ʼ����¼�α�
		cursor.moveToFirst();		
		//��ȡ�α���
		return(SysUtil.getResult(cursor, titles));
	}
};
