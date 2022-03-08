package com.Dennis.Boxinator.Model;

public interface IBox {

    double calculateShippingCostBasedOnCountry(double weight);
    double roundToTwoDecimals(double shippingCost);
}
