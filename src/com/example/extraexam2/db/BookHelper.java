package com.example.extraexam2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import au.com.bytecode.opencsv.CSVReader;
import com.example.extraexam2.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfv on 05.02.14.
 */
public class BookHelper extends SQLiteOpenHelper {
    private static final String BOOKS_DB_NAME = "books.db";

    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_REVIEWS = "reviews";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_AUTHOR = "author";
    public static final String COL_LANGUAGE = "language";
    public static final String COL_PAGE_NUMBER = "pageNumber";
    public static final String COL_PRICE = "price";
    public static final String COL_URL = "url";
    public static final String COL_BOOK_ID = "bookId";
    public static final String COL_MARK = "mark";
    public static final String COL_REVIEW = "review";

    private InputStream booksRaw;
    private InputStream reviewsRaw;
    private static final int VERSION = 1;

    public BookHelper(Context context) {
        super(context, BOOKS_DB_NAME, null, VERSION);
        booksRaw = context.getResources().openRawResource(R.raw.books);
        reviewsRaw = context.getResources().openRawResource(R.raw.reviews);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_BOOKS + " (" +
                COL_ID + " integer, " +
                COL_NAME + " text, " +
                COL_AUTHOR + " text, " +
                COL_LANGUAGE + " text, " +
                COL_PAGE_NUMBER + " integer, " +
                COL_PRICE + " integer, " +
                COL_URL + " text);");

        db.execSQL("create table " + TABLE_REVIEWS + " (" +
                COL_BOOK_ID + " integer, " +
                COL_MARK + " integer, " +
                COL_REVIEW + " text);");

        try {
            initDatabaseData(db);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDatabaseData(SQLiteDatabase db) throws IOException {
        CSVReader booksReader = new CSVReader(new InputStreamReader(booksRaw));
        CSVReader reviewsReader = new CSVReader(new InputStreamReader(reviewsRaw));
        List<String[]> booksList = booksReader.readAll();
        List<String[]> reviewsList = reviewsReader.readAll();

        for (String[] values : booksList) {
            Book newBook = new Book(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6]);
            insertBook(db, newBook);
        }

        for (String[] values : reviewsList) {
            Review newReview = new Review(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
            insertReview(db, newReview);
        }
    }

    public void insertBook(SQLiteDatabase db, Book b) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, b.getId());
        values.put(COL_NAME, b.getName());
        values.put(COL_AUTHOR, b.getAuthor());
        values.put(COL_LANGUAGE, b.getLanguage());
        values.put(COL_PAGE_NUMBER, b.getPageNumber());
        values.put(COL_PRICE, b.getPrice());
        values.put(COL_URL, b.getURL());
        db.insert(TABLE_BOOKS, null, values);
    }

    public void insertReview(SQLiteDatabase db, Review r) {
        ContentValues values = new ContentValues();
        values.put(COL_BOOK_ID, r.getBookId());
        values.put(COL_MARK, r.getMark());
        values.put(COL_REVIEW, r.getReview());
        db.insert(TABLE_REVIEWS, null, values);
    }

    public ArrayList<Book> getBooks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.query(TABLE_BOOKS, new String[]{COL_ID, COL_NAME, COL_AUTHOR, COL_LANGUAGE, COL_PAGE_NUMBER, COL_PRICE, COL_URL}, null, null, null, null, null);
        ArrayList<Book> books = new ArrayList<Book>();

        if (!cur.moveToFirst()) return books;
        do {
            books.add(new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getInt(4), cur.getInt(5), cur.getString(6)));
        } while (cur.moveToNext());

        return books;
    }

    public ArrayList<Review> getReviewsByBookId(Integer bookId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.query(TABLE_REVIEWS, new String[]{COL_BOOK_ID, COL_MARK, COL_REVIEW}, COL_BOOK_ID + " = ?", new String[]{Integer.toString(bookId)}, null, null, COL_MARK + " ASC");
        ArrayList<Review> reviews = new ArrayList<Review>();

        if (!cur.moveToFirst()) return reviews;
        do {
            reviews.add(new Review(cur.getInt(0), cur.getInt(1), cur.getString(2)));
        } while (cur.moveToNext());

        return reviews;
    }

    public ArrayList<Book> getBooksByCriteria(String pattern) {
        SQLiteDatabase db = getReadableDatabase();

        // I'm not Yandex by any means, so a LIKE %name% should be okay
        Cursor cur = db.query(TABLE_BOOKS, new String[]{COL_ID, COL_NAME, COL_AUTHOR, COL_LANGUAGE, COL_PAGE_NUMBER, COL_PRICE, COL_URL}, COL_NAME + " like ?", new String[]{"%" + pattern + "%"}, null, null, null);
        ArrayList<Book> books = new ArrayList<Book>();

        if (!cur.moveToFirst()) return books;
        do {
            books.add(new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getInt(4), cur.getInt(5), cur.getString(6)));
        } while (cur.moveToNext());

        return books;
    }

    public Book getBookById(Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.query(TABLE_BOOKS, new String[]{COL_ID, COL_NAME, COL_AUTHOR, COL_LANGUAGE, COL_PAGE_NUMBER, COL_PRICE, COL_URL}, COL_ID + " = ?", new String[]{Integer.toString(id)}, null, null, null);
        if (cur.getCount() == 1)
            return new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getInt(4), cur.getInt(5), cur.getString(6));
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_BOOKS + ";");
        db.execSQL("drop table if exists " + TABLE_REVIEWS + ";");
        onCreate(db);
    }
}
