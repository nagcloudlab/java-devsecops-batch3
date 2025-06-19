package com.npci.access_modifiers.package1;

/**
 * author: Npci-dev-1
 */

public class A {

    // variables
    private int pri = 1;
    int def = 2;
    protected int pro = 3;
    public int pub = 4;

    // constructors

    // methods
    public void aInstanceMethod() {
        System.out.println("A instance method");
        System.out.println("pri: " + pri);
        System.out.println("def: " + def);
        System.out.println("pro: " + pro);
        System.out.println("pub: " + pub);
    }

    // Inner class

}
