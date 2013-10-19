package foolstudio.demo.pim;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Organization;

public class OrganizationsUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		String[] titles = {
				"��˾", "̧ͷ", "����"
		};		
		
		String[] columns = {
			Organization.COMPANY,
			Organization.TITLE,
			Organization.TYPE
		};
		
		//��ȡ��ϵ�����й�˾��Ϣ		
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+Organization.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//��ȡ�α��¼�����
		return(PimUtil.getResult(c, titles));
	}
};
