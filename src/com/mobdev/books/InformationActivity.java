package com.mobdev.books;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends Activity {
	BooksDB helper;
	SQLiteDatabase db;
	public static final String BOOK = "BOOK";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		helper = new BooksDB(this);
		db = helper.getWritableDatabase();
		Intent intent = getIntent();
		int pos = intent.getIntExtra(BOOK, 0);
		Cursor cursor = db.query(BooksDB.DATABASE_TABLE, new String[] { BooksDB.B_ID,
				BooksDB.B_NAME, BooksDB.B_AUTHOR, BooksDB.B_LANG,
				BooksDB.B_SIZE, BooksDB.B_PRICE, BooksDB.B_URL }, null, null,
				null, null, null);
		cursor.moveToFirst();
		for (int i = 0; i<pos; ++i)
			cursor.moveToNext();		
		((TextView)findViewById(R.id.txt1)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_ID)));
		((TextView)findViewById(R.id.txt2)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_NAME)));
		((TextView)findViewById(R.id.txt3)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_AUTHOR)));
		((TextView)findViewById(R.id.txt4)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_LANG)));
		((TextView)findViewById(R.id.txt5)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_SIZE)));
		((TextView)findViewById(R.id.txt6)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_PRICE))+" руб.");
		((TextView)findViewById(R.id.txt7)).setText(cursor.getString(cursor.getColumnIndex(BooksDB.B_URL)));
	}
}
