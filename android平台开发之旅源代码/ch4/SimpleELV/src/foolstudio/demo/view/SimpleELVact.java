package foolstudio.demo.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class SimpleELVact extends ExpandableListActivity {
	
	private ArrayList<HashMap<String,String>> mGroups = 
									new ArrayList<HashMap<String,String>>();
	private ArrayList<ArrayList<HashMap<String, String>>> mPeople = 
						new ArrayList<ArrayList<HashMap<String, String>>>();
	private final String mDetailKeys[] = {
		People.KEY1,
		People.KEY2		
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        initChildren();
        
        ExpandableListAdapter adapter =  new SimpleExpandableListAdapter(this,
        	mGroups,
        	android.R.layout.simple_expandable_list_item_1,
        	new String[] {Group.KEY1},
        	new int[] { android.R.id.text1 },
        	mPeople,
        	android.R.layout.simple_expandable_list_item_2,
        	mDetailKeys,
        	new int[] { android.R.id.text1, android.R.id.text2 });
		this.setListAdapter(adapter);
    }

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPos, int childPos, long id) {
		// TODO Auto-generated method stub
		People people = new People(mPeople.get(groupPos).get(childPos) );
		Toast.makeText(this, people.toString(), Toast.LENGTH_LONG).show();
		
		return super.onChildClick(parent, v, groupPos, childPos, id);
	}

	private void initChildren() {
		// TODO Auto-generated method stub
		//同学组
		Group group1 = new Group("同学");
		mGroups.add(group1.getData() );
		
		PeopleGroup pg1 = new PeopleGroup();
		//人员1
		People people1 = new People("张三", "139-1234-5678");		
		pg1.addPeople(people1);		
		//人员2
		People people2 = new People("李四", "135-6789-5432");		
		pg1.addPeople(people2);		
		mPeople.add(pg1.getData() );
		
		//朋友组
		Group group2 = new Group("朋友");
		mGroups.add(group2.getData() );
		
		PeopleGroup pg2 = new PeopleGroup();
		//人员3
		People people3 = new People("王五", "134-2345-7890");		
		pg2.addPeople(people3);
		//人员4
		People people4 = new People("赵六", "135-3456-6789");
		pg2.addPeople(people4);	
		mPeople.add(pg2.getData() );
	}
};