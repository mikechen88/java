package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.Contacts.People;

public class PeopleUtil {
	public static String getInfo(ContentResolver contentResolver) {
		String[] columns = new String[] {
				Contacts.PeopleColumns.CUSTOM_RINGTONE, //����
				Contacts.PeopleColumns.DISPLAY_NAME, //��ʾ����
				Contacts.PeopleColumns.LAST_TIME_CONTACTED, //�����ϵ
				Contacts.PeopleColumns.NAME, //����
				Contacts.PeopleColumns.NOTES, //��ע
				Contacts.PeopleColumns.TIMES_CONTACTED //��ϵ����
		};
		String[] titles = new String[] {
				"����", "��ʾ����", "�����ϵ", "����", "��ע", "��ϵ����"
		};		
		//����ϵ����Ϣ���ݱ�
		Cursor cursor = contentResolver.query(People.CONTENT_URI, columns, 
											  null, null, null);
		//��ʼ����¼�α�
		cursor.moveToFirst();
		//��ȡ�α���
		return(SysUtil.getResult(cursor, titles));
	}
};
