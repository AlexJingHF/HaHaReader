package com.hahareader.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.hahareader.listener.BackGestureListener;
import com.hahareader.tool.Mlog;

public class BaseActivity extends Activity
{
	private final String TAG = BaseActivity.class.getSimpleName();

	GestureDetector mGestureDetector;

	private boolean mBackGestureAvailed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initGestureDetector();
	}

	private void initGestureDetector()
	{
		if (mGestureDetector == null)
		{
			mGestureDetector = new GestureDetector(getApplicationContext(),
					new BackGestureListener(this));
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		if (ismBackGestureAvailed())
		{
			Mlog.d(TAG, "dispatchTouchEvent");
			return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	public boolean ismBackGestureAvailed()
	{
		return mBackGestureAvailed;
	}

	public void setmBackGestureAvailed(boolean mBackGestureAvailed)
	{
		this.mBackGestureAvailed = mBackGestureAvailed;
	}
	
	public void doBack(View view){
		Mlog.d(TAG, "doBack");
		onBackPressed();
	}
	
	
}
