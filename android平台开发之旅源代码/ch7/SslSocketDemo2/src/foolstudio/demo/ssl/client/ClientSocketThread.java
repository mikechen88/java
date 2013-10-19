package foolstudio.demo.ssl.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
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

public class ClientSocketThread extends Thread {
	
	public static final String EXTRAS_KEY ="status";
	public static final String SERVER_HOST="127.0.0.1";
	public static final int SERVER_PORT = 10086;
	//
    private static final String PASSWORD = "master2010";
	//
	private SSLSocket mClintSocket = null;
	//

	public ClientSocketThread(String ksPath) {
		
		try {
			initSSLClient(ksPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	//初始化客户端
	private void initSSLClient(String ksPath) throws NoSuchAlgorithmException, 
										KeyStoreException, 
										CertificateException, 
										UnrecoverableKeyException, 
										KeyManagementException {
		// TODO Auto-generated method stub
        try {   
            //取得SSLContext   
            SSLContext ctx = SSLContext.getInstance("TLS");   
            //取得SunX509私钥管理器   
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            
            TrustManagerFactory tmf = 
            	(TrustManagerFactory)TrustManagerFactory.getInstance("SunX509");
            
            KeyStore kks= KeyStore.getInstance("JKS", "SUN");
            KeyStore tks = KeyStore.getInstance("JKS", "SUN");
            
            kks.load(new FileInputStream(ksPath), 
            		 PASSWORD.toCharArray() );
            tks.load(new FileInputStream(ksPath), 
            		 PASSWORD.toCharArray() );
            
            kmf.init(kks, PASSWORD.toCharArray() );
            tmf.init(tks);
            
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            
            mClintSocket = 
            	(SSLSocket)ctx.getSocketFactory().createSocket(SERVER_HOST, 
            												   SERVER_PORT);
            
            sendStatus("Connect to " + SERVER_HOST + ":" + SERVER_PORT + " ...");
        } catch (Exception e) {   
        	// TODO Auto-generated catch block
			e.printStackTrace();  
        }   
    }

	//输出状态
	private void sendStatus(String status) {
		// TODO Auto-generated method stub
		System.out.println(status);	
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
	
	//请求服务端
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
		br.close();
		is.close();
		//
		pr.close();
		os.close();
	}	
};
