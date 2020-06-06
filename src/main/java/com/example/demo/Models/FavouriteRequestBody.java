package com.example.demo.Models;

public class FavouriteRequestBody {

    private Book book;
    private Favourite favourite;

    public FavouriteRequestBody() {
    }

    public FavouriteRequestBody(Book book, Favourite favourite) {
        this.book = book;
        this.favourite = favourite;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourite favourite) {
        this.favourite = favourite;
    }
}
