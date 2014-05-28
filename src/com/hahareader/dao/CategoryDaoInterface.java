package com.hahareader.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.hahareader.bean.CategoryItem;

public interface CategoryDaoInterface
{
	public boolean addCache(CategoryItem item);

	public boolean deleteCache(String whereClause, String[] whereArgs);

	public boolean updataCache(ContentValues values, String whereClause,
			String[] whereArgs);

	public Map<String, String> viewCache(String selection,
			String[] selectionArgs);

	public List<Map<String, String>> listCache(String selection,
			String[] selectionArgs);

	public void clearFeedTable();
}
