package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PhonesUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		String[] titles = {
				"����", "����"
		};		
		
		String[] columns = {
			Phone.NUMBER,
			Phone.TYPE
		};
		
		//��ȡ��ϵ�����е绰��Ϣ
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+Phone.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//��ȡ�α��¼�����
		return(PimUtil.getResult(c, titles));
	}
};
