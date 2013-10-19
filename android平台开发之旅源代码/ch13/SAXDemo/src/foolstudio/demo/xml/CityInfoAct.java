package foolstudio.demo.xml;

import foolstudio.demo.xml.common.City;
import foolstudio.demo.xml.common.Country;
import foolstudio.demo.xml.common.State;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CityInfoAct extends Activity {
	
	private TextView mTxtCityName = null;
	private TextView mTxtCityCode = null;
	private TextView mTxtProvinceName = null;
	private TextView mTxtProvinceCode = null;
	private TextView mTxtCountryName = null;
	private TextView mTxtCountryCode = null;
	
	private City mCity = null;
	private State mProvince = null;
	private Country mCountry = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        
    	mTxtCityName = (TextView)findViewById(R.id.LAB_CITY_NAME);
    	mTxtCityCode = (TextView)findViewById(R.id.LAB_CITY_CODE);
    	mTxtProvinceName = (TextView)findViewById(R.id.LAB_STATE_NAME);
    	mTxtProvinceCode = (TextView)findViewById(R.id.LAB_STATE_CODE);
    	mTxtCountryName = (TextView)findViewById(R.id.LAB_COUNTRY_NAME);
    	mTxtCountryCode = (TextView)findViewById(R.id.LAB_COUNTRY_CODE);
    	
    	Intent intent = this.getIntent();
    	Bundle bundle = intent.getExtras();
    	
    	if(bundle == null) {
    		return;
    	}
    	
    	mCity = (City)bundle.getParcelable(SAXDemoAct.EXTRA_NAME1);
    	mProvince = (State)bundle.getParcelable(SAXDemoAct.EXTRA_NAME2);
    	mCountry = (Country)bundle.getParcelable(SAXDemoAct.EXTRA_NAME3);    	
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		if(this.mCity == null) {
			return;
		}
		
		showCity();
	}

	//显示城市信息
	private void showCity() {
		// TODO Auto-generated method stub
		if(mCity != null) {
			mTxtCityName.setText(mCity.getName() );
			mTxtCityCode.setText(mCity.getCode() );
		}
		
		if(mProvince != null) {
			mTxtProvinceName.setText(mProvince.getName() );
			mTxtProvinceCode.setText(mProvince.getCode() );
		}
		
		if(mCountry != null) {
			mTxtCountryName.setText(mCountry.getName() );
			mTxtCountryCode.setText(mCountry.getCode() );	
		}
	}
};
