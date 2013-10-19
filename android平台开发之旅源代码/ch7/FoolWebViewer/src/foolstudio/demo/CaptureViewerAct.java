package foolstudio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CaptureViewerAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//获取intent传来的文件存储路径信息
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		String filePath = bundle.getString("FILE_PATH");		
		
		//设置内容视图
		CaptureView view = new CaptureView(this);
		view.setSource(filePath);
		this.setContentView(view);
	}
};
