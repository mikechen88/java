package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CaptureViewerAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//��ȡintent�������ļ��洢·����Ϣ
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		String filePath = bundle.getString("FILE_PATH");		
		
		//����������ͼ
		CaptureView view = new CaptureView(this);
		view.setSource(filePath);
		this.setContentView(view);
	}
};
