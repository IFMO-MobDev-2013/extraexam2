package local.firespace.CardIndex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BookDBAdapter {
	public static final String DATABASE_ID = "_id";
	public static final String DATABASE_NAME = "cardindex";
	public static final int DATABASE_VERSION = 1;
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String AUTHOR = "author";
	public static final String LANGUAGE = "lang";
	public static final String COUNT = "count";
	public static final String COST = "cost";
	public static final String URL = "url";

	private final Context mcontext;

	private DBHelper helper;
	private SQLiteDatabase db;

	public Book[] getAllData() {
		Cursor cursor = db.query(DATABASE_NAME, null, null, null, null, null, null);
		Book[] result = null;
		int size = -1;

		while (cursor.moveToNext()) {
			if (size == -1) result = new Book[cursor.getCount()];
			int id = cursor.getInt(cursor.getColumnIndex(ID));
			String name = cursor.getString(cursor.getColumnIndex(NAME));
			String author = cursor.getString(cursor.getColumnIndex(AUTHOR));
			String lang = cursor.getString(cursor.getColumnIndex(LANGUAGE));
			int count = cursor.getInt(cursor.getColumnIndex(COUNT));
			int cost = cursor.getInt(cursor.getColumnIndex(COST));
			String url = cursor.getString(cursor.getColumnIndex(URL));

			size++;
			result[size] = new Book(id, name, author, lang, count, cost, url);
		}

		return result;
	}

	public Book getBookByID(Integer id) {
		Cursor cursor = db.query(DATABASE_NAME, null, ID + " = ?", new String[]{id.toString()}, null, null, null);
		cursor.moveToFirst();

		String name = cursor.getString(cursor.getColumnIndex(NAME));
		String author = cursor.getString(cursor.getColumnIndex(AUTHOR));
		String lang = cursor.getString(cursor.getColumnIndex(LANGUAGE));
		int count = cursor.getInt(cursor.getColumnIndex(COUNT));
		int cost = cursor.getInt(cursor.getColumnIndex(COST));
		String url = cursor.getString(cursor.getColumnIndex(URL));

		return new Book(id, name, author, lang, count, cost, url);
	}

	public Book[] getBooksByName(String name) {
		Cursor cursor = db.query(DATABASE_NAME, null, null, null, null, null, null);
		ArrayList<Book> books = new ArrayList<Book>();
		while (cursor.moveToNext()) {
			String supposedName = cursor.getString(cursor.getColumnIndex(NAME));
			//noinspection ConstantConditions
			if (supposedName.toLowerCase().contains(name.toLowerCase())) {
				books.add(getBookByID(cursor.getInt(cursor.getColumnIndex(ID))));
			}
		}

		return books.toArray(new Book[books.size()]);
	}

	public boolean isEmpty() {
		Cursor cursor = db.query(DATABASE_NAME, null, null, null, null, null, null);
		cursor.moveToFirst();
		return cursor.getCount() == 0;
	}

	public void insertBook(Book book) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ID, book.getId());
		contentValues.put(NAME, book.getName());
		contentValues.put(AUTHOR, book.getAuthor());
		contentValues.put(LANGUAGE, book.getLang());
		contentValues.put(COUNT, book.getCount());
		contentValues.put(COST, book.getCost());
		contentValues.put(URL, book.getUrl());
		db.insert(DATABASE_NAME, null, contentValues);
	}

	public void clear() {
		helper.onUpgrade(db, 1 , 2);
	}

	public BookDBAdapter(Context context) {
		this.mcontext = context;
		helper = new DBHelper(mcontext);
		db = helper.getWritableDatabase();
	}

	private class DBHelper extends SQLiteOpenHelper {

		private static final String SQL_CREATE_ENTRIES = "create table "
				+ DATABASE_NAME + " ("
				+ DATABASE_ID + " integer primary key autoincrement, "
				+ ID + " integer not null, "
				+ NAME + " text not null, "
				+ AUTHOR + " text not null, "
				+ LANGUAGE + " text not null, "
				+ COUNT + " integer not null, "
				+ COST + " text not null, "
				+ URL + " text not null ); ";

		private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DATABASE_NAME;

		DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_ENTRIES);
			onCreate(db);
		}
	}
}
