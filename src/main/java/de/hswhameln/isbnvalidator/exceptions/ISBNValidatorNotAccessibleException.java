package de.hswhameln.isbnvalidator.exceptions;

public class ISBNValidatorNotAccessibleException extends RuntimeException {

    public ISBNValidatorNotAccessibleException() {
        super("Zugriff auf die API des ISBN Validator aktuell nicht verfügbar.");
    }
}
