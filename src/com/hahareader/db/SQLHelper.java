package com.hahareader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper
{
	/**
	 * 数据库名称
	 */
	private static final String DB_NAME = "database.db";
	private static final int VERSION = 1;
	private static final String TABLE_NAME = "category";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String ORDERID = "orderId";
	private static final String SELECTED = "selected";
	private Context mContext;

	public SQLHelper(Context context)
	{
		super(context, getDbName(), null, getVersion());
		this.setContext(context);
	}

	public SQLHelper(Context context, String name, CursorFactory factory,
			int version)
	{
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "create table if not exists " + getTableName()
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + getId()
				+ " INTEGER , " + getName() + " TEXT , " + getOrderid() + " INTEGER , "
				+ getSelected() + " SELECTED)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		onCreate(db);
	}

	public static String getDbName()
	{
		return DB_NAME;
	}

	public static int getVersion()
	{
		return VERSION;
	}

	public static String getTableName()
	{
		return TABLE_NAME;
	}

	public Context getContext()
	{
		return mContext;
	}

	public void setContext(Context mContext)
	{
		this.mContext = mContext;
	}

	public static String getId()
	{
		return ID;
	}

	public static String getName()
	{
		return NAME;
	}

	public static String getOrderid()
	{
		return ORDERID;
	}

	public static String getSelected()
	{
		return SELECTED;
	}

}
