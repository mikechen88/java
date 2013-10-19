package foolstudio.demo.sys;

import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Message;

public class FoolFileObserver extends FileObserver {
	
	public static final String EXTRAS_KEY ="status";	
	private Handler mHandler = null;

	public FoolFileObserver(String path, int mask) {
		super(path, mask);
		// TODO Auto-generated constructor stub
	}

	public void setHandler(Handler handler) {
		this.mHandler = handler;
	}		

	@Override
	public void onEvent(int event, String path) {
		// TODO Auto-generated method stub		
		int unmaskedEvent = (event&0x0000ffff);
		String realPath = ((path == null)?FileObserverDemoAct.DEST_PATH:path);
		String action = null;
		
		switch(unmaskedEvent) { //分发文件观察事件
			case FileObserver.CREATE: {
				action = "创建";
				break;
			}
			case FileObserver.DELETE: {
				action = "删除";
				break;
			}	
			case FileObserver.MODIFY: {
				action = "修改";
				break;
			}
			case FileObserver.OPEN: {
				action = "打开";
				break;
			}
			case FileObserver.CLOSE_WRITE: {
				action = "保存关闭";
				break;
			}			
			case FileObserver.CLOSE_NOWRITE: {
				action = "关闭";
				break;
			}
			case FileObserver.MOVED_FROM: {
				action = "移动从";
				break;
			}
			case FileObserver.MOVED_TO: {
				action = "移动到";
				break;
			}
			case FileObserver.ATTRIB: {
				action = "属性访问";
				break;
			}			
			default: {
				action = "Event: " + event;
				break;
			}
		}
		
		sendStatus(action + " " + realPath);
	}

	//发送状态
	private void sendStatus(String status) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putString(EXTRAS_KEY, status);
		
		Message msg = new Message();
		msg.setData(bundle);
		
		mHandler.sendMessage(msg);		
	}
};
