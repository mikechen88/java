package foolstudio.demo.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class FoolRenderer implements GLSurfaceView.Renderer {
	
	private GLDrawView mView = null;
	//背景色
	//private float mBkgColor = 0.0f;
	//纹理资源
	private Bitmap mSprite = null;
	private int mTexture = 0;	
	//
	private static final float lightAmbient[] = new float[] { 
		0.2f, 0.3f, 0.6f, 1.0f 
	};
	private static final float lightDiffuse[] = new float[] { 
		0.2f, 0.3f, 0.6f, 1.0f 
		};
	private static final float matAmbient[] = new float[] { 
		0.6f, 0.6f, 0.6f, 1.0f 
	};
	private static final float matDiffuse[] = new float[] { 
		0.6f, 0.6f, 0.6f, 1.0f 
	};

	private static final float fogColor[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	private static final float[] pos = new float[] {0,0,3,0};
	
	private static final float box[] = new float[] {
		//前
		-0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f,  0.5f,
		-0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f,  0.5f,
		//后
		-0.5f, -0.5f, -0.5f,
		-0.5f,  0.5f, -0.5f,
		 0.5f, -0.5f, -0.5f,
		 0.5f,  0.5f, -0.5f,
		//左
		-0.5f, -0.5f,  0.5f,
		-0.5f,  0.5f,  0.5f,
		-0.5f, -0.5f, -0.5f,
		-0.5f,  0.5f, -0.5f,
		//右
		 0.5f, -0.5f, -0.5f,
		 0.5f,  0.5f, -0.5f,
		 0.5f, -0.5f,  0.5f,
		 0.5f,  0.5f,  0.5f,
		//上
		-0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f,  0.5f,
		 -0.5f,  0.5f, -0.5f,
		 0.5f,  0.5f, -0.5f,
		//下
		-0.5f, -0.5f,  0.5f,
		-0.5f, -0.5f, -0.5f,
		 0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f, -0.5f,
	};
	
	private static final float texCoords[] = new float[] {
		//前
		 0.0f, 0.0f,
		 1.0f, 0.0f,
		 0.0f, 1.0f,
		 1.0f, 1.0f,
		//后
		 1.0f, 0.0f,
		 1.0f, 1.0f,
		 0.0f, 0.0f,
		 0.0f, 1.0f,
		//左
		 1.0f, 0.0f,
		 1.0f, 1.0f,
		 0.0f, 0.0f,
		 0.0f, 1.0f,
		//右
		 1.0f, 0.0f,
		 1.0f, 1.0f,
		 0.0f, 0.0f,
		 0.0f, 1.0f,
		//上
		 0.0f, 0.0f,
		 1.0f, 0.0f,
		 0.0f, 1.0f,
		 1.0f, 1.0f,
		//下
		 1.0f, 0.0f,
		 1.0f, 1.0f,
		 0.0f, 0.0f,
		 0.0f, 1.0f
	};

	private FloatBuffer cubeBuff = null;
	private FloatBuffer texBuff = null;
	
	public FoolRenderer(Context context, GLDrawView view) {
		this.mView = view;		
		
		mSprite = BitmapFactory.decodeResource(context.getResources(), 
											   R.drawable.butterfly);		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub		
		//mBkgColor += 0.01f;		
        //gl.glClearColor( 0.0f, mBkgColor, 0.0f, 1.0f );
        //gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);	
		
		int width = mView.getWidth();
		int height = mView.getHeight();
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0,0,width,height);
		GLU.gluPerspective(gl, 45.0f, ((float)width)/height, 1f, 100f);
			
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		
		gl.glRotatef(10.0f, 1, 0, 0);
		gl.glRotatef(40.0f, 0, 1, 0);
	
		gl.glColor4f(1.0f, 1, 1, 1.0f);
		gl.glNormal3f(0,0,1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glNormal3f(0,0,-1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
	
		gl.glColor4f(1, 1.0f, 1, 1.0f);
		gl.glNormal3f(-1,0,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		gl.glNormal3f(1,0,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
		
		gl.glColor4f(1, 1, 1.0f, 1.0f);
		gl.glNormal3f(0,1,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		gl.glNormal3f(0,-1,0);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
	}   

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub	
		//gl.glClearColor( 0.0f, 0.0f, 0.0f, 1.0f );
		
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, 
						GL10.GL_AMBIENT, matAmbient, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, 
						GL10.GL_DIFFUSE, matDiffuse, 0);
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse,	0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, pos, 0);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_CULL_FACE);
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepthf(1.0f);
		
		cubeBuff = makeFloatBuffer(box);
		texBuff = makeFloatBuffer(texCoords);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuff);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		ByteBuffer bb = ByteBuffer.allocateDirect(
				mSprite.getHeight()*mSprite.getWidth()*4);
		bb.order(ByteOrder.nativeOrder());
		IntBuffer ib = bb.asIntBuffer();
		
		for (int y=0;y<mSprite.getHeight();y++) {
			for (int x=0;x<mSprite.getWidth();x++) {
				ib.put(mSprite.getPixel(x,y));
			}
		}
		
		ib.position(0);
		bb.position(0);
		
		int[] tmp_tex = new int[1];
		
		gl.glGenTextures(1, tmp_tex, 0);
		this.mTexture = tmp_tex[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, this.mTexture);
		gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, 
						mSprite.getWidth(), mSprite.getHeight(), 
						0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, bb);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
						   GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
						   GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		gl.glFogf(GL10.GL_FOG_MODE, GL10.GL_EXP);;
		gl.glFogfv(GL10.GL_FOG_COLOR, fogColor, 0);
		gl.glFogf(GL10.GL_FOG_DENSITY, 0.75f);
		gl.glHint(GL10.GL_FOG_HINT, GL10.GL_DONT_CARE);
		gl.glFogf(GL10.GL_FOG_START, 1.0f);
		gl.glFogf(GL10.GL_FOG_END, 5.0f);
		gl.glEnable(GL10.GL_FOG);
	}
	
	protected FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}
};
