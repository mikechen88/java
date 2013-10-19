package foolstudio.demo.pim;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class PimUtil {
	//将Unix时间戳转换成日期时间字符串
	public static String unixTimestamp2Str(long epoch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00") );
        Date theDate = new Date(epoch);
        return (sdf.format(theDate) );
	}
	
	//获取游标记录集结果
	public static String getResult(Cursor cursor, String[] titles) {
		StringBuffer sb = new StringBuffer();		

		//初始化记录游标
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast() ) {
			
			for(int i = 0; i < titles.length; ++i) {
				sb.append(titles[i]+'='+cursor.getString(i) );
				
				if(titles[i].contains("类型") ) {
					int type = Integer.parseInt(cursor.getString(i) );
					sb.append("("+getPhoneType(type)+")");
					//sb.append("("+getPhoneType(type)+")");
				}
				
				if(i < (titles.length-1) ) {
					sb.append(',');
				}
			}
			
			sb.append('\n');
			//下一条记录
			cursor.moveToNext();
		}
		
		cursor.close();		
		return(sb.toString() );		
	}
	
	//获取组织类型
	private static String getOrgsType(int type) {
		switch(type) {
			case Organization.TYPE_CUSTOM: {
				return("自定义类型");
			}
			case Organization.TYPE_WORK: {
				return("工作类型");
			}			
			case Organization.TYPE_OTHER: {
				return("其他组织");
			}
			default: {
				return("未知类型："+type);
			}
		}
	}	
	
	//获取电话号码类型
	private static String getPhoneType(int type) {
		switch(type) {
			case Phone.TYPE_HOME: {
				return("家庭号码");
			}
			case Phone.TYPE_MOBILE: {
				return("手机号码");
			}			
			case Phone.TYPE_WORK: {
				return("工作号码");
			}
			default: {
				return("未知类型："+type);
			}
		}
	}	
};
