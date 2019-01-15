package de.clever.healthvalue.entities;

import java.util.Date;

import de.clever.healthvalue.util.DateTimeUtils;

public class Temperature {

    private double bodyTemperature;
    private String scale; // C = Celsius, F = Fahrenheit
    private long id;
    private Date date; // Date and time where the temperature was added.

    /**
     * Empty constructor. Please use the setters to fill the values!
     */
    public Temperature() {
    }

    /***
     * Temperature constructor.
     * @param id - The id in the database.
     * @param bodyTemperature - the body temperature.
     * @param scale - the scale as string (C = Celsius or F = Fahrenheit)
     */
    public Temperature(long id, double bodyTemperature, String scale) {
        this.bodyTemperature = bodyTemperature;
        this.scale = scale;
        this.id = id;
    }

    /***
     * Temperature constructor used for creating a new object read out of the DB.
     * @param id - The id in the database.
     * @param bodyTemperature - the body temperature.
     * @param scale - the scale as string (C = Celsius or F = Fahrenheit)
     * @param date - The timestamp of the measure as a date object.
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
     * Returns the Date in a localized string.
     *
     * @param pattern - the string for the pattern (example: "YYYY-MM-dd HH:mm:ss")
     * @return - The DateTime as a formatted string.
     */
    public String getDateTime(String pattern) {
        return DateTimeUtils.getDateTimeAsLocalizedString(getDate(), pattern);
    }

}
