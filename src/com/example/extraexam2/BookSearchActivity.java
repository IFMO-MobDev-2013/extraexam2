package com.example.extraexam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.example.extraexam2.db.Book;
import com.example.extraexam2.db.BookHelper;

import java.util.ArrayList;

/**
 * Created by gfv on 05.02.14.
 */
public class BookSearchActivity extends Activity {
    BookHelper helper;
    BookFixedListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_search);
        helper = new BookHelper(this);
        adapter = new BookFixedListAdapter(this, new ArrayList<Book>());
        ListView bookList = (ListView) findViewById(R.id.bookList);

        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) parent.getAdapter().getItem(position);
                Intent intent = new Intent(BookSearchActivity.this, BookDetailsActivity.class);
                intent.putExtra(BookDetailsActivity.BOOK_EXTRA, book);
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void onSearchClick(View view) {
        if (view.getId() == R.id.searchButton) {
            String pattern = ((EditText) findViewById(R.id.searchEdit)).getText().toString();
            ArrayList<Book> books = helper.getBooksByCriteria(pattern);

            adapter.setBooks(books);
            adapter.notifyDataSetChanged();
        }
    }
}