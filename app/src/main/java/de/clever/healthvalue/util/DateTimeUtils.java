package de.clever.healthvalue.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    private DateTimeUtils(){

    }

    /**
     * Get the date and time as a string in local format.
     *
     * @param datetime - The Date-object that should be returned as a String-object.
     * @return localized date and time where the temperature was added as string.
     */
    public static String getDateTimeAsLocalizedString(Date datetime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(datetime);
    }

}
