package foolstudio.demo.view;

import java.util.HashMap;

public class People {
	public static final String KEY1 = "NAME";
	public static final String KEY2 = "SEX";
	public static final String KEY3 = "Birthday";
	public static final String KEY4 = "ContactNum";
	public static final String KEY5 = "E-mail";	
	
	public static final String SEX_1 = "Male";
	public static final String SEX_2 = "Female";
	
	private HashMap<String, String> mData = new HashMap<String, String>();
	
	public People(String name, String sex, String birthday, String contactNum, String email) {
		mData.put(KEY1, name);
		mData.put(KEY2, sex);
		mData.put(KEY3, birthday);
		mData.put(KEY4, contactNum);
		mData.put(KEY5, email);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public HashMap<String, String> getData() {
		return (mData);
	}
};
