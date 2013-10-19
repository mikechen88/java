package com.szy.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author  coolszy
 * @blog    http://blog.csdn.net/coolszy
 */
public class ImageViewActivity extends Activity
{
	private ImageView imageView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		imageView = (ImageView)findViewById(R.id.imageView);
		Intent intent = getIntent();
		int imageId = intent.getIntExtra("id", R.drawable.p1);
		imageView.setImageResource(imageId);
	}
}
