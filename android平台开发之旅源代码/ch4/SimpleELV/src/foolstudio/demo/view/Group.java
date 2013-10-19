package foolstudio.demo.view;

import java.util.HashMap;

public class Group {
	public static final String KEY1 = "NAME";
	
	private HashMap<String, String> mData = new HashMap<String, String>();
	
	public Group(String name) {
		mData.put(KEY1, name);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public HashMap<String, String> getData() {
		return (mData);
	}
}
