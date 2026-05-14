package com.app.quantitymeasurement.unit;

public enum TemperatureUnit implements Unit {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    public double toBase(double value) {
        switch (this) {
            case CELSIUS:
                return value;
            case FAHRENHEIT:
                return (value - 32) * 5 / 9;
            case KELVIN:
                return value - 273.15;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }
    }

    public double fromBase(double baseValue) {
        switch (this) {
            case CELSIUS:
                return baseValue;
            case FAHRENHEIT:
                return (baseValue * 9 / 5) + 32;
            case KELVIN:
                return baseValue + 273.15;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }
    }
}