package de.hswhameln.isbnvalidator.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Stellt das Ergebnis einer ISBN-Validierung in Form eines Booleans dar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ISBNValidationResult {
    private boolean isValid;

    public ISBNValidationResult() {
    }

    
    /** 
     * @param isValid
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    
    /** 
     * @return boolean
     */
    public boolean isValid() {
        return this.isValid;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "ValidationResult{" + "isValid='" + isValid + '}';
    }
}
