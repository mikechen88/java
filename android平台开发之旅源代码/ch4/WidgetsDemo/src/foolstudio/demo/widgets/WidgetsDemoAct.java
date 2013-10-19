package foolstudio.demo.widgets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

public class WidgetsDemoAct extends Activity {
	
	private ZoomButton btnZoomin = null;
	private ZoomButton btnZoomout= null;
	private ZoomControls btnZoomCtrl = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnZoomin = (ZoomButton)findViewById(R.id.BTN_ZOOMIN);
        btnZoomout = (ZoomButton)findViewById(R.id.BTN_ZOOMOUT);
        btnZoomCtrl = (ZoomControls)findViewById(R.id.BTN_ZOOMCTRL);
        
        btnZoomin.setZoomSpeed(100);
        btnZoomout.setZoomSpeed(100);
        btnZoomCtrl.setZoomSpeed(100);
    }
}