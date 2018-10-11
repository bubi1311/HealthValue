package de.clever.healthvalue.healthvalue;

import org.junit.Test;

import de.clever.healthvalue.util.StringUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestStringUtils {

    private final static String testText1 = "I'm a text.";
    private final static String testTextWhiteSpaceEnd = "I'm a text with a whitespace at the end. ";
    private final static String testTextWhiteSpaceBegin = " I'm a text with a whitespace at the start.";
    private final static String testTextOnlyNumbers = "1234567890";
    private final static String testTextOnlyNumbersWithWhiteSpaces = "1 2 3 4 5 6 7 8 9 0";
    private final static String testTextEmptyWhiteSpace = " ";
    private final static String testTextEmpty = "";
    private final static String testTextEmptyNull = null;

    @Test
    public void isTextEmpty_isCorrect() {


        // expect true, because string is empty.
        assertTrue(StringUtils.isTextEmpty(testTextEmpty));
        assertTrue(StringUtils.isTextEmpty(testTextEmptyNull));
        assertTrue(StringUtils.isTextEmpty(testTextEmptyWhiteSpace));


        // expect false, because string is not empty.
        assertFalse(StringUtils.isTextEmpty(testText1));
        assertFalse(StringUtils.isTextEmpty(testTextWhiteSpaceBegin));
        assertFalse(StringUtils.isTextEmpty(testTextWhiteSpaceEnd));
        assertFalse(StringUtils.isTextEmpty(testTextOnlyNumbers));
        assertFalse(StringUtils.isTextEmpty(testTextOnlyNumbersWithWhiteSpaces));
    }
}
