package de.clever.healthvalue.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {



    private DateTimeUtils(){

    }


    /**
     * public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
     *
     * @param datetime - The Date-object that should be returned as a String-object.
     * @param pattern - Pattern describing the date and time format.
     * @return localized date and time where the temperature was added as string.
     */
    public static String getDateTimeAsLocalizedString(Date datetime, String pattern) {
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return dateFormat.format(datetime);
    }

    /**
     * Returns a date object which was parsed from a string using the patternFormat.
     * @param dateTime - The String-object that should parsed to a date object.
     * @param patternFormat - the pattern describing the date and time format is should be parsed from.
     * @return an date object from a formatted string.
     * @throws ParseException - Please catch the parse exception in your code.
     */
    public static Date getParsedDateTimeFromLocalizedString(String dateTime, String patternFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patternFormat, Locale.getDefault());
        Date myDate = sdf.parse(dateTime);


        return myDate;
    }

}
