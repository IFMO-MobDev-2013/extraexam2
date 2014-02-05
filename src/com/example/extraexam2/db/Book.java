package com.example.extraexam2.db;

/**
 * Created by gfv on 05.02.14.
 */
public class Book {
    private Integer id;
    private String name;
    private String author;
    private String language;
    private Integer pageNumber;
    private Integer price;
    private String URL;

    public Integer getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Book(String name, String author, String language, Integer pageNumber, Integer price, String URL) {
        this.name = name;
        this.author = author;
        this.language = language;
        this.pageNumber = pageNumber;
        this.price = price;
        this.URL = URL;
    }
}
