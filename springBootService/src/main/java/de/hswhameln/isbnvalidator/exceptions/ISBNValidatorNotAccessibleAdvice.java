package de.hswhameln.isbnvalidator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice für den Fall, dass der ISBNValidator nicht erreichbar ist
 */
@ControllerAdvice
public class ISBNValidatorNotAccessibleAdvice {

    /**
     * @param ex
     * @return String
     */
    @ResponseBody
    @ExceptionHandler(ISBNValidatorNotAccessibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String isbnValidatorNotAccessibleHandler(ISBNValidatorNotAccessibleException ex) {
        return ex.getMessage();
    }
}
