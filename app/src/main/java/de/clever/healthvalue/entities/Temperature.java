package de.clever.healthvalue.entities;

public class Temperature {

    private double bodyTemperature;
    private String scale = "C"; // C = Celsius, F = Fahrenheit

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




}
