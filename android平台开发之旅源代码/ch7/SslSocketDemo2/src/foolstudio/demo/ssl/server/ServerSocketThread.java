package foolstudio.demo.ssl.server;

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
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;

public class ServerSocketThread extends Thread {
	
	public static final String EXTRAS_KEY ="status";
	public static final int SERVER_PORT = 10086;
	//
    private static final String PASSWORD = "master2010";
	//
	private SSLServerSocket mServSocket = null;
	//
	private boolean mIsRunning = false;
	
	public ServerSocketThread(String ksPath) {
		
		try {
			initSSLServer(ksPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	//初始化服务器
	private void initSSLServer(String ksPath) throws NoSuchAlgorithmException, 
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
            //取得JKS密库实例   
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");   
            //加载服务端私钥   
            ks.load(new FileInputStream(ksPath), 
            		PASSWORD.toCharArray() );   
            //初始化   
            kmf.init(ks, PASSWORD.toCharArray());   
            //初始化SSLContext   
            ctx.init(kmf.getKeyManagers(),null, null);   
            //通过SSLContext取得ServerSocketFactory，创建ServerSocket   
            mServSocket = (SSLServerSocket)
            	ctx.getServerSocketFactory().createServerSocket(SERVER_PORT);   
            
            sendStatus("Server starting @ port " + SERVER_PORT + " ...");
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

	public void setIsRunning(boolean isRunning) {
		this.mIsRunning = isRunning;
	}	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(mIsRunning) {
			try {
				SSLSocket clientSocket = (SSLSocket)mServSocket.accept();
				sendStatus("Accept client '" + 
						clientSocket.getInetAddress().getHostAddress() + ":" +
						clientSocket.getPort() + "'");
				
				replyClient(clientSocket);
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}

	//回复给客户端
	private void replyClient(SSLSocket clientSocket) throws IOException {
		// TODO Auto-generated method stub
		InputStream is = clientSocket.getInputStream();
		OutputStream os = clientSocket.getOutputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		String line = null;
		
		if((line=br.readLine()) != null) {
			sendStatus("Got messge '" + line + "'");
		}
		
		PrintWriter pr = new PrintWriter(os);
		
		String reply = "Talking over, bye!";
		pr.println(reply);
		pr.flush();
		sendStatus("Reply '" + reply + "'");
		//
		pr.close();
		br.close();
		//
		os.close();		
		is.close();
	}
};
