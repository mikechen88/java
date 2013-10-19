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
		//ͬѧ��
		Group group1 = new Group("ͬѧ");
		mGroups.add(group1.getData() );
		
		PeopleGroup pg1 = new PeopleGroup();
		//��Ա1
		People people1 = new People("����", People.SEX_2, "1985-6-30", "227", "jerry@gmail.com");		
		pg1.addPeople(people1);		
		//��Ա2
		People people2 = new People("����", People.SEX_1, "1984-9-30", "114", "andrew@gmail.com");		
		pg1.addPeople(people2);		
		mPeople.add(pg1.getData() );
		
		//������
		Group group2 = new Group("����");
		mGroups.add(group2.getData() );
		
		PeopleGroup pg2 = new PeopleGroup();
		//��Ա3
		People people3 = new People("����", People.SEX_1, "1986-1-14", "108", "ogg@dev2.co");		
		pg2.addPeople(people3);
		//��Ա4
		People people4 = new People("����", People.SEX_2, "1985-10-31", "534", "linda@sina.com.cn");
		pg2.addPeople(people4);	
		mPeople.add(pg2.getData() );
	}
};