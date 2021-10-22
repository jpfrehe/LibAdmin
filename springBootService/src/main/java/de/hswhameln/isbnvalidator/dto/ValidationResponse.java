package de.hswhameln.isbnvalidator.dto;

/**
 * Stellt die Antwort für eine Validierungsanfrage dar.
 */
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

    /**
     * @return String
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return boolean
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
