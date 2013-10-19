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
				"姓", "名", "显示姓名"
		};		
		
		String[] columns = {
			StructuredName.FAMILY_NAME,
			StructuredName.GIVEN_NAME,
			StructuredName.DISPLAY_NAME
		};
		
		//获取联系数据中姓名信息
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+StructuredName.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//获取游标记录集结果
		return(PimUtil.getResult(c, titles));
	}
};
