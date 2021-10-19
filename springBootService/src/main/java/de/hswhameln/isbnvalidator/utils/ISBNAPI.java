package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.dto.ValidationResponse;
import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ISBNAPI {
    private static final Logger logger = LoggerFactory.getLogger(ISBNAPI.class);

    public static boolean validateISBN(String isbn) {
        logger.info("Start Rest-call to validateISBN for isbn: " + isbn);
        RestConsumer isbnValidator = RestConsumer.getInstance();
        ValidationResponse response = isbnValidator.getRequest("/ValidateISBN", isbn);
        return response.isValid();
    }

    /*
    public static int getCheckDigit(String isbn) {
        logger.info("Start Rest-call getCheckDigit for isbn: " + isbn);
        RestConsumer isbnValidator = RestConsumer.getInstance();
        ValidationRe response = isbnValidator.getRequest("/checkDigit", isbn);
        try {
            return response.getInt("checkDigit");
        } catch (JSONException e) {
            throw new ISBNValidatorNotAccessibleException();
        }
    }
    */

}
