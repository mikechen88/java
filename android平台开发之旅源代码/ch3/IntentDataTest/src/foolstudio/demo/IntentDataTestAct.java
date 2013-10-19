package foolstudio.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IntentDataTestAct extends Activity implements OnClickListener {
	public static final int REQ_CODE = 2012;
	public static final String EXTRAS_KEY = "EXTRAS_DATA";
	private ArrayList<Kid> mKids = new ArrayList<Kid>();
	
	/*Andoid平台禁止访问Activity的构造函数，否则抛出异常
	public static IntentDataTestAct mInstance = new IntentDataTestAct();
	
	private IntentDataTestAct() {
	}
	
	public static IntentDataTestAct getInstance() {
		return (mInstance);
	}
	*/
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        Button btnStart = (Button)findViewById(R.id.BTN_START);
        btnStart.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_START: {
				doStart();
				break;
			}
		}
	}
	
	//启动新的活动
	private void doStart() {
		Intent startNew = new Intent(this, DataViewerAct.class);		
		ArrayList<Kid> kids = initArrayList();	
		startNew.putParcelableArrayListExtra(EXTRAS_KEY, kids);		
		//this.startActivity(startNew);
		this.startActivityForResult(startNew, REQ_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == REQ_CODE) {
			Bundle bundle = data.getExtras();
			String msg = bundle.getString("Msg");
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}	
	
	//初始化列表
	private ArrayList<Kid> initArrayList() {
		//ArrayList<Kid> kids = new ArrayList<Kid>();
		
		addKid(mKids, "Jim", Kid.SEX_FEMALE, "2007-5-31");
		addKid(mKids, "Jane", Kid.SEX_MALE, "2007-10-31");
		addKid(mKids, "Bob", Kid.SEX_FEMALE, "2007-6-1");		
		
		return mKids;
	}
	
	//获取列表
	public ArrayList<Kid> getArrayList() {
		return (mKids);
	}
	
	//添加小孩记录
	private void addKid(ArrayList<Kid> kids, 
						String name, int sex_flag, String birthday) {
		Kid kid = new Kid(name, sex_flag, birthday);
		kids.add(kid);
	}
};