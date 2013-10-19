package foolstudio.demo.sys;

import android.app.SearchManager;
import android.content.ComponentName;

public class SearchServiceDemo {
	public static String getInfo(SearchManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		service.startSearch("foolstudio", true, 
				new ComponentName("foolstudio.demo.sys", 
								  "SysServiceDemoAct"), 
				null, true);
		
		return (sb.toString() );
	}
};
