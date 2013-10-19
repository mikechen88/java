package foolstudio.demo.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import foolstudio.demo.net.Config;
import foolstudio.demo.net.SocketDemoAct;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//服务线程
public class ServerThread extends Thread {

	private ServerSocket mServerSocket = null;
	private Handler mHandler = null;
	
	public ServerThread(SocketDemoAct ctx, Handler handler) {
		
		this.mHandler = handler;
		
		try {
			mServerSocket = new ServerSocket(Config.SERVER_PORT);
			showResponse("Server running @ "+Config.SERVER_PORT+" ...");					
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
		
		if(mServerSocket != null) {
			try {
				mServerSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	

	private void startServer() {
		// TODO Auto-generated method stub
		try {			
			while(true) {
				Socket socket = mServerSocket.accept();				
				
				//客户端服务线程（一个会话）
				showResponse("Accept client "+
						socket.getInetAddress().getHostAddress()+ ":" + 
						socket.getPort() );
				//
				ServerSocketThread t = new ServerSocketThread(socket, mHandler);
				t.start();
				
				//会话结束
				//clientSocket.close();
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//向主界面线程消息队列发送消息
	private void showResponse(String dataString) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putCharSequence("DEST", Config.SERVER_ID);
		bundle.putString("MSG", dataString);
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);		
	}	
};
