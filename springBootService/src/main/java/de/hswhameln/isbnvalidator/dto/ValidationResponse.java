package de.hswhameln.isbnvalidator.dto;

public class ValidationResponse {
    private String isbn;
    

    private boolean valid;
    private String message;

    public ValidationResponse() {

    }
    
    public ValidationResponse(String isbn, boolean valid, String message) {
        this.isbn = isbn;
        this.valid = valid;
        this.message = message;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
