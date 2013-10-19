package foolstudio.demo.net.ssl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HttpsDemoAct extends Activity implements OnClickListener {
	
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
		URL url = new URL(mTxtURL.getText().toString() );
		HttpsURLConnection conn = (HttpsURLConnection)(url.openConnection() );
		//获取答复用
		InputStream is = conn.getInputStream();

		getHeader(conn);
		getResponse(is);

		//关闭输入流
		is.close();
	}

	//获取HTTP头部
	private void getHeader(HttpURLConnection conn) {
		// TODO Auto-generated method stub
		Map<String,List<String>> header = conn.getHeaderFields();
		
		for(int i = 0; i < header.size(); ++i) {
			String key = conn.getHeaderFieldKey(i);
			List<String> entries = header.get(key);
			
			if( (key == null) || (entries == null) ) {
				continue;
			}
			
			for(int j = 0; j < entries.size(); ++j) {
				showResponse(key+": "+entries.get(j) );
			}
		}
	}

	private void getResponse(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		
		//再接受HTTP内容
		BufferedReader br = new BufferedReader(new InputStreamReader(is), 
											   BUFFER_SIZE);
		String line = null;
		
		while((line=br.readLine()) != null) {
			showResponse(line);
		}
	}

	private void showResponse(String string) {
		// TODO Auto-generated method stub
		mTxtContents.append(string);
		mTxtContents.append("\n");
		Log.d(this.getClass().getName(), string);
	}
}