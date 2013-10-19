package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class FoolGridViewerAct extends Activity implements OnItemClickListener {
	
	public static final String EXTRA_NAME = "SELECTED_ID";
	
	private FoolImageAdapter mAdapter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer_view);
        
        //��ʼ��������
        mAdapter = new FoolImageAdapter(this);
        
        //���ø�����ͼ������
        GridView gridView = (GridView)findViewById(R.id.VIEW_GRID);
        gridView.setAdapter(mAdapter);
        //����ѡ�������¼�
        gridView.setOnItemClickListener(this);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
        //����ֵ
        Intent data = new Intent();        

        int resId = mAdapter.getResId(pos);        
        data.putExtra(EXTRA_NAME, resId);
        
        //����
        Log.d(this.getClass().getName(), "Image resource id " + resId);
        
        this.setResult(GridViewDemoAct.REQUEST_CODE, data);	
        this.finish();		
	}
};
