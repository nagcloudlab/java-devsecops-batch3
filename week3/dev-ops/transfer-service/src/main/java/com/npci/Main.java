package com.npci;

public class Main {
    public static void main(String[] args) {
        double amount = 1000.0; // Example amount
        double tax = TaxCalculator.getTax(amount);
        System.out.println("Tax on amount " + amount + " is: " + tax);
    }
}