package foolstudio.demo.sys;

import java.util.List;

import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

public class InputMethodServiceDemo {
	//获取输入法信息
	public static String getInfo(InputMethodManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		//List<InputMethodInfo> list = service.getInputMethodList();
		List<InputMethodInfo> list2 = service.getEnabledInputMethodList();

		/*
		for(int i = 0; i < list.size(); ++i) {
			sb.append(getInfo(list.get(i)) );
		}
		*/
		
		for(int j = 0; j < list2.size(); ++j) {
			sb.append(getInfo(list2.get(j)) );
		}		
		
		return (sb.toString() );
	}
	
	//获取输入法信息
	private static Object getInfo(InputMethodInfo info) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		sb.append("Id="+info.getId() );
		sb.append(";Act="+info.getSettingsActivity() );
		sb.append(";Name="+info.getServiceName() );
		sb.append('\n');
		
		return (sb.toString() );
	}
};
