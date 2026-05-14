package com.app.quantitymeasurement.unit;

public enum LengthUnit implements Unit {

    METER(1.0),
    CENTIMETER(0.01),
    KILOMETER(1000.0),
    FEET(0.3048),
    INCHES(0.0254),
    YARDS(0.9144);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}