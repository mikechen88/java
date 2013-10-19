package foolstudio.demo.net.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//
import foolstudio.demo.net.Config;
import foolstudio.demo.net.DatagramUtil;
//
import android.os.Handler;

public class ServerThread extends Thread {

	private DatagramSocket mServerDatagram = null;
	private Handler mHandler = null;
	private DatagramUtil mUtil = DatagramUtil.getInstance();
	
	public ServerThread(Handler handler) {
		
		this.mHandler = handler;
		
		try {
			mServerDatagram = new DatagramSocket(Config.SERVER_PORT);
			mUtil.showResponse(handler, Config.SERVER_ID,
					"Server runing @ " + Config.SERVER_PORT + " ...");					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//开始服务
		startServer();
		
		mServerDatagram.close();
	}	

	//开始服务
	private void startServer() {
		// TODO Auto-generated method stub
		try {			
			while(true) {
				//获取请求
				byte[] request = new byte[Config.BUFFER_SIZE];
				DatagramPacket pack = new DatagramPacket(request, 
														 Config.BUFFER_SIZE);
				mServerDatagram.receive(pack);
				
				//显示请求
				String info = mUtil.getPackInfo(pack);
				String data = mUtil.getPacketData(pack);
				mUtil.showResponse(mHandler, Config.SERVER_ID, "Got '"+info+"'");				
				mUtil.showResponse(mHandler, Config.SERVER_ID, "Got '"+data+"'");

				//发送答复
				String msg = Config.RET_FLAG+data;
				byte[] respond = msg.getBytes();
				DatagramPacket newPack = new DatagramPacket(respond, 
						0, respond.length, pack.getAddress(), pack.getPort() );
				mServerDatagram.send(newPack);	
				mUtil.showResponse(mHandler, Config.SERVER_ID, "Sent '"+msg+"'");				
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
};
