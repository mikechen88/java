package foolstudio.demo.opengl;

import android.app.Activity;
import android.os.Bundle;

public class OpenGLDrawAct extends Activity {
	
	private GLDrawView mView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draw_view);
		
		mView = (GLDrawView)findViewById(R.id.MAIN_VIEW);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mView.onPause();
		
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mView.onResume();
		
		super.onResume();
	}
};
