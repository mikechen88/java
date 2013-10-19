package foolstudio.demo;

import android.widget.ImageView;

public class ImageMesh {
	private ImageView mImageView = null; //ͼƬ��ʾ���
	private int mImageResId = 0; //ͼƬ��ԴID
	
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
