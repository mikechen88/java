package foolstudio.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import foolstudio.util.FoolUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ContextFileDemoAct extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_view);
        
        Button btnStore = (Button)findViewById(R.id.BTN_STORE);
        Button btnFetch = (Button)findViewById(R.id.BTN_FETCH);
        //
        btnStore.setOnClickListener(this);
        btnFetch.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_STORE: {
				doStore();
				break;
			}
			case R.id.BTN_FETCH: {
				doFetch();
				break;
			}
		}
	}
	
	private void doStore() {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos = this.openFileOutput(Config.CONTEXT_FILE, Activity.MODE_WORLD_WRITEABLE);
			OutputStreamWriter osr = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osr);
			
			String str = "Hello, Android!";
			bw.write(str);
			
			bw.close();
			osr.close();
			fos.close();
			
			FoolUtil.showMsg(this, "Store " + str + " successfully!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void doFetch() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = this.openFileInput(Config.CONTEXT_FILE);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			String line = br.readLine();
			
			br.close();
			isr.close();
			fis.close();
			
			FoolUtil.showMsg(this, line);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
};
