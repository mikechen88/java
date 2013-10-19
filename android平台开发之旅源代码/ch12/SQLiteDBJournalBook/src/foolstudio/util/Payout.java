package foolstudio.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Payout implements Parcelable {
	
	private String mTimestamp = null;
	private String mComments = null;
	private double mMoney = 0.0D;
	
	//����Ҫ��һ����ΪCREATOR�ĳ�Ա���󣬷����޷�����Parcelable����ͨ��
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
	//����ʱ���
	public void setTimestamp(String timestamp) {
		mTimestamp = timestamp;
	}

	//-------------------------------------------------------------------------
	//��ȡʱ���
	public String getTimestamp() {
		return (mTimestamp);
	}

	//-------------------------------------------------------------------------
	//����̧ͷ
	public void setComments(String comments) {
		mComments = comments;
	}

	//-------------------------------------------------------------------------
	//��ȡ̧ͷ
	public String getComments() {
		return (mComments);
	}

	//-------------------------------------------------------------------------
	//���ý��
	public void setMoney(double money) {
		mMoney = money;
	}

	//-------------------------------------------------------------------------
	//��ȡ���
	public double getMoney() {
		return (mMoney);
	}

	//-------------------------------------------------------------------------
	public String toString() {
		return (mTimestamp + "," + mComments + "," + mMoney);
	}
	
	//-------------------------------------------------------------------------	
	//ʵ��Parcelable�ӿ�
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
