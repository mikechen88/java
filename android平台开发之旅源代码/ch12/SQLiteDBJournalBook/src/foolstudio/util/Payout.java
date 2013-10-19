package foolstudio.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Payout implements Parcelable {
	
	private String mTimestamp = null;
	private String mComments = null;
	private double mMoney = 0.0D;
	
	//必须要有一个名为CREATOR的成员对象，否则无法进行Parcelable对象通信
    public static final Parcelable.Creator<Payout> CREATOR = new Parcelable.Creator<Payout>() {
		public Payout createFromParcel(Parcel in) {
		    return new Payout(in);
		}
		
		public Payout[] newArray(int size) {
		    return new Payout[size];
		}
	};	

	//-------------------------------------------------------------------------
	public Payout(String timestamp, String comments, double money) {
		mTimestamp = timestamp;
		mComments = comments;
		mMoney = money;
	}

	//-------------------------------------------------------------------------
	//设置时间戳
	public void setTimestamp(String timestamp) {
		mTimestamp = timestamp;
	}

	//-------------------------------------------------------------------------
	//获取时间戳
	public String getTimestamp() {
		return (mTimestamp);
	}

	//-------------------------------------------------------------------------
	//设置抬头
	public void setComments(String comments) {
		mComments = comments;
	}

	//-------------------------------------------------------------------------
	//获取抬头
	public String getComments() {
		return (mComments);
	}

	//-------------------------------------------------------------------------
	//设置金额
	public void setMoney(double money) {
		mMoney = money;
	}

	//-------------------------------------------------------------------------
	//获取金额
	public double getMoney() {
		return (mMoney);
	}

	//-------------------------------------------------------------------------
	public String toString() {
		return (mTimestamp + "," + mComments + "," + mMoney);
	}
	
	//-------------------------------------------------------------------------	
	//实现Parcelable接口
	public Payout(Parcel in) {		
		this.mTimestamp = in.readString();
		this.mComments = in.readString();
		this.mMoney = in.readDouble();
	}	
	
	//-------------------------------------------------------------------------	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//-------------------------------------------------------------------------	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.mTimestamp);
		dest.writeString(this.mComments);
		dest.writeDouble(this.mMoney);
	}	

	//-------------------------------------------------------------------------
};
