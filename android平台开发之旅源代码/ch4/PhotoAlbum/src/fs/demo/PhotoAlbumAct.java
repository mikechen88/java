package fs.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoAlbumAct extends Activity {
	
	private Integer mPhotoIds[] = {
			R.drawable.black,
			R.drawable.yellow,
			R.drawable.blue,
			R.drawable.green,
			R.drawable.red,
			R.drawable.silver
	};
	
	private ImageView mIV;
	private Integer mCurIndex = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
        
        //��ȡͼƬ�������
        mIV = (ImageView)findViewById(R.id.imageView);
        mIV.setImageResource(mPhotoIds[mCurIndex]);         
        //��ȡ��ť�������
        Button btnNext = (Button)findViewById(R.id.nextButton);
        Button btnPrev = (Button)findViewById(R.id.prevButton);
        //�����������ť�¼�
        btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				mCurIndex++;
				
				if(mCurIndex >= mPhotoIds.length) {
					mCurIndex = 0;
				}
				
				mIV.setImageResource(mPhotoIds[mCurIndex]);
			}        	
        });
        
        //��ǰ����¼�
        btnPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mCurIndex > 0) {
					mCurIndex--;
				}
				else {
					mCurIndex = (mPhotoIds.length-1);
				}
				
				mIV.setImageResource(mPhotoIds[mCurIndex]);
			}        	
        });        
    }
}