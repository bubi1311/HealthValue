package de.clever.healthvalue.entities;

public class Temperature {

    private double bodyTemperature;
    private String scale; // C = Celsius, F = Fahrenheit
    private long id;

    public Temperature(double bodyTemperature, String scale, long id) {
        this.bodyTemperature = bodyTemperature;
        this.scale = scale;
        this.id = id;
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


}
