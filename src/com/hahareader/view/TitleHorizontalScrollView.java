package com.hahareader.view;

import com.hahareader.tool.Mlog;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/**
 * @author alex
 *
 */
public class TitleHorizontalScrollView extends HorizontalScrollView
{
	private final String TAG = TitleHorizontalScrollView.class.getSimpleName();
	/** 父Activity */
	private Activity activity;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** 左侧阴影图片 */
	private ImageView leftShapeImage;
	/** 右侧阴影图片 */
	private ImageView rightShapeImage;
	/** 传入的内容布局 */
	private View llContent;
	/** 传入的加入更多栏目选择布局 */
	private View llMore;
	/** 传入的拖动布局 */
	private View rlColumn;

	public TitleHorizontalScrollView(Context context)
	{
		super(context);
	}

	public TitleHorizontalScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public TitleHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle)
	{
		super(context, attrs, defStyle);
	}

	/**
	 * 描述：传入参数<br>
	 * 
	 * @param activity
	 * @param screenWidth
	 * @param content
	 * @param more
	 * @param column
	 * @param leftShape
	 * @param rightShape
	 * @author alex
	 * @return void
	 * @data 2014年5月26日
	 */
	public void setParam(Activity activity, int screenWidth, View content,
			View more, View column, ImageView leftShape, ImageView rightShape)
	{
		this.activity = activity;
		this.mScreenWidth = screenWidth;
		this.llContent = content;
		this.llMore = more;
		this.rlColumn = column;
		this.leftShapeImage = leftShape;
		this.rightShapeImage = rightShape;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		Mlog.d(TAG, "onScrollChanged:" + "l=" + l + " t=" + t + " oldl=" + oldl
				+ " oldt=" + oldt);
		super.onScrollChanged(l, t, oldl, oldt);
		shapeState();
		if (isOk())
		{
			if (llContent.getWidth() <= mScreenWidth)
			{
				leftShapeImage.setVisibility(View.GONE);
				rightShapeImage.setVisibility(View.GONE);
			}
		}else {
			return;
		}
		
		if (l == 0)
		{
			leftShapeImage.setVisibility(View.GONE);
			rightShapeImage.setVisibility(View.VISIBLE);
			return;
		}
		if (llContent.getWidth() - l + llMore.getWidth() + rlColumn.getLeft() == mScreenWidth)
		{
			leftShapeImage.setVisibility(View.VISIBLE);
			rightShapeImage.setVisibility(View.GONE);
			return;
		}
		leftShapeImage.setVisibility(View.VISIBLE);
	}

	private boolean isOk()
	{
		return (!activity.isFinishing() && llContent != null
				&& leftShapeImage != null && rightShapeImage != null
				&& llMore != null && rlColumn != null);
	}

	private void shapeState()
	{
		if (!activity.isFinishing() && llContent != null)
		{
			measure(0, 0);
			// 如果整体宽度小于屏幕宽度的话，那左右阴影都隐藏
			if (mScreenWidth >= getMeasuredWidth())
			{
				leftShapeImage.setVisibility(View.GONE);
				rightShapeImage.setVisibility(View.GONE);
			}
		} else
		{
			return;
		}
		if (getLeft() == 0)
		{
			// 最左侧，左边隐藏，右边显示
			leftShapeImage.setVisibility(View.GONE);
			rightShapeImage.setVisibility(View.VISIBLE);
			return;
		}
		if (getRight() == getMeasuredWidth() - mScreenWidth)
		{
			// 最右侧，左边显示，右边隐藏
			leftShapeImage.setVisibility(View.VISIBLE);
			rightShapeImage.setVisibility(View.GONE);
			return;
		}
		// 中间位置
		leftShapeImage.setVisibility(View.VISIBLE);
		rightShapeImage.setVisibility(View.VISIBLE);
	}
}
