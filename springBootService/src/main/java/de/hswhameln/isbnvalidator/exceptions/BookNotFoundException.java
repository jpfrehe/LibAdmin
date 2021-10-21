package de.hswhameln.isbnvalidator.exceptions;

/**
 * Exception für den Fall, dass ein Buch nicht gefunden wurde
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super(String.format("Could not found the book with the isbn %s", isbn));
    }
}
