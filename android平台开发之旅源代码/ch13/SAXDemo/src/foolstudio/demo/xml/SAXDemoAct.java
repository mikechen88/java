package foolstudio.demo.xml;

import java.util.ArrayList;

import foolstudio.demo.xml.common.City;
import foolstudio.demo.xml.common.Country;
import foolstudio.demo.xml.common.Location;
import foolstudio.demo.xml.common.State;
import foolstudio.demo.xml.util.SAXUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SAXDemoAct extends Activity implements OnItemSelectedListener {
	
	public static final String EXTRA_NAME1 = "City";
	public static final String EXTRA_NAME2 = "Province";
	public static final String EXTRA_NAME3 = "Country";	
	
	private Spinner mSpnCountries = null;
	private Spinner mSpnProvinces = null;
	private Spinner mSpnCities = null;
	private Location mLocation = null;
	//����������
	private ArrayList<Country> mCountries = new ArrayList<Country>();
	private ArrayList<State> mStates = new ArrayList<State>();;
	private ArrayList<City> mCities = new ArrayList<City>();;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        //��ȡ��ʾ���
        this.mSpnCountries = (Spinner)findViewById(R.id.SPN_COUNTRY);
        this.mSpnProvinces = (Spinner)findViewById(R.id.SPN_STATE);
        this.mSpnCities = (Spinner)findViewById(R.id.SPN_CITY);
        //����������        
        ArrayAdapter<Country> adapter1 = new ArrayAdapter<Country>(this, 
				android.R.layout.simple_spinner_item, mCountries);
		adapter1.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		this.mSpnCountries.setAdapter(adapter1);
		
        ArrayAdapter<State> adapter2 = new ArrayAdapter<State>(this, 
				android.R.layout.simple_spinner_item, mStates);
		adapter2.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		this.mSpnProvinces.setAdapter(adapter2);
		
        ArrayAdapter<City> adapter3 = new ArrayAdapter<City>(this, 
				android.R.layout.simple_spinner_item, mCities);
		adapter3.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		this.mSpnCities.setAdapter(adapter3);
		//��������
        this.mSpnCountries.setOnItemSelectedListener(this);
        this.mSpnProvinces.setOnItemSelectedListener(this);
        this.mSpnCities.setOnItemSelectedListener(this);
        
        mLocation = new Location();
        
        init();
    }
    
    //��ʼ������
	private void init() {
		// TODO Auto-generated method stub
		super.onStart();
		SAXUtil.getInstance().parse(
				this.getResources().openRawResource(R.raw.cities), mLocation);
		
		/*
		int countriesCount = mLocation.getCountries().size();
		int statesCount = mLocation.getCountries().get(countriesCount-1)
							.getStates().size();
		int citiesCount = mLocation.getCountries().get(countriesCount-1).
							getStates().get(0).getCities().size();
		Log.d(this.getClass().getName(), "" + countriesCount + " countries.");
		Log.d(this.getClass().getName(), "" + statesCount + " states.");
		Log.d(this.getClass().getName(), "" + citiesCount + " cities.");	
		*/
		
		//������ݼ�������
		clearAllDataSet();
		//�������ݼ�������
		mCountries.addAll(mLocation.getCountries() );
		mStates.addAll(mLocation.getCountries().get(0).getStates() );
		mCities.addAll(mLocation.getCountries().get(0).
						getStates().get(0).getCities() );
		//֪ͨ���ݼ��ı䣨����
		notifyAllDataSet();
	}

	//֪ͨ�������ݼ��Ѿ��ı䣨����
	private void notifyAllDataSet() {
		// TODO Auto-generated method stub
		((ArrayAdapter)this.mSpnCountries.getAdapter()).notifyDataSetChanged();
		((ArrayAdapter)this.mSpnProvinces.getAdapter()).notifyDataSetChanged();
		((ArrayAdapter)this.mSpnCities.getAdapter()).notifyDataSetChanged();		
	}

	//����������ݼ�������
	private void clearAllDataSet() {
		// TODO Auto-generated method stub
		mCountries.clear();
		mStates.clear();
		mCities.clear();		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
		
		Log.d(this.getClass().getName(), "onItemSelected("+parent.getId()+","+ 
				view.getId()+","+ pos+","+ id+")");
		
		switch(parent.getId() ) {
			case R.id.SPN_COUNTRY: {
				doCountrySelected(pos);
				break;
			}
			case R.id.SPN_STATE: {
				doSateSelected(pos);
				break;
			}
			case R.id.SPN_CITY: {
				doCitySelected(pos);
				break;
			}			
		}
	}
	
	//ѡ�����
	private void doCountrySelected(int position) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "doCountrySelected("+position+")");
		
		//������ݼ�������
		mStates.clear();
		mCities.clear();
		//�������ݼ�������
		mStates.addAll(mLocation.getCountries().get(position).getStates() );
		mCities.addAll(mLocation.getCountries().get(position).
						getStates().get(0).getCities() ); 
		//֪ͨ���ݼ��ı䣨����		
		((ArrayAdapter)this.mSpnProvinces.getAdapter()).notifyDataSetChanged();
		((ArrayAdapter)this.mSpnCities.getAdapter()).notifyDataSetChanged();
		//��ʼ������ѡ�������
		this.mSpnProvinces.setSelection(0);
		this.mSpnCities.setSelection(0);
	}	
	
	//ѡ����/ʡ
	private void doSateSelected(int position) {
		// TODO Auto-generated method stub
		Log.d(this.getClass().getName(), "doSateSelected("+position+")");
		
		int countryPosition = this.mSpnCountries.getSelectedItemPosition();		
		
		//������ݼ�������
		mCities.clear();
		//�������ݼ�������
		mCities.addAll(mLocation.getCountries().get(countryPosition).
						getStates().get(position).getCities() );
		//֪ͨ���ݼ��ı䣨����
		((ArrayAdapter)this.mSpnCities.getAdapter()).notifyDataSetChanged();
		//��ʼ������ѡ�������
		this.mSpnCities.setSelection(0);		
	}	

	//ѡ�����
	private void doCitySelected(int position) {
		// TODO Auto-generated method stub		
		Log.d(this.getClass().getName(), "doCitySelected("+position+")");
		
		int countryPosition = this.mSpnCountries.getSelectedItemPosition();
		int statePosition = this.mSpnProvinces.getSelectedItemPosition();
		
		Country country =  mLocation.getCountries().get(countryPosition);
		State state = country.getStates().get(statePosition);		
		City city = state.getCities().get(position);
		
		Log.d(this.getClass().getName(), "City: "+city.getName()+", Code: "+
											city.getCode() );
		Log.d(this.getClass().getName(), "State: "+state.getName()+", Code: "+
											state.getCode() );
		Log.d(this.getClass().getName(), "Country: "+country.getName()+", Code: "+
											country.getCode() );
		Intent intentStart = new Intent(this, CityInfoAct.class);
			intentStart.putExtra(EXTRA_NAME1, city);
			intentStart.putExtra(EXTRA_NAME2, state);
			intentStart.putExtra(EXTRA_NAME3, country);
		this.startActivity(intentStart);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
};