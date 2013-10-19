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
		
		switch(unmaskedEvent) { //�ַ��ļ��۲��¼�
			case FileObserver.CREATE: {
				action = "����";
				break;
			}
			case FileObserver.DELETE: {
				action = "ɾ��";
				break;
			}	
			case FileObserver.MODIFY: {
				action = "�޸�";
				break;
			}
			case FileObserver.OPEN: {
				action = "��";
				break;
			}
			case FileObserver.CLOSE_WRITE: {
				action = "����ر�";
				break;
			}			
			case FileObserver.CLOSE_NOWRITE: {
				action = "�ر�";
				break;
			}
			case FileObserver.MOVED_FROM: {
				action = "�ƶ���";
				break;
			}
			case FileObserver.MOVED_TO: {
				action = "�ƶ���";
				break;
			}
			case FileObserver.ATTRIB: {
				action = "���Է���";
				break;
			}			
			default: {
				action = "Event: " + event;
				break;
			}
		}
		
		sendStatus(action + " " + realPath);
	}

	//����״̬
	private void sendStatus(String status) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putString(EXTRAS_KEY, status);
		
		Message msg = new Message();
		msg.setData(bundle);
		
		mHandler.sendMessage(msg);		
	}
};
