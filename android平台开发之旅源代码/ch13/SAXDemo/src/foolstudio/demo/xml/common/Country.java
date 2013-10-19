package foolstudio.demo.xml.common;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

//����/������
public class Country implements Parcelable {
	
	public static final String TAG_NAME = "CountryRegion";	
	
	private	String name = null;
	private	String code = null;
	private ArrayList<State> states = null;
	
	//����Ҫ��һ����ΪCREATOR�ĳ�Ա���󣬷����޷�����Parcelable����ͨ��
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
	//ʵ��Parcelable�ӿ�
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
	//��ȡ����/��������
	public String getName() { 
		if(this.name == null) {
			return ("(Default)");
		}

		return (this.name);  
	}

	//-------------------------------------------------------------------------
	//��ȡ����
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