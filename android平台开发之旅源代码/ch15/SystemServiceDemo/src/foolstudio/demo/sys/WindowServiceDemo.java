package foolstudio.demo.sys;

import android.view.Display;
import android.view.WindowManager;

public class WindowServiceDemo {
	//获取窗口管理服务信息
	public static String getInfo(WindowManager service) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		Display display = service.getDefaultDisplay();
		
		if(display != null) {
			sb.append("Height:" + display.getHeight() );
			sb.append("\nWidth:" + display.getWidth() );			
			sb.append("\nOrientation:" + display.getOrientation() );
			sb.append("\nPixelFormat:" + display.getPixelFormat() );
			sb.append("\nRefreshRate:" + display.getRefreshRate() );
			sb.append("\nId:" + display.getDisplayId() );
		}
		
		return (sb.toString() );
	}
};
