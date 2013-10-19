package com.example.animations;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		makeAnimate(R.id.TextView_Title, R.anim.fade_in, null);
		makeAnimate(R.id.TextView_SubTitle, R.anim.fade_in_2, null);
		makeAnimate(R.id.ImageView_Star, R.anim.rotate, new FinishAnimationListener());
	}
	
	public void makeAnimate(int fieldId, int animId, AnimationListener listener)
	{
		View view = findViewById(fieldId);
		Animation anim = AnimationUtils.loadAnimation(this, animId);
		view.startAnimation(anim);
		
		if(listener == null) return;
		anim.setAnimationListener(listener);
	}
	
	private class FinishAnimationListener implements AnimationListener {

		public void onAnimationEnd(Animation animation) 
		{
			startActivity(new Intent(SplashActivity.this, HomeActivity.class));
			SplashActivity.this.finish();
		}

		public void onAnimationRepeat(Animation animation) {}

		public void onAnimationStart(Animation animation) {}
		
	}
	
	public void onPause()
	{
		super.onPause();
		findViewById(R.id.TextView_Title).clearAnimation();
		findViewById(R.id.TextView_SubTitle).clearAnimation();
		findViewById(R.id.ImageView_Star).clearAnimation();
	}
}
















