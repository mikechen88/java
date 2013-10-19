package foolstudio.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.view.View;

public class CaptureView extends View {
	
	private Picture mPicture = null;

	public CaptureView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		Rect rect = canvas.getClipBounds();
		int width = rect.width();
		int height = rect.height();
		
		//非全屏显示缩略图（按３／４的比例）
		rect.left += width/8;
		rect.top += height/8;
		rect.right = width*7/8;
		rect.bottom = height*7/8;
		
		canvas.drawPicture(mPicture, rect);
		
		super.draw(canvas);
	}

	//设置数据源
	public void setSource(String filePath) {
		// TODO Auto-generated method stub
		
		try {
			mPicture = 
				Picture.createFromStream(new FileInputStream(new File(filePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
};
