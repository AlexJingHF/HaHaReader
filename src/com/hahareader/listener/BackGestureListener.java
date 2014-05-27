package com.hahareader.listener;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import com.hahareader.base.BaseActivity;
import com.hahareader.tool.Mlog;

public class BackGestureListener implements OnGestureListener
{
	private final String TAG = BackGestureListener.class.getSimpleName();
	
	private BaseActivity activity;
	
	public BackGestureListener(BaseActivity activity)
	{
		this.activity = activity;
	}
	@Override
	public boolean onDown(MotionEvent e)
	{
		Mlog.d(TAG, "onDown");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
		Mlog.d(TAG, "onShowPress");
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		Mlog.d(TAG, "onSingleTapUp");
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		Mlog.d(TAG, "onScroll");
		if ((e2.getX() - e1.getX()) > 100 && Math.abs(e1.getY() - e2.getY()) < 60)
		{
			activity.onBackPressed();
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
		Mlog.d(TAG, "onLongPress");
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY)
	{
		Mlog.d(TAG, "onFling");
		return false;
	}

}
