package foolstudio.demo.view;

import java.util.HashMap;

public class People {
	public static final String KEY1 = "NAME";
	public static final String KEY2 = "ContactNum";
	public static final String KEY3 = "E-mail";	
	
	private HashMap<String, String> mData = new HashMap<String, String>();
	
	public People(String name, String contactNum) {
		mData.put(KEY1, name);
		mData.put(KEY2, contactNum);
	}
	
	public People(HashMap<String, String> data) {
		mData = data;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return("ÐÕÃû£º"+mData.get(KEY1).toString() + "|" +
			   "ºÅÂë£º"+mData.get(KEY2).toString() );
	}

	public HashMap<String, String> getData() {
		return (mData);
	}
};
