package foolstudio.demo.xml.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import foolstudio.demo.xml.common.City;
import foolstudio.demo.xml.common.Country;
import foolstudio.demo.xml.common.Location;
import foolstudio.demo.xml.common.State;

public class ParseHandler extends DefaultHandler {
	
	private Location mLocation = null;
	private Country mCurCountry = null;
	private State mCurState = null;	

	public ParseHandler(Location district) {
		// TODO Auto-generated constructor stub
		this.mLocation = district;
	}

	//--------------------------------------------------------------------------	
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, name, attributes);
		
		//调试用
		//Log.d(this.getClass().getName(), "localName="+localName);
		//Log.d(this.getClass().getName(), "name="+name);
		
		//当前元素有关国家/区域
		if(localName.equalsIgnoreCase(Country.TAG_NAME) ) {
			String countryName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			Country country = new Country(countryName, code);
			mLocation.addCountry(country);

			mCurCountry = country; //记录当前国家/区域
		}
		else if(localName.equals(State.TAG_NAME) ) { //当前元素有关州/省
			String stateName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			//如果其从属的国家/区域不为空则添加其国家/区域的州/省列表
			if(mCurCountry != null) {
				State state = new State(stateName, code);
				mCurCountry.addState(state);

				mCurState = state; //记录当前州/省
			}
		}
		else if(localName.equals(City.TAG_NAME) ) { //当前元素有关城市
			String cityName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			//如果其从属的州/省不为空则添加其州/省的城市列表
			if(mCurState != null) {
				City city = new City(cityName, code);
				mCurState.addCity(city);
			}
		}		
	}

	//--------------------------------------------------------------------------
};
