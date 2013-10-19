package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.AdapterView.OnItemClickListener;

public class FoolGalleryViewerAct extends Activity implements OnItemClickListener {
	
	public static final String EXTRA_NAME = "SELECTED_ID";
	
	private FoolImageAdapter mAdapter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_view);
        
        //初始化适配器
        mAdapter = new FoolImageAdapter(this);
        
        //设置格子视图适配器
        Gallery gallery = (Gallery)findViewById(R.id.GALLERY_MAIN);
        gallery.setAdapter(mAdapter);
        //设置选择侦听事件
        gallery.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
        //返回值
        Intent data = new Intent();        

        int resId = mAdapter.getResId(pos);        
        data.putExtra(EXTRA_NAME, resId);
        
        //调试
        Log.d(this.getClass().getName(), "Image resource id " + resId);
        
        this.setResult(GalleryDemoAct.REQUEST_CODE, data);	
        this.finish();		
	}
};
