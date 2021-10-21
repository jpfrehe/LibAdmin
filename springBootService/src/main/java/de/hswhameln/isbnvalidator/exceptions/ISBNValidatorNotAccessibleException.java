package de.hswhameln.isbnvalidator.exceptions;

/**
 * Exception für den Fall, dass der ISBNValidator nicht erreichbar ist
 */
public class ISBNValidatorNotAccessibleException extends RuntimeException {

    public ISBNValidatorNotAccessibleException() {
        super("Zugriff auf die API des ISBN Validator aktuell nicht verfügbar.");
    }
}
