package com.hahareader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil
{
	private static DBUtil INSTANCE;
	private Context mContext;
	private SQLHelper mSQLHelper;
	private SQLiteDatabase mSQLiteDatabase;

	private DBUtil(Context context)
	{
		mContext = context;
		mSQLHelper = new SQLHelper(mContext);
		mSQLiteDatabase = mSQLHelper.getWritableDatabase();
	}

	public static DBUtil getInstance(Context context)
	{
		if (INSTANCE == null)
		{
			synchronized (DBUtil.class)
			{
				if (null == INSTANCE)
				{
					INSTANCE = new DBUtil(context);
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * 描述：关闭数据库<br>
	 * 
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void close()
	{
		mSQLHelper.close();
		mSQLiteDatabase.close();
		mSQLHelper = null;
		mSQLiteDatabase = null;
		INSTANCE = null;
	}

	/**
	 * 描述：添加数据<br>
	 * 
	 * @param ContentValues
	 *            values
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void insertData(ContentValues values)
	{
		mSQLiteDatabase.insert(SQLHelper.getTableName(), null, values);
	}

	/**
	 * 描述：更新数据<br>
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void updateData(ContentValues values, String whereClause,
			String[] whereArgs)
	{
		mSQLiteDatabase.update(SQLHelper.getTableName(), values, whereClause,
				whereArgs);
	}

	/**
	 * 描述：删除数据<br>
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @author alex
	 * @return void
	 * @data 2014年5月27日
	 */
	public void delelteData(ContentValues values, String whereClause,
			String[] whereArgs)
	{
		mSQLiteDatabase
				.delete(SQLHelper.getTableName(), whereClause, whereArgs);
	}

	/**
	 * 描述：查询数据<br>
	 * 
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 * @author alex
	 * @return Cursor
	 * @data 2014年5月27日
	 */
	public Cursor selectData(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy)
	{
		Cursor cursor = mSQLiteDatabase.query(SQLHelper.getTableName(),
				columns, selection, selectionArgs, groupBy, having, orderBy);
		return cursor;
	}
}
