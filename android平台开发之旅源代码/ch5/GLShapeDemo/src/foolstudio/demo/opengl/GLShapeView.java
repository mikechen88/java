package foolstudio.demo.opengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GLShapeView extends GLSurfaceView implements GLSurfaceView.Renderer {
	
    private Cube mCube = null;
    private float mAngle = 0.0f;

	public GLShapeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
        mCube = new Cube();		
        
		this.setRenderer(this);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -3.0f);
        gl.glRotatef(mAngle,        0, 1, 0);
        gl.glRotatef(mAngle*0.25f,  1, 0, 0);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        mCube.draw(gl);

        gl.glRotatef(mAngle*2.0f, 0, 1, 1);
        gl.glTranslatef(0.5f, 0.5f, 0.5f);

        mCube.draw(gl);

        mAngle += 1.2f;		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
        gl.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
        gl.glDisable(GL10.GL_DITHER);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        //gl.glClearColor(0,0,0,0);
        gl.glClearColor(1,1,1,1);
        
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);		
	}
};
