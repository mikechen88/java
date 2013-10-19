package foolstudio.demo.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.AdapterView.OnItemClickListener;

public class SlidingDrawerDemoAct extends Activity implements OnItemClickListener {
	
	private ImageView mImageView = null;	
	private SlidingDrawer mMainView = null;
	//
	private FoolItemAdapter mAdapter = null;
	private ListView mContentView = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mImageView = (ImageView)findViewById(R.id.PANEL_MAIN);        
        mMainView = (SlidingDrawer)findViewById(R.id.MAIN_VIEW);
        
        //初始化适配器
        mAdapter = new FoolItemAdapter(this, R.layout.item_view, R.id.LAB_TITLE);     
        
        //设置格子视图适配器
        mContentView = (ListView)findViewById(R.id.CONTENT_VIEW);
        mContentView.setAdapter(mAdapter);
        //设置选择侦听事件
        mContentView.setOnItemClickListener(this);       
    }
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
		if(view == null) {
			return;
		}
		
		ImageView imageView = (ImageView)view.findViewById(R.id.IMG_ICON);
		mImageView.setImageDrawable(imageView.getDrawable() );
		//TextView textView = (TextView)view.findViewById(R.id.LAB_TITLE);
		//Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();
		
		mMainView.close();
	}
};