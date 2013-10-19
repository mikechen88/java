package foolstudio.demo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HTTPDemoAct extends Activity implements OnClickListener {
	
	private static final int SERVER_PORT = 80;
	private static final int BUFFER_SIZE = 8192; //8KB
	
	private EditText mTxtURL = null;
	private Button mBtnGo = null;
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        mTxtURL = (EditText)findViewById(R.id.TXT_URL);
        mBtnGo = (Button)findViewById(R.id.BTN_GO);
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        mBtnGo.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_GO: {
				try {
					doGo();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	private void doGo() throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket clientSocket = new Socket(mTxtURL.getText().toString(), SERVER_PORT);
		//获取答复用
		InputStream is = clientSocket.getInputStream();
		//发送请求用
		OutputStream os = clientSocket.getOutputStream();
		
		sendRequest(os);
		getResponse(is);
		
		//
		os.close();
		is.close();
	}

	private void getResponse(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		//先接受HTTP头部
		byte[] headerContents = new byte[BUFFER_SIZE];
		if(is.read(headerContents) != -1) {
			showResponse(new String(headerContents) );
		}
		headerContents = null;
		
		//再接受HTTP内容
		BufferedReader br = new BufferedReader(new InputStreamReader(is), BUFFER_SIZE);
		String line = null;
		
		while((line=br.readLine()) != null) {
			showResponse(line);
		}
		
		br.close();
	}

	private void showResponse(String string) {
		// TODO Auto-generated method stub
		mTxtContents.append(string);
		Log.d(this.getClass().getName(), string);
	}

	private void sendRequest(OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		String request = "GET / HTTP1.1\r\n\r\n";
		os.write(request.getBytes() );
		os.flush();
		//注意：不能在这里关闭输出流
		//os.close();
	}
};