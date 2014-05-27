package com.hahareader;

import com.hahareader.tool.Mlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class Welcome extends Activity
{
	private final String TAG = Welcome.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		View view = findViewById(R.id.welcome_text);
		Animation animation = new AlphaAnimation(0.3f, 1f);
		animation.setDuration(2000);
		view.setAnimation(animation);
		animation.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				Mlog.d(TAG, "onAnimationStart");
			}
			
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				Mlog.d(TAG, "onAnimationRepeat");				
			}
			
			@Override
			public void onAnimationEnd(Animation animation)
			{
				Mlog.d(TAG, "onAnimationEnd");	
				toMainActivity();
				finish();
			}
		});
	}
	
	private void toMainActivity(){
		startActivity(new Intent(this, MainActivity.class));
	}
}
