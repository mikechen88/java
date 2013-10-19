package com.example.a72;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;



public class SecondActivity extends BaseActivity {
SharedPreferences settings;
	
	ImageView avatarButton;
	TextView textView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		 textView = (TextView)findViewById(R.id.FirstNameP2);
		avatarButton=(ImageView)findViewById(R.id.ImageView);
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
        initTextView(FIRST_NAME);
        
        
    	initImageView (AVATAR);
    	

		makeAnimate(R.id.ImageView, R.anim.fade_in_2, null);
		makeAnimate(R.id.FirstNameP2, R.anim.rotate, new FinishAnimationListener());
     
    }
	
	
	
	public void initImageView( String key ){
		
		if ( !settings.contains(key))return;
	       
    	String uriString = settings.getString(key, "");
		if (!uriString.equals("")) {
			Uri imageUri = Uri.parse(uriString);
			avatarButton.setImageURI(imageUri);

		}
    	
	}
	
	private void initTextView(String key)
    {
    	if(settings.contains(key))
        {
        	String prefText = settings.getString(key, "");
        	textView.setText("Welcome "+prefText);
        }
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
			startActivity(new Intent(SecondActivity.this, MainTabActivity.class));
			SecondActivity.this.finish();
		}

		public void onAnimationRepeat(Animation animation) {}

		public void onAnimationStart(Animation animation) {}
		
	}
	
	public void onPause()
	{
		super.onPause();
		
		findViewById(R.id.FirstNameP2).clearAnimation();
		findViewById(R.id.ImageView).clearAnimation();
	}
}

