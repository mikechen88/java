package foolstudio.demo.net;

import java.net.DatagramPacket;
import java.net.InetAddress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DatagramUtil {
	
	private static DatagramUtil mInstance = new DatagramUtil();
	
	//����ģʽ�����캯�������⣩
	private DatagramUtil() {
	}
	
	public static DatagramUtil getInstance() {
		return (mInstance);
	}
	
	//��ȡ���ݱ�����
	public String getPacketData(DatagramPacket pack) {
		// TODO Auto-generated method stub
		byte[] data = pack.getData();
		String dataString = new String(data, pack.getOffset(), pack.getLength() );
		
		return (dataString);
	}	
	
	//��ȡ���ݱ���Ϣ
	public String getPackInfo(DatagramPacket pack) {
		InetAddress addr = pack.getAddress();
		int port = pack.getPort();		
		
		return (addr.getHostAddress() + "(" + addr.getHostName() + ") : " + port);
	}
	
	//������Ϣ�������߳���Ϣ������
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
