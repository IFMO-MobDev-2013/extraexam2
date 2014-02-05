package ru.mihver1.bookshelf.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mihver1 on 05.02.14.
 */
public class DBStorageBooks {


    private static final String DB_NAME = "shelf.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "books";
    private static final String FIELDS = "uid TEXT, name TEXT, author TEXT, lang TEXT, pages TEXT, price TEXT, url TEXT";
    private static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + FIELDS + ")";

    private SQLiteDatabase db;

    public DBStorageBooks(Context context) {
        SQLiteOpenHelper dbhelper = new SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE);
                Log.d("YOLO", SQL_CREATE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.d("YOLO", "Upgrading db");
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            }
        };
        db = dbhelper.getWritableDatabase();
    }


    public ArrayList<BookItem> getBookList(String name) {
        ArrayList<BookItem> answer = new ArrayList<BookItem>();
        Cursor cur = null;
        if(name != null) {
            cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%" + name + "%'", null);
        } else {
            cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }
        cur.moveToFirst();
        if(cur.getCount() == 0) {
            return null;
        }
        while(!cur.isAfterLast()) {
            BookItem rec = new BookItem(cur.getString(0),
                                        cur.getString(1),
                                        cur.getString(2),
                                        cur.getString(3),
                                        cur.getString(4),
                                        cur.getString(5),
                                        cur.getString(6));
            answer.add(rec);
            cur.moveToNext();
        }

        return answer;
    }

    public BookItem getItem(int uid) {
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE uid = '" + uid + "'", null);
        cur.moveToFirst();
        if(cur.getCount() == 0) {
            return null;
        }
        BookItem rec = new BookItem(cur.getString(0),
                cur.getString(1),
                cur.getString(2),
                cur.getString(3),
                cur.getString(4),
                cur.getString(5),
                cur.getString(6));
        return rec;
    }

    public void addItem(String line) {
        String[] input = line.split(";");
        if(input.length < 7) {
            Log.d("YOLO", line);
            for(String s: input) {
                Log.d("YOLO", "\t" + s);
            }
        }
        ContentValues values = new ContentValues();
        values.put("uid", input[0]);
        values.put("name", input[1]);
        values.put("author", input[2]);
        values.put("lang", input[3]);
        values.put("pages", input[4]);
        values.put("price", input[5]);
        values.put("url", input[6]);
        db.insert(TABLE_NAME, null, values);
    }

}
