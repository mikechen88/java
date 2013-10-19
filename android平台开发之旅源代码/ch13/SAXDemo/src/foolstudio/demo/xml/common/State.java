package foolstudio.demo.xml.common;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

//州/省类
public class State implements Parcelable {
	
	public static final String TAG_NAME = "State";		
	
	private	String name = null;
	private	String code = null;
	private ArrayList<City> cities = null;
	
	//必须要有一个名为CREATOR的成员对象，否则无法进行Parcelable对象通信
    public static final Parcelable.Creator<State> CREATOR = 
    	new Parcelable.Creator<State>() {
		public State createFromParcel(Parcel in) {
		    return new State(in);
		}
		
		public State[] newArray(int size) {
		    return new State[size];
		}
	};
	
	//-------------------------------------------------------------------------	
	//实现Parcelable接口
	public State(Parcel in) {		
		this.name = in.readString();
		this.code = in.readString();
	}	

	//-------------------------------------------------------------------------
	public State(String name, String code) {
		this.name = name;
		this.code = code;

		cities = new ArrayList<City>();
	}

	//-------------------------------------------------------------------------
	//获取州/省名称
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
	
	public void addCity(City city) {
		this.cities.add(city);
	}

	public ArrayList<City> getCities() {
		return this.cities;
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