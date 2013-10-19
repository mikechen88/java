package foolstudio.demo.net.client;

import java.io.IOException;
import java.net.DatagramPacket;

import android.os.Handler;

import foolstudio.demo.net.Config;
import foolstudio.demo.net.DatagramUtil;

public class ClientThread extends Thread {
	
	private ClientDatagramDemoAct mContext = null;	
	private Handler mHandler = null;
	private String mData = null;
	private DatagramUtil mUtil = DatagramUtil.getInstance();

	public ClientThread(ClientDatagramDemoAct ctx, Handler handler, String data) {
		// TODO Auto-generated constructor stub
		this.mContext = ctx;
		this.mHandler = handler;
		this.mData = data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//发送请求
		byte[] data = mData.getBytes();
		
		DatagramPacket pack = new DatagramPacket(data, data.length);
		try {
			mContext.getClientDatagram().send(pack);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//获取回复
		byte[] respond = new byte[Config.BUFFER_SIZE];
		DatagramPacket newPack = new DatagramPacket(respond, respond.length);
		try {
			mContext.getClientDatagram().receive(newPack);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//显示回复
		String packIinfo = mUtil.getPackInfo(newPack);
		String dataString = mUtil.getPacketData(newPack);
		mUtil.showResponse(mHandler, Config.CLIENT_ID, "Got '"+packIinfo+"'");
		mUtil.showResponse(mHandler, Config.CLIENT_ID, "Got '"+dataString+"'");
	}
};
