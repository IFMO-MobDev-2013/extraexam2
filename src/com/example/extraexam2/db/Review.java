package com.example.extraexam2.db;

import java.io.Serializable;

/**
 * Created by gfv on 05.02.14.
 */
public class Review implements Serializable {
    Integer bookId;
    Integer mark;
    String review;

    public Review(Integer bookId, Integer mark, String review) {
        this.bookId = bookId;
        this.mark = mark;
        this.review = review;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
