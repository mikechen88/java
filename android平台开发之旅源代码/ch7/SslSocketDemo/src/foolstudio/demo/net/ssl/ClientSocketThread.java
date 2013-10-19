package foolstudio.demo.net.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ClientSocketThread extends Thread {
	
	public static final String EXTRAS_KEY ="status";
	public static final String SERVER_HOST = "127.0.0.1";
	public static final int SERVER_PORT = 10086;
	//
    private static final String PASSWORD = "master2010";
	
	private Context mContext = null;
	private Handler mHandler = null;
	//
	private SSLSocket mClintSocket = null;
	
	public ClientSocketThread(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
		
		try {
			initSSLClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	private void initSSLClient() throws NoSuchAlgorithmException, 
										KeyStoreException, 
										CertificateException, 
										UnrecoverableKeyException, 
										KeyManagementException {
		// TODO Auto-generated method stub
        try {   
            //(1)
            SSLContext ctx = SSLContext.getInstance("TLS");   
            //(2)
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            //(3)
            KeyStore kks= KeyStore.getInstance("BKS", "BC");
            KeyStore tks = KeyStore.getInstance("BKS", "BC");
            //(4)
            kks.load(mContext.getResources().openRawResource(R.raw.foolstudio), PASSWORD.toCharArray() );
            tks.load(mContext.getResources().openRawResource(R.raw.foolstudio), PASSWORD.toCharArray() );
            //(5)
            kmf.init(kks, PASSWORD.toCharArray() );
            tmf.init(tks);
            //(6)
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            //(7)
            mClintSocket = 
            	(SSLSocket)ctx.getSocketFactory().createSocket(SERVER_HOST, SERVER_PORT);
            
            sendStatus("Connect to " + SERVER_HOST + ":" + SERVER_PORT + " ...");
        } catch (Exception e) {   
        	// TODO Auto-generated catch block
			e.printStackTrace();  
        }   
    }

	private void sendStatus(String status) {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putString(EXTRAS_KEY, status);
		
		Message msg = new Message();
		msg.setData(bundle);
		
		mHandler.sendMessage(msg);		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(this.mClintSocket != null) {
			try {
				requestServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
	
	private void requestServer() throws IOException {
		// TODO Auto-generated method stub
		OutputStream os = mClintSocket.getOutputStream();
		PrintWriter pr = new PrintWriter(os);
		
		String request = "Hello, SSL Server!";
		pr.println(request);
		pr.flush();
		sendStatus("Request '" + request+ "'");		
		
		InputStream is = mClintSocket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		String line = null;
		
		if((line=br.readLine()) != null) {
			sendStatus("Got message '" + line + "'");
		}
		
		//
		mClintSocket.close();
	}
};
