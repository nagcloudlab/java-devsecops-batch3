package com.npci.shopit;

import com.npci.shopit.bill.Billing;
import com.npci.shopit.bill.BillingImpl_v1;
import com.npci.shopit.pm.ExcelPriceMatrix;
import com.npci.shopit.pm.PriceMatrix;

public class Application {
    public static void main(String[] args) {

        PriceMatrix priceMatrix = new ExcelPriceMatrix();
        Billing billing = new BillingImpl_v1(priceMatrix);

        String[] cart1 = {"123", "456", "789"};
        double totalPrice1 = billing.getTotalPrice(cart1);
        System.out.println("Total Price for cart1: " + totalPrice1);

        String[] cart2 = {"123", "456", "789", "101112"};
        double totalPrice2 = billing.getTotalPrice(cart2);
        System.out.println("Total Price for cart2: " + totalPrice2);

    }
}
