package com.hahareader.fragment;

import com.hahareader.R;
import com.hahareader.tool.Mlog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CategoryFragment extends Fragment
{
	private final String TAG = CategoryFragment.class.getSimpleName();
	private Activity activity;

	// private ArrayList<E>
	private String text;
	private int id;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";
		id = args != null ? args.getInt("id", 0) : 0;
		initData();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		this.activity = activity;
		super.onAttach(activity);
	}

	private void initData()
	{
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.category_fragment, null);
		TextView tv = (TextView) view.findViewById(R.id.show);
		tv.setText(text);
		
		return view;
	}
	
	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		Log.d(TAG, "onDestroyView");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Mlog.d(TAG, "onDestroy id == "+id);
	}
}
