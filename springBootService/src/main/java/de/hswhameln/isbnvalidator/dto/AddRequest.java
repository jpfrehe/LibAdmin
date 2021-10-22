package de.hswhameln.isbnvalidator.dto;

import de.hswhameln.isbnvalidator.beans.Book;

/**
 * Stellt eine Hinzufüganfrage für ein Buch dar.
 */
public class AddRequest {
    private Book book;

    /**
     * @return Book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }
}
