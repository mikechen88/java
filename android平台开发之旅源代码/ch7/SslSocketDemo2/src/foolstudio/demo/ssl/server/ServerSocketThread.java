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

	//��ʼ��������
	private void initSSLServer(String ksPath) throws NoSuchAlgorithmException, 
										KeyStoreException, 
										CertificateException, 
										UnrecoverableKeyException, 
										KeyManagementException {
		// TODO Auto-generated method stub
        try {   
            //ȡ��SSLContext   
            SSLContext ctx = SSLContext.getInstance("TLS");   
            //ȡ��SunX509˽Կ������   
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");   
            //ȡ��JKS�ܿ�ʵ��   
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");   
            //���ط����˽Կ   
            ks.load(new FileInputStream(ksPath), 
            		PASSWORD.toCharArray() );   
            //��ʼ��   
            kmf.init(ks, PASSWORD.toCharArray());   
            //��ʼ��SSLContext   
            ctx.init(kmf.getKeyManagers(),null, null);   
            //ͨ��SSLContextȡ��ServerSocketFactory������ServerSocket   
            mServSocket = (SSLServerSocket)
            	ctx.getServerSocketFactory().createServerSocket(SERVER_PORT);   
            
            sendStatus("Server starting @ port " + SERVER_PORT + " ...");
        } catch (Exception e) {   
        	// TODO Auto-generated catch block
			e.printStackTrace();  
        }   
    }

	//���״̬
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

	//�ظ����ͻ���
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
