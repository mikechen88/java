package foolstudio.demo.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

public class ExpandableListViewDemoAct extends ExpandableListActivity {
	
	private ArrayList<HashMap<String,String>> mGroups = new ArrayList<HashMap<String,String>>();
	private ArrayList<ArrayList<HashMap<String, String>>> mPeople = new ArrayList<ArrayList<HashMap<String, String>>>();
	private final int mDetailViewsId[] = {
		R.id.TXT_CHILD_ITEM1,R.id.TXT_CHILD_ITEM2,R.id.TXT_CHILD_ITEM3,R.id.TXT_CHILD_ITEM4,R.id.TXT_CHILD_ITEM5
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        initChildren();
        
        ExpandableListAdapter adapter =  new SimpleExpandableListAdapter(this,
        	mGroups,
        	R.layout.group_view,
        	new String[] {Group.KEY1},
        	new int[] { R.id.TXT_GROUP_ITEM }, 
        	mPeople,
        	R.layout.child_view,
        	new String[] {People.KEY1,People.KEY2,People.KEY3,People.KEY4,People.KEY5},
        	mDetailViewsId);
		this.setListAdapter(adapter);
    }

	private void initChildren() {
		// TODO Auto-generated method stub
		//同学组
		Group group1 = new Group("同学");
		mGroups.add(group1.getData() );
		
		PeopleGroup pg1 = new PeopleGroup();
		//人员1
		People people1 = new People("张三", People.SEX_2, "1985-6-30", "227", "jerry@gmail.com");		
		pg1.addPeople(people1);		
		//人员2
		People people2 = new People("李四", People.SEX_1, "1984-9-30", "114", "andrew@gmail.com");		
		pg1.addPeople(people2);		
		mPeople.add(pg1.getData() );
		
		//朋友组
		Group group2 = new Group("朋友");
		mGroups.add(group2.getData() );
		
		PeopleGroup pg2 = new PeopleGroup();
		//人员3
		People people3 = new People("王五", People.SEX_1, "1986-1-14", "108", "ogg@dev2.co");		
		pg2.addPeople(people3);
		//人员4
		People people4 = new People("赵六", People.SEX_2, "1985-10-31", "534", "linda@sina.com.cn");
		pg2.addPeople(people4);	
		mPeople.add(pg2.getData() );
	}
};