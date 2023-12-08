package Boundary;

import java.util.Base64;

public class Base64EncodeConversion {
    /*  This method takes in the password entered by the user 
    as the input and returns a Base 64 encoded
    string as the output */
    public static String encodeString (String decodedString) {
        String encodedString = Base64.getEncoder().encodeToString(decodedString.getBytes());
        return encodedString;
    }
}