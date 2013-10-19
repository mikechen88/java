package foolstudio.demo.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HttpDemo3Act extends Activity implements OnClickListener {
	
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
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				break;
			}
		}
	}

	private void doGo() throws UnknownHostException, IOException, HttpException {
		// TODO Auto-generated method stub
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet method = new HttpGet(mTxtURL.getText().toString() );
		HttpResponse response = client.execute(method);
		
		//获取应答项
		StatusLine statusLine = response.getStatusLine();
		
		//状态判断
		if(statusLine.getStatusCode() != HttpStatus.SC_OK) {
			showResponse("Method failed: " + statusLine.getStatusCode() );
			return;
		}
		
		//HTTP头部
		Header[] headers = response.getAllHeaders();			
		for(int i = 0; i < headers.length; ++i) {
			HeaderElement[] elements = headers[i].getElements();
			
			for(int j = 0; j < elements.length; ++j) {
				NameValuePair[] pairs = elements[j].getParameters();
				
				for(int k = 0; k < pairs.length; ++k) {
					showResponse(pairs[k].getName()+"="+pairs[k].getValue() );
				}
			}
		}
		
		//内容
		HttpEntity entry = response.getEntity();		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(entry.getContent()));
		String line = null;
		
		while((line=br.readLine())!= null) {
			showResponse(line);
		}
		
		br.close();
	}

	private void showResponse(String string) {
		// TODO Auto-generated method stub
		mTxtContents.append(string);
		mTxtContents.append("\n");
		Log.d(this.getClass().getName(), string);
	}
};