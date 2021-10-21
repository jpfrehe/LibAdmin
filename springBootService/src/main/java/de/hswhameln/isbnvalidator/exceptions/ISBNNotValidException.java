package de.hswhameln.isbnvalidator.exceptions;

/**
 * Exception für den Fall, dass eine ISBN nicht valide ist
 */
public class ISBNNotValidException extends RuntimeException {
    public ISBNNotValidException(String isbn, String message) {
        super(String.format("%s Inserting the ISBN %s failed.", message, isbn));
    }
}
