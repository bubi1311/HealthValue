package de.clever.healthvalue.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.clever.healthvalue.util.DateTimeUtils;

public class Temperature {

    private double bodyTemperature;
    private String scale; // C = Celsius, F = Fahrenheit
    private long id;
    private Date date; // Date and time where the temperature was added.

    /***
     * Temperature constructor.
     * @param id
     * @param bodyTemperature
     * @param scale
     */
    public Temperature(long id, double bodyTemperature, String scale) {
        this.bodyTemperature = bodyTemperature;
        this.scale = scale;
        this.id = id;
    }

    /***
     * Temperature constructor used for creating a new object read out of the DB.
     * @param id
     * @param bodyTemperature
     * @param scale
     * @param date
     */
    public Temperature(long id, double bodyTemperature, String scale, Date date) {
        this.id = id;
        this.bodyTemperature = bodyTemperature;
        this.scale = scale;
        this.date = date;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }


    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the date and time as a string in local format.
     *
     * @return localized date and time where the temperature was added as string.
     */
    public String getDateTime() {
        return DateTimeUtils.getDateTimeAsLocalizedString(getDate());
    }

    public Date parseLocalStringToDate(String timestamp) throws ParseException {

        Date date = new SimpleDateFormat(DateTimeUtils.dateTimePattern).parse(timestamp);

        return date;
    }


}
