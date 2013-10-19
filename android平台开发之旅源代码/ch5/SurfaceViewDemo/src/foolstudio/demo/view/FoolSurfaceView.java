package foolstudio.demo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FoolSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	
	private Context mContext = null;	
	private Bitmap mBkgImage = null;
	//
	private int mScreenWidth = 0;
	private int mScreenHeight = 0;

	public FoolSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		
        getHolder().addCallback(this);
		
        Resources res = mContext.getResources();
        mBkgImage = BitmapFactory.decodeResource(res, R.drawable.android);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub		
		
		mScreenWidth = holder.getSurfaceFrame().width();
		mScreenHeight = holder.getSurfaceFrame().height();
		
		//绘制前锁定
		Canvas canvas = holder.lockCanvas();
		//开始绘制
		drawTitle(canvas);
		drawBkg(canvas);
		drawSprite(canvas);
		//解锁
		holder.unlockCanvasAndPost(canvas);
	}

	//绘制背景
	private void drawBkg(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(mBkgImage, 
				(mScreenWidth-mBkgImage.getWidth())/2, 
			(mScreenHeight-mBkgImage.getHeight())/2-mBkgImage.getHeight()/2, 
				null);		
	}

	//绘制精灵
	private void drawSprite(Canvas canvas) {
		// TODO Auto-generated method stub
		for(int r = 0; r <= 360; ++r) {
			Matrix matrix = new Matrix();
			matrix.postRotate(r);
			
			Bitmap tempBitmap = Bitmap.createBitmap(mBkgImage, 0, 0, 
					mBkgImage.getWidth(), mBkgImage.getHeight(), 
					matrix, false);
			
			canvas.drawBitmap(tempBitmap, 
					(mScreenWidth-tempBitmap.getWidth())/2, 
			(mScreenHeight-tempBitmap.getHeight())/2+mBkgImage.getHeight()/2, 
					null);
		}
	}

	//绘制抬头
	private void drawTitle(Canvas canvas) {
		// TODO Auto-generated method stub
		String title = "SurfaceView Demo";		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setTextSize(32.0f);
		int textWidth = getStringWidth(title, paint);
		
		canvas.drawText(title, (mScreenWidth-textWidth)/2, 48.0f, paint);		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}

	//获取字符串的绘制宽度
	private int getStringWidth(String str, Paint paint) {
		// TODO Auto-generated method stub
		float[] widths = new float[str.length()+1];
		int widthsCount = paint.getTextWidths(str, widths);
		int textWidth = 0;
		
		for(int i = 0; i < widthsCount; ++i) {
			textWidth += widths[i];
		}
		
		return (textWidth);
	}	
	
	//--------------------------------------------------------------------------	
};
