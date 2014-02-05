package ru.mihver1.bookshelf.db;

/**
 * Created by mihver1 on 05.02.14.
 */
public class BookItem {
    public String uid;
    public String name;
    public String author;
    public String lang;
    public String pages;
    public String price;
    public String url;

    public BookItem(String uid, String name, String author, String lang, String pages, String price, String url) {
        this.uid = uid.substring(1, uid.length()-1);
        this.name = name.substring(1, name.length()-1);
        this.author = author.substring(1, author.length()-1);
        this.lang = lang.substring(1, lang.length()-1);
        this.pages = pages.substring(1, pages.length()-1);
        this.price = price.substring(1, price.length()-1);
        this.url = url.substring(1, url.length()-1);
    }

    public String getCSVLine() {
        return uid + ";" +
               name + ";" +
               author + ";" +
               lang + ";" +
               pages + ";" +
               price + ";" +
               url;

    }
}
