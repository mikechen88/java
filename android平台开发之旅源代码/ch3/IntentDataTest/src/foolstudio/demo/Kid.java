package foolstudio.demo;

import android.os.Parcel;
import android.os.Parcelable;

public class Kid implements Parcelable {
	
	public static final int SEX_FEMALE = 1;
	public static final int SEX_MALE = 0;	
	
	private String name = null;
	private int sex = 1;
	private String birthday = null;
	
	//必须要有一个名为CREATOR的成员对象，否则无法进行Parcelable对象通信
    public static final Parcelable.Creator<Kid> CREATOR = 
    	new Parcelable.Creator<Kid>() {
		public Kid createFromParcel(Parcel in) {
		    return new Kid(in);
		}
		
		public Kid[] newArray(int size) {
		    return new Kid[size];
		}
	};	
	
	public Kid(String _name, int _sex, String _birthday) {		
		this.birthday = _birthday;
		this.sex = _sex;
		this.name = _name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSex() {
		return sex;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Name: ");
		sb.append(this.name + "\n");
		sb.append("Sex: ");
		sb.append( ((this.sex == 1) ? "Female" : "Male") + "\n");
		sb.append("Birthday: ");
		sb.append(this.birthday );
		
		return (sb.toString() );
	}

	//实现Parcelable接口
	public Kid(Parcel in) {		
		this.name = in.readString();
		this.sex = in.readInt();
		this.birthday = in.readString();
	}	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.name);
		dest.writeInt(this.sex);
		dest.writeString(this.birthday);
	}
};
