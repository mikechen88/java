package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;

public class PeopleUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		String[] titles = {
				"��", "��", "��ʾ����"
		};		
		
		String[] columns = {
			StructuredName.FAMILY_NAME,
			StructuredName.GIVEN_NAME,
			StructuredName.DISPLAY_NAME
		};
		
		//��ȡ��ϵ������������Ϣ
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+StructuredName.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//��ȡ�α��¼�����
		return(PimUtil.getResult(c, titles));
	}
};
