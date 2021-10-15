package de.hswhameln.isbnvalidator.utils;

import de.hswhameln.isbnvalidator.exceptions.ISBNValidatorNotAccessibleException;
import org.json.JSONException;
import org.json.JSONObject;

public class ISBNAPI {

    private static RestConsumer isbnValidator;

    public static boolean validateISBN(String isbn) {
        if(isbnValidator == null) {
            isbnValidator = new RestConsumer("http://localhost:3000");
        }
        JSONObject response = isbnValidator.getRequest("/validateISBN", isbn);
        try {
            return response.getBoolean("valid");
        } catch (JSONException e) {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

    public static int getCheckDigit(String isbn) {
        if(isbnValidator == null) {
            isbnValidator = new RestConsumer("http://localhost:3000");
        }
        JSONObject response = isbnValidator.getRequest("/checkDigit", isbn);
        try {
            return response.getInt("checkDigit");
        } catch (JSONException e) {
            throw new ISBNValidatorNotAccessibleException();
        }
    }

}
