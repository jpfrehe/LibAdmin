package de.hswhameln.isbnvalidator.dto;

public class ValidationRequest {
    public String isbn;

    public ValidationRequest(String isbn) {
        this.isbn = isbn;
    }


    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
