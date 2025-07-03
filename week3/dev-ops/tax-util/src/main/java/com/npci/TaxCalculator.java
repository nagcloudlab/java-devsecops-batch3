package com.npci;

public class TaxCalculator {
    public static double getTax(double amount) {
        double taxRate = 0.18; // Example tax rate of 18%
        return amount * taxRate;
    }
}
