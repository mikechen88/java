package foolstudio.demo.xml.common;

import android.os.Parcel;
import android.os.Parcelable;

//城市类
public class City implements Parcelable {
	
	public static final String TAG_NAME = "City";	
	
	private String name = null;
	private String code = null;
	
	//必须要有一个名为CREATOR的成员对象，否则无法进行Parcelable对象通信
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
	//实现Parcelable接口
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
	//获取城市名称
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