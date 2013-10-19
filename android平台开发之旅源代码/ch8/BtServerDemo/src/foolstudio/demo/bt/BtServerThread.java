package foolstudio.demo.bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class BtServerThread extends Thread {
	
	private BluetoothServerSocket mServerSocket = null;
	private boolean canRunning = true;

	public BtServerThread(BluetoothAdapter adapter) {
		// TODO Auto-generated constructor stub
		if(adapter == null) {
			return;
		}
		
		
		//��ȡ���������׽���
		try {
			this.mServerSocket= 
				adapter.listenUsingRfcommWithServiceRecord(BtConfig.BT_SDP, 
														   BtConfig.BT_UUID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void setCanRunning(boolean canRunning) {
		this.canRunning = canRunning;
	}	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if(this.mServerSocket == null) {
			return;
		}
		
		try {
			while(canRunning) {
				//�ȴ��ͻ��˵�����
				BluetoothSocket socket = this.mServerSocket.accept();
				//��ͻ��˽���ͨ��
				talkVia(socket);
				//ͨ����ϣ��ر�����
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(mServerSocket != null) {
				try {
					mServerSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 			
		}
		
		super.run();
	}

	//��ͻ��˽���ͨ��
	private void talkVia(BluetoothSocket socket) throws IOException {
		// TODO Auto-generated method stub
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		
		//���տͻ�������
		while( (line=br.readLine()) != null) {
			Log.d(this.getClass().getSimpleName(), line);
		}
		
		//�ظ��ͻ���
		PrintWriter pw = new PrintWriter(os);
		pw.println("There are Bluetooth Server.");
		pw.flush();
		
		pw.close();
		br.close();
	}
};
