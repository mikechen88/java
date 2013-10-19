package foolstudio.demo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class FoolCustomView extends View {
	
	private int mScreenWidth = 0;
	private int mScreenHeight = 0;	
	private Bitmap mBkgImage = null;
	
	public FoolCustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		WindowManager service = 
			(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		mScreenHeight = service.getDefaultDisplay().getHeight();
		mScreenWidth = service.getDefaultDisplay().getWidth();
		
		//载入背景图片
		mBkgImage = BitmapFactory.decodeResource(context.getResources(), 
												 R.drawable.hulu);
	}	
	/*
	public FoolCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager service = 
			(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		mScreenHeight = service.getDefaultDisplay().getHeight();
		mScreenWidth = service.getDefaultDisplay().getWidth();
		
		//载入背景图片
		mBkgImage = BitmapFactory.decodeResource(context.getResources(), 
												 R.drawable.hulu);
	}
	*/

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		drawTitle(canvas);
		drawSprite(canvas);
		
		super.draw(canvas);
	}
	
	//绘制标题
	private void drawTitle(Canvas canvas) {
		// TODO Auto-generated method stub
		String title = "ViewDraw Demo";		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		paint.setTextSize(32.0f);
		int textWidth = getStringWidth(title, paint);
		
		canvas.drawText(title, (mScreenWidth-textWidth)/2, 48.0f, paint);		
	}

	//获取字符串的宽度
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
					(mScreenHeight-tempBitmap.getHeight())/2, 
					null);
		}
	}	
	//--------------------------------------------------------------------------
};
