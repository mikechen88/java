package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GridViewDemoAct extends Activity implements OnClickListener {
	
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

	//ִ��ͼƬѡ��
	private void doSelect() {
		// TODO Auto-generated method stub
		Intent startNew = new Intent(this, FoolGridViewerAct.class);
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

	//��ȡѡȡ���
	private void doGetResult(Intent data) {
		// TODO Auto-generated method stub
		Bundle bundle = data.getExtras();
		int resId = bundle.getInt(FoolGridViewerAct.EXTRA_NAME);
		
		mImageView.setImageResource(resId);
	}
};