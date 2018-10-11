package de.clever.healthvalue.util;

public class StringUtils {

    /**
     * Checks if a string is empty.
     * @param text - The string that should be checked.
     * @return - true, if the given string is empty. Otherwise false.
     */
    public static Boolean isTextEmpty(String text){
        return (text == null) || text.isEmpty() || (text.trim().length() == 0);

    }


}
