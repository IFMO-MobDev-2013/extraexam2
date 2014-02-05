package ru.mihver1.bookshelf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.view.*;
import android.view.textservice.TextInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.mihver1.bookshelf.db.BookItem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mihver1 on 05.02.14.
 */
public class RowAdapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<BookItem> bookRecords;


    public RowAdapter(Context context, ArrayList<BookItem> bookItems) {
        bookRecords = bookItems;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookRecords.size();
    }

    @Override
    public BookItem getItem(int position) {
        return bookRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.listitem, parent, false);
            holder = new ViewHolder(
                    (TextView) view.findViewById(R.id.name),
                    (TextView) view.findViewById(R.id.author),
                    (TextView) view.findViewById(R.id.price)
            );
            view.setTag(holder);
            final int pos = position;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), Details.class).putExtra("csvInfo", bookRecords.get(pos).getCSVLine());
                    v.getContext().startActivity(myIntent);
                }
            });
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.name.setText(bookRecords.get(position).name);
        holder.author.setText(bookRecords.get(position).author);
        holder.price.setText(bookRecords.get(position).price + " RUR");

        return view;
    }

    private static class ViewHolder {
        TextView name;
        TextView author;
        TextView price;

        ViewHolder(TextView n, TextView a, TextView p) {
            name = n;
            author = a;
            price = p;
        }
    }
}
