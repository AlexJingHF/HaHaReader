package com.hahareader.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.SQLException;

import com.hahareader.dao.CategoryDao;
import com.hahareader.db.SQLHelper;
import com.hahareader.tool.Mlog;

public class CategoryManager
{

	private final String TAG = CategoryManager.class.getSimpleName();

	private static CategoryManager INSTANCE;

	public static List<CategoryItem> defaultUserCategories;

	public static List<CategoryItem> defaultOtherCategories;

	static
	{
		defaultUserCategories = new ArrayList<CategoryItem>();
		defaultOtherCategories = new ArrayList<CategoryItem>();
		defaultUserCategories.add(new CategoryItem(1, "最新", 1, 1));
		defaultUserCategories.add(new CategoryItem(2, "最哈", 2, 1));
		defaultUserCategories.add(new CategoryItem(3, "图片", 3, 1));
		defaultUserCategories.add(new CategoryItem(4, "热评", 4, 1));
//		defaultUserCategories.add(new CategoryItem(5, "科技", 5, 1));
//		defaultUserCategories.add(new CategoryItem(6, "体育", 6, 1));
//		defaultUserCategories.add(new CategoryItem(7, "军事", 7, 1));
//		defaultOtherCategories.add(new CategoryItem(8, "财经", 1, 0));
//		defaultOtherCategories.add(new CategoryItem(9, "汽车", 2, 0));
//		defaultOtherCategories.add(new CategoryItem(10, "房产", 3, 0));
//		defaultOtherCategories.add(new CategoryItem(11, "社会", 4, 0));
//		defaultOtherCategories.add(new CategoryItem(12, "情感", 5, 0));
//		defaultOtherCategories.add(new CategoryItem(13, "女人", 6, 0));
//		defaultOtherCategories.add(new CategoryItem(14, "旅游", 7, 0));
//		defaultOtherCategories.add(new CategoryItem(15, "健康", 8, 0));
//		defaultOtherCategories.add(new CategoryItem(16, "美女", 9, 0));
//		defaultOtherCategories.add(new CategoryItem(17, "游戏", 10, 0));
//		defaultOtherCategories.add(new CategoryItem(18, "数码", 11, 0));
//		defaultUserCategories.add(new CategoryItem(19, "娱乐", 12, 0));
	}

	private CategoryDao mCategoryDao = null;

	private boolean userExist = false;

	private CategoryManager(SQLHelper helper) throws SQLException
	{
		if (mCategoryDao == null)
		{
			mCategoryDao = new CategoryDao(helper.getContext());
		}
	}

	public static CategoryManager getManager(SQLHelper helper)
			throws SQLException
	{
		if (INSTANCE == null)
		{
			synchronized (CategoryManager.class)
			{
				if (null == INSTANCE)
				{
					INSTANCE = new CategoryManager(helper);
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * 描述：清除所有分类<br>
	 * 
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void deleteAllCategory()
	{
		mCategoryDao.clearFeedTable();
	}

	public List<CategoryItem> getUserCategory()
	{
		Object cacheList = mCategoryDao.listCache(SQLHelper.getSelected()
				+ "=?", new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty())
		{
			userExist = true;
			List<Map<String, String>> maplist = (List<Map<String, String>>) cacheList;
			int count = maplist.size();
			List<CategoryItem> list = new ArrayList<CategoryItem>();
			for (int i = 0; i < count; i++)
			{
				CategoryItem item = new CategoryItem();
				item.setId(Integer.valueOf(maplist.get(i)
						.get(SQLHelper.getId())));
				item.setName(maplist.get(i).get(SQLHelper.getName()));
				item.setOrderId(Integer.valueOf(maplist.get(i).get(
						SQLHelper.getOrderid())));
				item.setSelected(Integer.valueOf(maplist.get(i).get(
						SQLHelper.getSelected())));
				list.add(item);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserCategories;
	}

	public List<CategoryItem> getOtherCategory()
	{
		Object cacheList = mCategoryDao.listCache(SQLHelper.getSelected()
				+ "= ?", new String[] { "0" });
		List<CategoryItem> list = new ArrayList<CategoryItem>();
		if (cacheList != null && !((List) cacheList).isEmpty())
		{
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++)
			{
				CategoryItem navigate = new CategoryItem();
				navigate.setId(Integer.valueOf(maplist.get(i).get(
						SQLHelper.getId())));
				navigate.setName(maplist.get(i).get(SQLHelper.getName()));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(
						SQLHelper.getOrderid())));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(
						SQLHelper.getSelected())));
				list.add(navigate);
			}
			return list;
		}
		if (userExist)
		{
			return list;
		}
		cacheList = defaultOtherCategories;
		return (List<CategoryItem>) cacheList;
	}

	private void initDefaultChannel()
	{
		Mlog.d(TAG, "initDefaultChannel");
		deleteAllCategory();
		saveUserChannel(defaultUserCategories);
		saveOtherChannel(defaultOtherCategories);
	}

	private void saveOtherChannel(List<CategoryItem> userList)
	{
		for (int i = 0; i < userList.size(); i++)
		{
			CategoryItem channelItem = (CategoryItem) userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			mCategoryDao.addCache(channelItem);
		}
	}

	private void saveUserChannel(List<CategoryItem> otherList)
	{
		for (int i = 0; i < otherList.size(); i++)
		{
			CategoryItem channelItem = (CategoryItem) otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			mCategoryDao.addCache(channelItem);
		}
	}

	public boolean isUserExist()
	{
		return userExist;
	}

	public void setUserExist(boolean userExist)
	{
		this.userExist = userExist;
	}

}
