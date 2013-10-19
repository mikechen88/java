package foolstudio.demo.xml.common;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

//国家/地区类
public class Country implements Parcelable {
	
	public static final String TAG_NAME = "CountryRegion";	
	
	private	String name = null;
	private	String code = null;
	private ArrayList<State> states = null;
	
	//必须要有一个名为CREATOR的成员对象，否则无法进行Parcelable对象通信
    public static final Parcelable.Creator<Country> CREATOR = 
    	new Parcelable.Creator<Country>() {
		public Country createFromParcel(Parcel in) {
		    return new Country(in);
		}
		
		public Country[] newArray(int size) {
		    return new Country[size];
		}
	};
	
	//-------------------------------------------------------------------------	
	//实现Parcelable接口
	public Country(Parcel in) {		
		this.name = in.readString();
		this.code = in.readString();
	}	

	//-------------------------------------------------------------------------
	public Country(String name, String code) {
		this.name = name;
		this.code = code;

		this.states = new ArrayList<State>();
	}

	//-------------------------------------------------------------------------
	//获取国家/地区名称
	public String getName() { 
		if(this.name == null) {
			return ("(Default)");
		}

		return (this.name);  
	}

	//-------------------------------------------------------------------------
	//获取代码
	public String getCode() { 
		return (this.code); 
	}
	
	public void addState(State state) {
		this.states.add(state);
	}

	public ArrayList<State> getStates() {
		return this.states;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (getName() );
	}
	
	//--------------------------------------------------------------------------
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.name);
		dest.writeString(this.code);
	}

	//-------------------------------------------------------------------------
};