package Boundary;

import java.util.Base64;

public class Base64EncodeConversion {
    public static String encodeString (String decodedString) {
        String encodedString = Base64.getEncoder().encodeToString(decodedString.getBytes());
        System.out.println("Encoded String: " + encodedString);
        return encodedString;
    }
}