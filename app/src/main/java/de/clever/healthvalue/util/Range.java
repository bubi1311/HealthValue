package de.clever.healthvalue.util;

public class Range {
    private Double high;
    private Double low;

    public Range(Double low, Double high) {
        this.high = high;
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", low, high);
    }
}