package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.Contacts.People;

public class PeopleUtil {
	public static String getInfo(ContentResolver contentResolver) {
		String[] columns = new String[] {
				Contacts.PeopleColumns.CUSTOM_RINGTONE, //响铃
				Contacts.PeopleColumns.DISPLAY_NAME, //显示名称
				Contacts.PeopleColumns.LAST_TIME_CONTACTED, //最后联系
				Contacts.PeopleColumns.NAME, //姓名
				Contacts.PeopleColumns.NOTES, //备注
				Contacts.PeopleColumns.TIMES_CONTACTED //联系次数
		};
		String[] titles = new String[] {
				"响铃", "显示名称", "最后联系", "姓名", "备注", "联系次数"
		};		
		//打开联系人信息数据表
		Cursor cursor = contentResolver.query(People.CONTENT_URI, columns, 
											  null, null, null);
		//初始化记录游标
		cursor.moveToFirst();
		//获取游标结果
		return(SysUtil.getResult(cursor, titles));
	}
};
