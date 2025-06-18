package com.npci.access_modifiers.package1;


/**
 * author: Npci-dev-3
 */

public class C {

    A a = new A(); // HAS-A relationship with A

    public void cInstanceMethod() {
        //System.out.println(a.pri);
        System.out.println(a.def);
        System.out.println(a.pro);
        System.out.println(a.pub);

    }

}