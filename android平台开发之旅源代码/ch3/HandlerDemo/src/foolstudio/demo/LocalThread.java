package foolstudio.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LocalThread extends Thread {
	
	private Handler mHandler = null;
	private HandlerDemoAct mContext = null;

	public LocalThread(HandlerDemoAct handlerDemoAct) {
		// TODO Auto-generated constructor stub
		this.mContext = handlerDemoAct;
	}

	public LocalThread(HandlerDemoAct handlerDemoAct, Handler handler) {
		// TODO Auto-generated constructor stub
		this.mHandler = handler;
		this.mContext = handlerDemoAct;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		mContext.addMsg("Hi, Paul!");
		
		Bundle bundle = new Bundle();
		bundle.putCharSequence("Sender", mContext.getTitle() );
		bundle.putString("Msg", "Hi, Paul!");
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}
};
