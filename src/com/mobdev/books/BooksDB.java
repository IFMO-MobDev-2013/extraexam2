package com.mobdev.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDB extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "booksDB.db";
	public static final String DATABASE_TABLE = "Books";
	private static final int DATABASE_VERSION = 1;
	
	public static final String KEY_ID = "_id";
	
	public static final String B_ID = "B_ID";
	public static final String B_NAME = "B_NAME";
	public static final String B_AUTHOR = "B_AUTHOR";
	public static final String B_LANG = "B_LANG";
	public static final String B_SIZE = "B_SIZE";
	public static final String B_PRICE = "B_PRICE";
	public static final String B_URL = "B_URL";
	
	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + " (" + KEY_ID
			+ " integer primary key autoincrement, " + B_ID
			+ " text not null, " + B_NAME + " text not null, " + B_AUTHOR
			+ " text not null, " + B_LANG + " text not null, " + B_SIZE
			+ " text not null, " + B_PRICE + " text not null, " + B_URL
			+ " text not null);";
	
	public BooksDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oVer, int nVer) {
		db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_NAME);
		onCreate(db);
	}

}