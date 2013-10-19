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
		
		//������
		//Log.d(this.getClass().getName(), "localName="+localName);
		//Log.d(this.getClass().getName(), "name="+name);
		
		//��ǰԪ���йع���/����
		if(localName.equalsIgnoreCase(Country.TAG_NAME) ) {
			String countryName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			Country country = new Country(countryName, code);
			mLocation.addCountry(country);

			mCurCountry = country; //��¼��ǰ����/����
		}
		else if(localName.equals(State.TAG_NAME) ) { //��ǰԪ���й���/ʡ
			String stateName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			//���������Ĺ���/����Ϊ������������/�������/ʡ�б�
			if(mCurCountry != null) {
				State state = new State(stateName, code);
				mCurCountry.addState(state);

				mCurState = state; //��¼��ǰ��/ʡ
			}
		}
		else if(localName.equals(City.TAG_NAME) ) { //��ǰԪ���йس���
			String cityName = attributes.getValue("Name");
			String code = attributes.getValue("Code");

			//������������/ʡ��Ϊ�����������/ʡ�ĳ����б�
			if(mCurState != null) {
				City city = new City(cityName, code);
				mCurState.addCity(city);
			}
		}		
	}

	//--------------------------------------------------------------------------
};
