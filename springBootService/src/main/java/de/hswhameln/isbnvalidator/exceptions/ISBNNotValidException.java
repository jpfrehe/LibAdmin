package de.hswhameln.isbnvalidator.exceptions;

public class ISBNNotValidException extends RuntimeException{
    public ISBNNotValidException(String isbn) {
        super(String.format("The inserted ISBN is not valid. Insert Failed.", isbn));
    }
}
