package com.example.extraexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.extraexam2.db.Book;
import com.example.extraexam2.db.BookHelper;

/**
 * Created by gfv on 05.02.14.
 */
class BookListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    BookHelper helper;

    BookListAdapter(Context ctx, BookHelper helper) {
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.helper = helper;
    }

    @Override
    public int getCount() {
        return helper.getBooks().size();
    }

    @Override
    public Object getItem(int position) {
        return helper.getBooks().get(position);
    }

    @Override
    public long getItemId(int position) {
        return helper.getBooks().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.book_list_row, null);
        }

        Book book = (Book) getItem(position);
        ((TextView) v.findViewById(R.id.bookAuthor)).setText(book.getAuthor());
        ((TextView) v.findViewById(R.id.bookTitle)).setText(book.getName());
        ((TextView) v.findViewById(R.id.bookPrice)).setText(Integer.toString(book.getPrice()));
        return v;
    }
}
