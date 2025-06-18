package com.npci.access_modifiers.package2;


import com.npci.access_modifiers.package1.A;

/**
 * author: Npci-dev-5
 */

public class E {

    A a = new A(); // HAS-A relationship with A

    public void eInstanceMethod() {
        //System.out.println(a.pri);
        //System.out.println(a.def);
        //System.out.println(a.pro);
        System.out.println(a.pub);
    }


}
