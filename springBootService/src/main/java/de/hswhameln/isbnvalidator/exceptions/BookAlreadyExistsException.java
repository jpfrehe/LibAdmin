package de.hswhameln.isbnvalidator.exceptions;

/**
 * Exception für den Fall, dass ein Buch bereits existiert
 */
public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super(String.format("Inserting the Book with the isbn %s failed because it already exists", isbn));
    }
}
