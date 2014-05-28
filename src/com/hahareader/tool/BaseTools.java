package com.hahareader.tool;

import android.app.Activity;
import android.util.DisplayMetrics;

public class BaseTools
{
	/**
	 * 描述：<br>
	 * @param activity
	 * @return 屏幕宽度
	 * @author alex
	 * @return int
	 * @data 2014年5月27日
	 */
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
}
