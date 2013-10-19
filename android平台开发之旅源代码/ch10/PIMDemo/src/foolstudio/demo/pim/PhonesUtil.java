package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.Contacts.Phones;

public class PhonesUtil {
	public static String getInfo(ContentResolver contentResolver) {
		String[] columns = new String[] {
				Contacts.PhonesColumns.LABEL, //��ǩ
				Contacts.PhonesColumns.NUMBER, //����
				Contacts.PhonesColumns.NUMBER_KEY, //�����ʶ
				Contacts.PhonesColumns.TYPE, //����
				Contacts.PhonesColumns.ISPRIMARY //�Ƿ���Ҫ
		};
		String[] titles = new String[] {
				"��ǩ", "����", "�����ʶ", "����", "�Ƿ���Ҫ"
		};			
		//�򿪵绰������Ϣ���ݱ�
		Cursor cursor = contentResolver.query(Phones.CONTENT_URI, columns, 
											  null, null, null);
		//��ʼ����¼�α�
		cursor.moveToFirst();		
		//��ȡ�α���
		return(SysUtil.getResult(cursor, titles));
	}
};
