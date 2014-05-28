package com.hahareader.adapter;

import java.util.ArrayList;

import com.hahareader.tool.Mlog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter
{
	private final String TAG = CategoryFragmentPagerAdapter.class.getSimpleName();

	private FragmentManager fm;
	
	private ArrayList<Fragment> fragments;
	
	public CategoryFragmentPagerAdapter(FragmentManager fm)
	{
		super(fm);
		this.setFm(fm);
	}

	
	public CategoryFragmentPagerAdapter(FragmentManager fm , ArrayList<Fragment> fragments)
	{
		super(fm);
		this.setFm(fm);
		this.setFragments(fragments);
	}
	
	@Override
	public Object instantiateItem(View container, int position)
	{
		Object obj = super.instantiateItem(container, position);
		return obj;
	}
	
	@Override
	public int getItemPosition(Object object)
	{
		return POSITION_NONE;
	}
	
	@Override
	public Fragment getItem(int position)
	{
		Mlog.d(TAG, "getItem("+position+")");
		return fragments.get(position);
	}

	@Override
	public int getCount()
	{
		return fragments.size();
	}


	public FragmentManager getFm()
	{
		return fm;
	}


	public void setFm(FragmentManager fm)
	{
		this.fm = fm;
	}


	public ArrayList<Fragment> getFragments()
	{
		return fragments;
	}


	/**
	 * 描述：处理页面<br>
	 * @param fragments
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void setFragments(ArrayList<Fragment> fragments)
	{
		if (this.fragments != null)
		{
			FragmentTransaction ft = fm.beginTransaction();
			for (Fragment fragment : this.fragments)
			{
				ft.remove(fragment);
			}
			ft.commit();
			ft = null;
			fm.executePendingTransactions();
		}
		this.fragments = fragments;
		notifyDataSetChanged();
	}

}
