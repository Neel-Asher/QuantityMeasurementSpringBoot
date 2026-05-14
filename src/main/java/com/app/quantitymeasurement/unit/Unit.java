package com.app.quantitymeasurement.unit;

public interface Unit {
    double toBase(double value);
    double fromBase(double baseValue);
}