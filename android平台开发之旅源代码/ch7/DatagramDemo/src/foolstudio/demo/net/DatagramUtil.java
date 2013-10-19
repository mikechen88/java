package foolstudio.demo.net;

import java.net.DatagramPacket;
import java.net.InetAddress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DatagramUtil {
	
	private static DatagramUtil mInstance = new DatagramUtil();
	
	//单例模式（构造函数不对外）
	private DatagramUtil() {
	}
	
	public static DatagramUtil getInstance() {
		return (mInstance);
	}
	
	//获取数据报数据
	public String getPacketData(DatagramPacket pack) {
		// TODO Auto-generated method stub
		byte[] data = pack.getData();
		String dataString = new String(data, pack.getOffset(), pack.getLength() );
		
		return (dataString);
	}	
	
	//获取数据报信息
	public String getPackInfo(DatagramPacket pack) {
		InetAddress addr = pack.getAddress();
		int port = pack.getPort();		
		
		return (addr.getHostAddress() + "(" + addr.getHostName() + ") : " + port);
	}
	
	//发送消息到界面线程消息队列中
	public void showResponse(Handler handler, String id, String data) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putCharSequence("DEST", id);
		bundle.putString("MSG", data);
		Message msg = new Message();
		msg.setData(bundle);
		handler.sendMessage(msg);		
	}		
};
