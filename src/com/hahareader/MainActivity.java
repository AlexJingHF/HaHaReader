package com.hahareader;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hahareader.bean.CategoryItem;
import com.hahareader.view.TitleHorizontalScrollView;

public class MainActivity extends FragmentActivity
{
	private final String TAG = MainActivity.class.getSimpleName();
	
	/**
	 * 自定义标题栏
	 */
	private TitleHorizontalScrollView mTitleHorizontalScrollView;
	private LinearLayout llContent;
	private LinearLayout llMore;
	private RelativeLayout rlColumn;
	private ImageView mButtonMoreColumns;
	private ViewPager mViewPager;
	
	/**
	 * 用户选择的分类
	 */
	private ArrayList<CategoryItem> userCategoryList = new ArrayList<CategoryItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}


}
