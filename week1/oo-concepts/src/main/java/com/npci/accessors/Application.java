package com.npci.accessors;

public class Application {
    public static void main(String[] args) {


        //-----------------------------------------
        // senior developer
        //-----------------------------------------

        Account a1 = new Account(123);
        a1.setBalance(1000.0);


        //-----------------------------------------
        // junior developer
        //-----------------------------------------
//        a1.number = -1; // Not allowed, number is private


        //-----------------------------------------
        // senior developer
        //-----------------------------------------

        System.out.println(a1.getNumber());
        System.out.println(a1.getBalance());

    }
}
