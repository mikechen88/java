package foolstudio.demo.wireless.gsm;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.EditText;

public class GsmCellLocationDemoAct extends Activity {
	
	private EditText mTxtContents = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTxtContents = (EditText)findViewById(R.id.TXT_CONTENTS);
        
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
		LocationManager man = (LocationManager) getSystemService(LOCATION_SERVICE);
		List<String> provides = man.getAllProviders();
		
		if(provides.size() < 1) {
			return;
		}
		
		printText("Provider(s):");
		for(int i = 0; i < provides.size(); ++i) {
			printText("#" + (i+1) + "/" + provides.size()+": " + provides.get(i) );
			
			LocationProvider provider = man.getProvider(provides.get(i) );
			printText("Accuracy: " + provider.getAccuracy() );
		}
		
		Location location = man.getLastKnownLocation(provides.get(0) );

		if(location == null) {
			return;
		}
		
		printText("Latitude: " + location.getLatitude() );
		printText("Longitude: " + location.getLongitude() );
	}
	
	private void printText(String text) {
		mTxtContents.append(text);
		mTxtContents.append("\n");
	}
};