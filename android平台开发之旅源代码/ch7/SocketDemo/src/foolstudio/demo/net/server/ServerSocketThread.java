package foolstudio.demo.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import foolstudio.demo.net.Config;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//服务器端通信线程
public class ServerSocketThread extends Thread {
	
	private Socket mClientSocket = null;
	private BufferedReader mStreamReader = null;
	private PrintWriter mStreamWriter = null;
	private boolean mIsFinish = false;
	private Handler mHandler = null;

	public ServerSocketThread(Socket socket, Handler handler) {
		// TODO Auto-generated constructor stub
		this.mHandler = handler;
		this.mClientSocket = socket;
			
		try {
			mStreamReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()), 
					Config.BUFFER_SIZE);
			mStreamWriter = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(!mIsFinish) {
			String line = "";
			
			try {
				line = mStreamReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			showResponse("Got '" + line + "'");
			
			if(line.equalsIgnoreCase(Config.BYE) ) {
				doQuit();
				break;
			}					
			else if(line.equalsIgnoreCase(Config.HELLO) ) {
				doGreeting();
			}
			else {
				echoMsg(Config.RET_FLAG+line);
			}
		}		
	}

	//处理问候
	private void doGreeting() {
		// TODO Auto-generated method stub
		echoMsg("Welcome!");
	}

	//结束与当前客户端的通信
	private void doQuit() {
		// TODO Auto-generated method stub
		echoMsg(Config.BYE);
		
		try {
			mClientSocket.close();
			mStreamWriter.close();
			mStreamReader.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//回复消息
	private void echoMsg(String msg) {
		mStreamWriter.println(msg);
		
		showResponse("Send '" + msg + "'");
	}
	
	//设置该线程是否结束的标识
	public void setIsFinish(boolean isFinish) {
		this.mIsFinish = isFinish;
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
