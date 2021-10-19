package de.hswhameln.isbnvalidator.exceptions;

public class ISBNNotValidException extends RuntimeException{
    public ISBNNotValidException(String isbn, String message) {
        super(String.format("%s Inserting the ISBN %s failed.", message, isbn));
    }
}
