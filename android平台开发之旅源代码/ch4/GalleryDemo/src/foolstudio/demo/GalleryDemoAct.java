package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryDemoAct extends Activity implements OnClickListener {
	
	public static final int REQUEST_CODE = 1980;
	
	private ImageView mImageView = null;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        
        Button btnSelect = (Button)findViewById(R.id.BTN_SELECT);
        btnSelect.setOnClickListener(this);
        
        mImageView = (ImageView)findViewById(R.id.IMG_DEST);
        mImageView.setImageResource(-1);        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId() ) {
			case R.id.BTN_SELECT: {
				doSelect();
				break;
			}
		}
	}
	
	//执行选取行为
	private void doSelect() {
		// TODO Auto-generated method stub
		Intent startNew = new Intent(this, FoolGalleryViewerAct.class);
		//this.startActivity(startNew);
		this.startActivityForResult(startNew, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode) {
			case REQUEST_CODE: {
				doGetResult(data);
				break;
			}
		}
	}

	//获取执行结果
	private void doGetResult(Intent data) {
		// TODO Auto-generated method stub
		Bundle bundle = data.getExtras();
		if(bundle == null) {
			return;
		}
		
		int resId = bundle.getInt(FoolGalleryViewerAct.EXTRA_NAME);
		
		mImageView.setImageResource(resId);
	}	
};