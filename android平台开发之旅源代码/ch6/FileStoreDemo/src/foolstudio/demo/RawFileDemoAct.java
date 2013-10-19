package foolstudio.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import foolstudio.util.FoolUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RawFileDemoAct extends Activity implements OnClickListener {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raw_view);
        
        Button btnFetch = (Button)findViewById(R.id.BTN_FETCH);
        btnFetch.setOnClickListener(this);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_FETCH: {
				doFetch();
				break;
			}
		}
	}
	
	private void doFetch() {
		// TODO Auto-generated method stub
		try {
			InputStream is = this.getResources().openRawResource(R.raw.matrix);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			String contents = "";
			
			while((line = br.readLine()) != null) {
				contents += (line + "\n");
			}
			
			br.close();
			isr.close();
			is.close();
			
			FoolUtil.showMsg(this, contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}    
};
