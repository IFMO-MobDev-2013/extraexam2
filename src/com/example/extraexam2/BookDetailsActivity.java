package com.example.extraexam2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.example.extraexam2.db.Book;
import com.example.extraexam2.db.BookHelper;

/**
 * Created by gfv on 05.02.14.
 */
public class BookDetailsActivity extends Activity {
    public static final String BOOK_EXTRA = "md.zoidberg.ee2.Book";
    BookHelper helper;
    ReviewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);

        Book book = (Book) getIntent().getSerializableExtra(BOOK_EXTRA);
        ((TextView) findViewById(R.id.bookAuthor)).setText(book.getAuthor());
        ((TextView) findViewById(R.id.bookTitle)).setText(book.getName());
        ((TextView) findViewById(R.id.bookPrice)).setText(Integer.toString(book.getPrice()));
        ((TextView) findViewById(R.id.bookLanguage)).setText(book.getLanguage());
        ((TextView) findViewById(R.id.bookPageNumber)).setText(Integer.toString(book.getPageNumber()));
        findViewById(R.id.buyButton).setOnClickListener(new UrlOnClick(book.getURL()));

        helper = new BookHelper(this);
        adapter = new ReviewsAdapter(this, helper, book.getId());
        ((ListView) findViewById(R.id.reviewList)).setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private class UrlOnClick implements View.OnClickListener {
        String url;

        private UrlOnClick(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}