package com.app.quantitymeasurement.unit;

public class Quantity implements IMeasurable {

    private double value;
    private Unit unit;

    public Quantity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    public double toBase() {
        return unit.toBase(value);
    }

    public static Quantity fromBase(double baseValue, Unit unit) {
        return new Quantity(unit.fromBase(baseValue), unit);
    }

    public Quantity add(Quantity other) {
    	validateSameType(other);
        double resultBase = this.toBase() + other.toBase();
        return fromBase(resultBase, this.unit);
    }

    private static final double EPSILON = 1e-6;

    public boolean compare(Quantity other) {
        validateSameType(other);
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }
    
    public Quantity subtract(Quantity other) {
        validateSameType(other);
        double resultBase = this.toBase() - other.toBase();
        return fromBase(resultBase, this.unit);
    }
    
    private void validateSameType(Quantity other) {
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new RuntimeException("Cannot operate on different measurement types");
        }
    }
}