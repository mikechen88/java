package foolstudio.demo.xml.common;

import android.os.Parcel;
import android.os.Parcelable;

//������
public class City implements Parcelable {
	
	public static final String TAG_NAME = "City";	
	
	private String name = null;
	private String code = null;
	
	//����Ҫ��һ����ΪCREATOR�ĳ�Ա���󣬷����޷�����Parcelable����ͨ��
    public static final Parcelable.Creator<City> CREATOR = 
    	new Parcelable.Creator<City>() {
		public City createFromParcel(Parcel in) {
		    return new City(in);
		}
		
		public City[] newArray(int size) {
		    return new City[size];
		}
	};
	
	//-------------------------------------------------------------------------	
	//ʵ��Parcelable�ӿ�
	public City(Parcel in) {		
		this.name = in.readString();
		this.code = in.readString();
	}	

	//-------------------------------------------------------------------------
	public City(final String __name, final String __code) {
		this.name = __name;
		this.code = __code;
	}

	//-------------------------------------------------------------------------
	//��ȡ��������
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