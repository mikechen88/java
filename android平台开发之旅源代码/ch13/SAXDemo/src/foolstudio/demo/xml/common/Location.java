package foolstudio.demo.xml.common;

import java.util.ArrayList;

//Çø»®
public class Location {
	
	public static final String TAG_NAME = "Location";
	
	private ArrayList<Country> countries = null;

	//-------------------------------------------------------------------------
	public Location() {
		this.countries = new ArrayList<Country>();
	}
	
	public void addCountry(Country country) {
		this.countries.add(country);
	}

	public ArrayList<Country> getCountries() {
		return this.countries;
	}

	//-------------------------------------------------------------------------
};