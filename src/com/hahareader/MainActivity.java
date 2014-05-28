package com.hahareader;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hahareader.adapter.CategoryFragmentPagerAdapter;
import com.hahareader.application.MyApplication;
import com.hahareader.bean.CategoryItem;
import com.hahareader.bean.CategoryManager;
import com.hahareader.fragment.CategoryFragment;
import com.hahareader.tool.BaseTools;
import com.hahareader.tool.Mlog;
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
	private ImageView mLeftShape;
	private ImageView mRightShape;
	/**
	 * 用户选择的分类
	 */
	private ArrayList<CategoryItem> userCategoryList = new ArrayList<CategoryItem>();
	/**
	 * 当前选中的栏目
	 */
	private int columnSelectIndex = 0;

	/**
	 * 屏幕宽度
	 */
	private int mScreenWidth = 0;

	/**
	 * item宽度
	 */
	private int mItemWidth = 0;

	/**
	 * 滑动菜单
	 */
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

//	protected SlidingMenu mSlidingMenu;

	/**
	 * 头部loading
	 */
	private ProgressBar mTopProgress;

	/**
	 * 头部刷新按钮
	 */
	private ImageView mTopRefresh;

	/**
	 * 头部左侧菜单按钮
	 */
	private ImageView mTopHead;

	/**
	 * 头部右侧菜单按钮
	 */
	private ImageView mTopMore;

	/**
	 * 请求code
	 */
	private final static int CHANNELREQUEST = 1;

	/**
	 * 调整后返回得code
	 */
	private final static int CHANNELRESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = mScreenWidth / 4;
		initView();
		initSlidingMenu();
	}

	private void initView()
	{
		/** head */
		mTopHead = (ImageView) findViewById(R.id.top_head_menu);
		mTopMore = (ImageView) findViewById(R.id.top_head_login);
		// mTopProgress
		// mTopRefresh
		/** title */
		mTitleHorizontalScrollView = (TitleHorizontalScrollView) findViewById(R.id.mTitleHorizontalScrollView);
		llContent = (LinearLayout) findViewById(R.id.ll_content);
		llMore = (LinearLayout) findViewById(R.id.ll_more);
		rlColumn = (RelativeLayout) findViewById(R.id.rl_column);
		mButtonMoreColumns = (ImageView) findViewById(R.id.button_more_columns);
		mLeftShape = (ImageView) findViewById(R.id.left_shape);
		mRightShape = (ImageView) findViewById(R.id.right_shape);
		/** pager */
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);

		mButtonMoreColumns.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent_category = new Intent(getApplicationContext(),
						CategoryActivity.class);
				startActivityForResult(intent_category, CHANNELREQUEST);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			}
		});

		mTopHead.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
//				if (mSlidingMenu.isMenuShowing())
//				{
//					mSlidingMenu.showContent();
//				} else
//				{
//					mSlidingMenu.showMenu();
//				}
			}
		});

		mTopMore.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
//				if (mSlidingMenu.isSecondaryMenuShowing())
//				{
//					mSlidingMenu.showContent();
//				} else
//				{
//					mSlidingMenu.showSecondaryMenu();
//				}
			}
		});
		setChangeView();
	}

	private void setChangeView()
	{
		initColumnData();
		initTabColumn();
		initFragment();
	}

	private void initColumnData()
	{
		userCategoryList = (ArrayList<CategoryItem>) CategoryManager
				.getManager(MyApplication.getApp().getSqlHelper())
				.getUserCategory();
	}

	private void initTabColumn()
	{
		llContent.removeAllViews();
		int count = userCategoryList.size();
		mTitleHorizontalScrollView.setParam(this, mScreenWidth, llContent,
				llMore, rlColumn, mLeftShape, mRightShape);
		for (int i = 0; i < count; i++)
		{
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					mItemWidth, LayoutParams.MATCH_PARENT);
			params.topMargin = 10;
			params.bottomMargin = 10;
			TextView tv = new TextView(this);
			tv.setTextAppearance(this,
					R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
			tv.setBackgroundResource(R.drawable.selector_title);
			tv.setGravity(Gravity.CENTER);
			tv.setPadding(5, 5, 5, 5);
			tv.setId(i);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(getResources().getColor(R.color.black));
			if (columnSelectIndex == i)
			{
				tv.setSelected(true);
			}
			tv.setText(userCategoryList.get(i).getName());
			tv.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v)
				{
					//
					for (int j = 0; j < llContent.getChildCount(); j++)
					{
						View view = llContent.getChildAt(j);
						if (view != v)
						{
							view.setSelected(false);
						} else
						{
							view.setSelected(true);
							// mViewPager.setCurrentItem(i);
						}
					}
					Toast.makeText(getApplicationContext(),
							userCategoryList.get(v.getId()).getName(),
							Toast.LENGTH_LONG).show();
				}
			});
			llContent.addView(tv, i, params);
		}

	}

	private void initFragment()
	{
		fragments.clear();
		int count = userCategoryList.size();
		for (int i = 0; i < count; i++)
		{
			Bundle data = new Bundle();
			data.putString("text", userCategoryList.get(i).getName());
			data.putInt("id", userCategoryList.get(i).getId());
			CategoryFragment fragment = new CategoryFragment();
			fragment.setArguments(data);
			fragments.add(fragment);
		}
		CategoryFragmentPagerAdapter mAdapter = new CategoryFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(pageChangeListener);
	}

	public OnPageChangeListener pageChangeListener = new OnPageChangeListener()
	{
		@Override
		public void onPageSelected(int arg0)
		{
			Mlog.d(TAG, "onPageSelected position[" + arg0 + "]");
			mViewPager.setCurrentItem(arg0);
			selectTab(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int position)
		{
		}

	};

	private void selectTab(int position)
	{
		columnSelectIndex = position;
		for (int i = 0; i < llContent.getChildCount(); i++)
		{
			View checkView = llContent.getChildAt(position);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mTitleHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		// 判断是否选中
		for (int j = 0; j < llContent.getChildCount(); j++)
		{
			View checkView = llContent.getChildAt(j);
			boolean ischeck;
			if (j == position)
			{
				ischeck = true;
			} else
			{
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}

	private void initSlidingMenu()
	{

	}

}
