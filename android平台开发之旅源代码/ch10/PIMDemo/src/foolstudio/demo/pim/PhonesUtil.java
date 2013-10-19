package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.Contacts.Phones;

public class PhonesUtil {
	public static String getInfo(ContentResolver contentResolver) {
		String[] columns = new String[] {
				Contacts.PhonesColumns.LABEL, //标签
				Contacts.PhonesColumns.NUMBER, //号码
				Contacts.PhonesColumns.NUMBER_KEY, //号码标识
				Contacts.PhonesColumns.TYPE, //类型
				Contacts.PhonesColumns.ISPRIMARY //是否主要
		};
		String[] titles = new String[] {
				"标签", "号码", "号码标识", "类型", "是否主要"
		};			
		//打开电话号码信息数据表
		Cursor cursor = contentResolver.query(Phones.CONTENT_URI, columns, 
											  null, null, null);
		//初始化记录游标
		cursor.moveToFirst();		
		//获取游标结果
		return(SysUtil.getResult(cursor, titles));
	}
};
