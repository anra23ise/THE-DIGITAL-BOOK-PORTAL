package com.entity;

public class Wishlist {
    private int id;
    private int userId;
    private int bookId;
    private BookDtls book;

    public Wishlist() {}

    public Wishlist(int id, int userId, int bookId, BookDtls book) {
        super();
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.book = book;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public BookDtls getBook() {
        return book;
    }

    public void setBook(BookDtls book) {
        this.book = book;
    }
}
