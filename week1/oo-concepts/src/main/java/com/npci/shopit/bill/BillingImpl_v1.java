package com.npci.shopit.bill;


import com.npci.shopit.pm.PriceMatrix;

// SRP, OCP , LSP, DIP
public class BillingImpl_v1 implements Billing {

    private final PriceMatrix priceMatrix;

    public BillingImpl_v1(PriceMatrix priceMatrix) {
        this.priceMatrix = priceMatrix;
    }

    public double getTotalPrice(String[] cart) {
        double total = 0.0;
        for (String cartItem : cart) {
            double price = priceMatrix.getPrice(cartItem);
            total += price;
        }
        return total;
    }

}
