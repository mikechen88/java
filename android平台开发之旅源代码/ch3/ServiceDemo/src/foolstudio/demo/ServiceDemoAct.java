package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceDemoAct extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnStart = (Button)findViewById(R.id.BTN_START);
        Button btnStop = (Button)findViewById(R.id.BTN_STOP);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_START: {
				doStart();
				break;
			}
			case R.id.BTN_STOP: {
				doStop();
				break;
			}			
		}
	}
	
	//开始服务
	private void doStart() {
		Intent startService = new Intent(this, DummyService.class);
		this.startService(startService);
	}
	
	//停止服务
	private void doStop() {
		Intent startService = new Intent(this, DummyService.class);
		this.stopService(startService);		
	}
};