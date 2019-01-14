package de.clever.healthvalue.healthvalue;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.clever.healthvalue.util.DateTimeUtils;

public class TestDateTimeUtils {

    // Test dates
    private final static Date date1 = (new GregorianCalendar(2018, Calendar.NOVEMBER, 13, 7, 20, 0)).getTime();
    private final static Date date2 = (new GregorianCalendar(1984, Calendar.JUNE, 22, 14, 23, 13)).getTime();
    private final static Date date3 = (new GregorianCalendar(1940, Calendar.JANUARY, 11, 23, 21, 8)).getTime();

    // expected Strings in Germany yyyy-MM-dd HH:mm:ss
    private final static String dateString1 = "2018-11-13 07:20:00";
    private final static String dateString2 = "1984-06-22 14:23:13";
    private final static String dateString3 = "1940-01-11 23:21:08";

    private final static String patternInternational = "yyyy-MM-dd HH:mm:ss";



    @Test
    public void testGetDateTimeAsLocalizedString() {
        Assert.assertEquals(dateString1, DateTimeUtils.getDateTimeAsLocalizedString(date1, patternInternational));
        Assert.assertEquals(dateString2, DateTimeUtils.getDateTimeAsLocalizedString(date2, patternInternational));
        Assert.assertEquals(dateString3, DateTimeUtils.getDateTimeAsLocalizedString(date3, patternInternational));
    }
}
