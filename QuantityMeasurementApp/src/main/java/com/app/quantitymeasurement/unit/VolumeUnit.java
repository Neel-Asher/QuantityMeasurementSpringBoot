package com.app.quantitymeasurement.unit;

public enum VolumeUnit implements Unit {

    LITER(1.0),
    MILLILITRE(0.001),
    GALLON(3.785);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}