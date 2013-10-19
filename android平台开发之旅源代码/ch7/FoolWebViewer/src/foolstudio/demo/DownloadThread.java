apackage foolstudio.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DownloadThread extends Thread {
	
	private String mUrl = null;
	private Handler mHandler = null;

	public DownloadThread(String url, Handler handler) {
		super();
		// TODO Auto-generated constructor stub
		this.mUrl = url;
		this.mHandler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(mUrl);
			
			String fileName = url.getFile();
			//将路径名替换成下划线（防止文件名重名）
			fileName = fileName.replace(File.separatorChar, '_');
			
			System.out.println("DownloadThread: " + fileName);
			//建立URL连接
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			
			FileOutputStream fos = new FileOutputStream(Config.DOWNLOAD_DIR+
												File.separatorChar+fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int aByte = -1;
			//开始下载文件			
			while( (aByte=bis.read()) != -1) {
				bos.write(aByte);
			}
			
			//关闭输出流
			bos.close();
			fos.close();
			
			//关闭输入流
			bis.close();
			is.close();		
			
			showResponse("Save '"+Config.DOWNLOAD_DIR+
						 File.separatorChar+fileName + "' OK!");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//super.run();
	}

	//向主界面线程消息队列发送消息
	private void showResponse(String data) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putCharSequence("Sender", "DownloadThread");
		bundle.putString("Msg", data);
		Message msg = new Message();
		msg.setData(bundle);
		mHandler.sendMessage(msg);		
	}	
};
