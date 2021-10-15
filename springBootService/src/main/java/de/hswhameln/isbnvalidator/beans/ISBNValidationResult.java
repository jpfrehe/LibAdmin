package de.hswhameln.isbnvalidator.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ISBNValidationResult {
    private boolean isValid;

    public ISBNValidationResult() {
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return this.isValid;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "isValid='" + isValid +
                '}';
    }
}
