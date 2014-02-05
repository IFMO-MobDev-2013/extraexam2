package com.example.extraexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.extraexam2.db.Book;

import java.util.ArrayList;

/**
 * Created by gfv on 05.02.14.
 */
class BookFixedListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<Book> books;

    BookFixedListAdapter(Context ctx, ArrayList<Book> books) {
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return books.get(position).getId();
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
