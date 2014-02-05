package com.example.extraexam2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gfv on 05.02.14.
 */
public class BookHelper extends SQLiteOpenHelper {

    private static final String BOOKS_DB_NAME = "books.db";
    private static final int VERSION = 1;

    public BookHelper(Context context) {
        super(context, BOOKS_DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table books (" +
                "id integer primary key autoincrement, " +
                "name text, " +
                "author text, " +
                "language text, " +
                "pageNumber integer, " +
                "price integer, " +
                "url text);");

        readCSV();

    }

    private void readCSV() {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
