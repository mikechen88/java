package foolstudio.demo;

import android.widget.ImageView;

public class ImageMesh {
	private ImageView mImageView = null; //图片显示组件
	private int mImageResId = 0; //图片资源ID
	
	public ImageMesh() {
	}
	
	public void setImageView(ImageView imageView) {
		mImageView = imageView;
	}
	
	public ImageView getImageView() {
		return (mImageView);
	}
	
	public void setImageResId(int imageResId) {
		mImageResId = imageResId;
		mImageView.setImageResource(imageResId);
	}
	
	public int getImageResId() {
		return (mImageResId);
	}
};
