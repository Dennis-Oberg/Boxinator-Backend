package com.Dennis.Boxinator.Model;

public enum CountryMultipliers {
    SWEDEN(1.3d),
    CHINA(4d),
    BRAZIL(8.6d),
    AUSTRALIA(7.2d);

    private final double value;

    CountryMultipliers(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
