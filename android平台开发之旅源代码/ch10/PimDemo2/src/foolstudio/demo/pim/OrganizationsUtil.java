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
				"公司", "抬头", "类型"
		};		
		
		String[] columns = {
			Organization.COMPANY,
			Organization.TITLE,
			Organization.TYPE
		};
		
		//获取联系数据中公司信息		
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+Organization.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//获取游标记录集结果
		return(PimUtil.getResult(c, titles));
	}
};
