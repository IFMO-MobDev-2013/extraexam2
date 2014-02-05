package ru.mihver1.bookshelf.db;

/**
 * Created by mihver1 on 05.02.14.
 */
public class ReviewItem {
    public String uid;
    public String rate;
    public String msg;

    public ReviewItem(String uid, String rate, String msg) {
        this.uid = uid.substring(1, uid.length()-1);
        this.rate = rate.substring(1, rate.length()-1);
        this.msg = msg.substring(1, msg.length()-1);
    }

    public String getCSVLine() {
        return uid + ";" +
                rate + ";" +
                msg;

    }
}
