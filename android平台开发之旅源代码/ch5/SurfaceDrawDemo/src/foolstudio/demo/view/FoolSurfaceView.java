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
	
	//获取绘制线程
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
		//在表面创建的同时启动绘制线程
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
			
			//随机加载绘制内容
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
                try { //绘制前锁定
                	canvas = mHolder.lockCanvas(null);
                    synchronized (mHolder) {
                        //doDraw(canvas);
                    	doRotate(canvas);
                    }
                } finally {
                    if (canvas != null) { //绘制完毕后解锁
                    	mHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }			
			
			super.run();
		}

		//绘制内容(不旋转)
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
		
		//执行画面旋转
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
		
		//获取旋转角度
		private float getRotate() {
			// TODO Auto-generated method stub
			int rotate = mRotate + 5;
			
			if(rotate >= 360) {
				rotate -= 360;
			}
			
			mRotate = rotate;
			
			return(rotate);
		}

		//清空背景
		private void clearBkg(Canvas c) {
			// TODO Auto-generated method stub
			c.drawColor(Color.BLACK, Mode.CLEAR);			
		}

		//获取随机编号
		public int getRandomIndex() {
			// TODO Auto-generated method stub
			return mRandomizer.nextInt(DRAWABLE_IDs.length-1);
		}		
	}
	//--------------------------------------------------------------------------
};
