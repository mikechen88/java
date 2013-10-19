package foolstudio.demo.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HandlerUtil {
	
	public static String SENDER = "Sender";
	public static String MSG = "Msg";
	
	public static void sendMsg(Handler handler, String sender, String msgStr) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putCharSequence(SENDER, sender);
		bundle.putString(MSG, msgStr);
		Message msg = new Message();
		msg.setData(bundle);
		handler.sendMessage(msg);		
	}
};
