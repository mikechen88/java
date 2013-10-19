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
				"号码", "类型"
		};		
		
		String[] columns = {
			Phone.NUMBER,
			Phone.TYPE
		};
		
		//获取联系数据中电话信息
		Cursor c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
							  			 columns, 
				   Data.MIMETYPE+"='"+Phone.CONTENT_ITEM_TYPE+"'", 
							  			 null, null);
		//获取游标记录集结果
		return(PimUtil.getResult(c, titles));
	}
};
