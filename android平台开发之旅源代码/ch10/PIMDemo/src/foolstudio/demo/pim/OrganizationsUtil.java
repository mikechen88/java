package foolstudio.demo.pim;

import foolstudio.demo.SysUtil;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Contacts;

public class OrganizationsUtil {
	public static String getInfo(ContentResolver contentResolver) {
		// TODO Auto-generated method stub
		final String[] columns = new String[] {
				Contacts.OrganizationColumns.COMPANY, //公司
				Contacts.OrganizationColumns.ISPRIMARY, //是否主要
				Contacts.OrganizationColumns.LABEL, //标签
				Contacts.OrganizationColumns.PERSON_ID,
				Contacts.OrganizationColumns.TITLE, //职位
				Contacts.OrganizationColumns.TYPE //类型
		};
		String[] titles = new String[] {
				"公司", "是否主要", "标签", "联系人ID", "职位", "类型" 
		};			
		//打开组织信息数据表
		Cursor cursor = contentResolver.query(Contacts.Organizations.CONTENT_URI, 
											  columns, null, null, null);
		//初始化记录游标
		cursor.moveToFirst();		
		//获取游标结果
		return(SysUtil.getResult(cursor, titles));
	}
};
