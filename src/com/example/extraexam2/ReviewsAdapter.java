package com.example.extraexam2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.extraexam2.db.BookHelper;
import com.example.extraexam2.db.Review;

/**
 * Created by gfv on 05.02.14.
 */
public class ReviewsAdapter extends BaseAdapter {

    LayoutInflater inflater;
    BookHelper helper;
    Integer bookId;

    ReviewsAdapter(Context ctx, BookHelper helper, Integer bookId) {
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.helper = helper;
        this.bookId = bookId;
    }

    @Override
    public int getCount() {
        return helper.getReviewsByBookId(bookId).size();
    }

    @Override
    public Object getItem(int position) {
        return helper.getReviewsByBookId(bookId).get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.review_row, null);
        }

        Review review = (Review) getItem(position);
        ((TextView) v.findViewById(R.id.reviewText)).setText(review.getReview());
        ((TextView) v.findViewById(R.id.reviewGrade)).setText(Integer.toString(review.getMark()));
        return v;
    }
}