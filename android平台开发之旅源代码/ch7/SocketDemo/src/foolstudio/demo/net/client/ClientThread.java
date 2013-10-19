package foolstudio.demo.net.client;

import java.io.IOException;

import foolstudio.demo.net.Config;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//�ͻ���ͨ���߳�
public class ClientThread extends Thread {
	
	private boolean mIsFinish = false;
	private ClientSocketDemoAct mContext = null;	
	private String mMsg = null;
	private Handler mHandler = null;

	public ClientThread(ClientSocketDemoAct ctx, String msg, Handler handler) {
		// TODO Auto-generated constructor stub
		this.mContext = ctx;
		this.mMsg = msg;
		this.mHandler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		sendMsg(this.mMsg);
		handleEcho();		
	}
	
	//�����������ָ����Ϣ
	private void sendMsg(String msg) {
		mContext.getStreamWriter().println(msg);
		showResponse("Sent '" + msg + "'");
	}
	
	//������
	private void handleEcho() {
		// TODO Auto-generated method stub		
		while(!this.mIsFinish) {
			String echo = "";
			
			try {
				echo = mContext.getStreamReader().readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(echo.equalsIgnoreCase(Config.BYE) ) { //����ǽ�����Ϣ
				showResponse(echo);
			}
			else { 
				showResponse("Got '" + echo+"'");
			}
			
			break;
		}
	}
	
	//���������߳���Ϣ���з�����Ϣ
	private void showResponse(String dataString) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putCharSequence("DEST", Config.CLIENT_ID);
		bundle.putString("MSG", dataString);
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);		
	}	

	//�����߳��Ƿ���ֹ�ı�ʶ
	public void setFinish(boolean isFinish) {
		// TODO Auto-generated method stub
		this.mIsFinish = isFinish;
	}	
};
