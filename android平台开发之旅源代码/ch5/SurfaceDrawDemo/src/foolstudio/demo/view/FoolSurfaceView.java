package foolstudio.demo.view;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FoolSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final int[] DRAWABLE_IDs = {
		R.drawable.bqt, R.drawable.dx, R.drawable.hd,
		R.drawable.hg, R.drawable.hm, R.drawable.xm
	};
	
	private SurfaceViewThread mThread = null;

	public FoolSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        
        mThread = new SurfaceViewThread(context, holder);
	}
	
	//��ȡ�����߳�
	public SurfaceViewThread getThread() {
		return this.mThread;
	};	

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//�ڱ��洴����ͬʱ���������߳�
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mThread.setRunning(false);
		try {
			mThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class SurfaceViewThread extends Thread {
		private Context mContext = null;
		private SurfaceHolder mHolder = null;
		private boolean mIsRunning = false;
		private Bitmap mSprite = null;
		private Random mRandomizer = null;
		//
		private int mRotate = 0;
		private Matrix mMatrix = null;

		public SurfaceViewThread(Context context, SurfaceHolder holder) {
			super();
			// TODO Auto-generated constructor stub
			this.mContext = context;
			this.mHolder = holder;
			
			//������ػ�������
			mRandomizer = new Random(System.currentTimeMillis() );
			
			mSprite = BitmapFactory.decodeResource(mContext.getResources(),
					DRAWABLE_IDs[2]);
			mMatrix = new Matrix();
		}
		
		public void setRunning(boolean isRunning) {
			// TODO Auto-generated method stub
			this.mIsRunning = isRunning;
		}	

		@Override
		public void run() {
			// TODO Auto-generated method stub
            while (mIsRunning) {
                Canvas canvas = null;
                try { //����ǰ����
                	canvas = mHolder.lockCanvas(null);
                    synchronized (mHolder) {
                        //doDraw(canvas);
                    	doRotate(canvas);
                    }
                } finally {
                    if (canvas != null) { //������Ϻ����
                    	mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }			
			
			super.run();
		}

		//��������(����ת)
		private void doDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			clearBkg(canvas);
			
			int index = getRandomIndex();
			Log.d("SurfaceViewThread", "Current picture index is " + index);
			
			Bitmap bmpRandom = 
					BitmapFactory.decodeResource(mContext.getResources(),
												 DRAWABLE_IDs[index]);			
			canvas.drawBitmap(bmpRandom, 
					(canvas.getWidth()-bmpRandom.getWidth())/2, 
					(canvas.getHeight()-bmpRandom.getHeight())/2, null);
			bmpRandom = null;
			
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ִ�л�����ת
		private void doRotate(Canvas canvas) {
			// TODO Auto-generated method stub
			clearBkg(canvas);
			
			mMatrix.setRotate(getRotate() );
			
			Bitmap bmpRotate = Bitmap.createBitmap(mSprite, 0, 0, 
					mSprite.getWidth(), mSprite.getHeight(), 
					mMatrix, true);
			canvas.drawBitmap(bmpRotate, 
					(canvas.getWidth()-bmpRotate.getWidth())/2, 
					(canvas.getHeight()-bmpRotate.getHeight())/2, null);
			bmpRotate = null;
			
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		//��ȡ��ת�Ƕ�
		private float getRotate() {
			// TODO Auto-generated method stub
			int rotate = mRotate + 5;
			
			if(rotate >= 360) {
				rotate -= 360;
			}
			
			mRotate = rotate;
			
			return(rotate);
		}

		//��ձ���
		private void clearBkg(Canvas c) {
			// TODO Auto-generated method stub
			c.drawColor(Color.BLACK, Mode.CLEAR);			
		}

		//��ȡ������
		public int getRandomIndex() {
			// TODO Auto-generated method stub
			return mRandomizer.nextInt(DRAWABLE_IDs.length-1);
		}		
	}
	//--------------------------------------------------------------------------
};
