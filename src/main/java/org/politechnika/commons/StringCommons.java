package org.politechnika.commons;

public class StringCommons {

    public static String getPolishHandName(String handName) {
        return "right".equals(handName) ? "prawa" : "lewa";
    }
}
