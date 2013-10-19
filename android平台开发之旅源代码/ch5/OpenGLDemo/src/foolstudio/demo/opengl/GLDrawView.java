package foolstudio.demo.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GLDrawView extends GLSurfaceView {
	
	private FoolRenderer mRenderer = null;

	public GLDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mRenderer = new FoolRenderer(context, this);
		this.setRenderer(mRenderer);		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub		
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub		
		super.onResume();
	}
};
