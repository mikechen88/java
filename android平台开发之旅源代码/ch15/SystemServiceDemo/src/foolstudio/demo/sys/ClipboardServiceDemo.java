package foolstudio.demo.sys;

import android.text.ClipboardManager;

public class ClipboardServiceDemo {

	public static String getInfo(ClipboardManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		service.setText("This text from ClipboardManager.");
		sb.append(service.getText() );
		
		return (sb.toString() );
	}
}
