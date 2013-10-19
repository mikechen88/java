package foolstudio.demo.view;

import java.util.ArrayList;
import java.util.HashMap;

public class PeopleGroup {
	private ArrayList<HashMap<String, String>> mData = new ArrayList<HashMap<String, String>>();
	
	public PeopleGroup() {
	}
	
	public void addPeople(People people) {
		mData.add(people.getData() );
	}
	
	public ArrayList<HashMap<String, String>> getData() {
		return(mData);
	}
};
