package de.hswhameln.isbnvalidator.dto;

import de.hswhameln.isbnvalidator.beans.Book;

public class AddRequest {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}