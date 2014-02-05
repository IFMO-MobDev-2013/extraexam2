package com.example.extraexam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.extraexam2.db.Book;
import com.example.extraexam2.db.BookHelper;

public class BookListActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    BookHelper helper;
    BookListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        helper = new BookHelper(this);
        adapter = new BookListAdapter(this, helper);

        ListView bookList = (ListView) findViewById(R.id.bookList);

        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) parent.getAdapter().getItem(position);
                Intent intent = new Intent(BookListActivity.this, BookDetailsActivity.class);
                intent.putExtra(BookDetailsActivity.BOOK_EXTRA, book);
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_go_search:
                startActivity(new Intent(this, BookSearchActivity.class));
                break;
        }
        return false;
    }
}
