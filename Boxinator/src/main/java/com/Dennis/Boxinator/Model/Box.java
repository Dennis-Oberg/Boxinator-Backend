package com.Dennis.Boxinator.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Box implements IBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boxID;
    private String boxName;
    private String country;
    private double weightInKiloGrams;
    private String containerColour;
    private double shippingCost; //I assume this could also be BigDecimal

    public Box() {

    }

    //for testing
    public Box(long id, String boxName, String destinationCountry, double weightInKiloGrams, String containerColour, double shippingCost) {
        this.boxID = id;
        this.boxName = boxName;
        this.country = destinationCountry;
        this.weightInKiloGrams = weightInKiloGrams;
        this.containerColour = containerColour;
        this.shippingCost = shippingCost;
    }
    //for testing
    public Box(String boxName, String country, double weightInKiloGrams, String containerColour) {
        this.boxName = boxName;
        this.country = country;
        this.weightInKiloGrams = weightInKiloGrams;
        this.containerColour = containerColour;
        this.shippingCost = calculateShippingCostBasedOnCountry(this.weightInKiloGrams);
    }
    //for testing
    public Box(Long id,String boxName, String country, double weightInKiloGrams, String containerColour) {
        this.boxID = id;
        this.boxName = boxName;
        this.country = country;
        this.weightInKiloGrams = weightInKiloGrams;
        this.containerColour = containerColour;
        this.shippingCost = calculateShippingCostBasedOnCountry(this.weightInKiloGrams);
    }

    @Override
    public double calculateShippingCostBasedOnCountry(double weight) {

        switch (this.country.toLowerCase()) {
            case "sweden":
                return weight * CountryMultipliers.SWEDEN.getValue();

            case "china":
                return weight * CountryMultipliers.CHINA.getValue();

            case "brazil":
                return weight * CountryMultipliers.BRAZIL.getValue();

            case "australia":
                return weight * CountryMultipliers.AUSTRALIA.getValue();

            default:
                throw new NullPointerException(String.format(this.country, " is not included." ));
        }
    }

    @Override
    public double roundToTwoDecimals(double shippingCost) {

        return BigDecimal.valueOf(shippingCost).setScale(2,
                RoundingMode.HALF_UP).doubleValue();
    }

    public boolean validateWeigth(double weight) {
        return !(weight < 0);
    }


    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String destinationCountry) {
        this.country = destinationCountry;
    }

    public double getWeightInKiloGrams() {
        return weightInKiloGrams;
    }

    public void setWeightInKiloGrams(double weightInKiloGrams) {
        this.weightInKiloGrams = weightInKiloGrams;
    }

    public String getContainerColour() {
        return containerColour;
    }

    public void setContainerColour(String containerColour) {
        this.containerColour = containerColour;
    }

    @Id
    public long getId() {
        return boxID;
    }

    public void setId(long id) {
        this.boxID = id;
    }


}
