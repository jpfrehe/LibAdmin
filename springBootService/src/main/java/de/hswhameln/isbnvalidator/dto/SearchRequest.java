package de.hswhameln.isbnvalidator.dto;

/**
 * Stellt eine Suchanfrage für eine ISBN dar.
 */
public class SearchRequest {
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
