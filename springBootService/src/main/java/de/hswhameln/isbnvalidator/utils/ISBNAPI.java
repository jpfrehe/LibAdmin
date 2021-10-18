package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;
import org.json.JSONException;
import org.json.JSONObject;

public class ISBNAPI {

    public static boolean validateISBN(String isbn) {
        RestConsumer isbnValidator = RestConsumer.getInstance();
        JSONObject response = isbnValidator.getRequest("/ValidateISBN", isbn);
        try {
            return response.getBoolean("valid");
        } catch (JSONException e) {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

    public static int getCheckDigit(String isbn) {
        RestConsumer isbnValidator = RestConsumer.getInstance();
        JSONObject response = isbnValidator.getRequest("/checkDigit", isbn);
        try {
            return response.getInt("checkDigit");
        } catch (JSONException e) {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

}
