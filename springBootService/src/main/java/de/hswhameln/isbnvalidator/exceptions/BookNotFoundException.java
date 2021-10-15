package de.hswhameln.isbnvalidator.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super(String.format("Could not found the book with the isbn %s", isbn));
    }
}
