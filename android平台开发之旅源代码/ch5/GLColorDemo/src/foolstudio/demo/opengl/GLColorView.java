package foolstudio.demo.opengl;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GLColorView extends GLSurfaceView implements GLSurfaceView.Renderer {
	
	private Random mRandomizer = null;

	public GLColorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mRandomizer = new Random(System.currentTimeMillis() );
		this.setRenderer(this);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub		
		gl.glClearColor(mRandomizer.nextFloat(), //红色比重
						mRandomizer.nextFloat(), //绿色比重
						mRandomizer.nextFloat(), //蓝色比重
						mRandomizer.nextFloat() ); //透明比重
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		//初始化背景
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_FOG);
	}
};
