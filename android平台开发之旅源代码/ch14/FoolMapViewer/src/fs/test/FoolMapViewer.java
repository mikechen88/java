package fs.test;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.os.Bundle;

public class FoolMapViewer extends MapActivity {	
	
	private MapView mMapView = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMapView = (MapView) findViewById(R.id.MV_MAIN);
        mMapView.setBuiltInZoomControls(true);        
    }
    
    @Override
    public boolean isRouteDisplayed() {
    	return (false);
    }
};