package com.npci;

// SAM
@FunctionalInterface
interface FI {
    void m();
}

class C1 implements FI {
    public void m() {
        System.out.println("C1->m()");
    }

}

// Using SAM interfaces, we can create function in java
public class How_To_Create_Function {
    public static void main(String[] args) {

        // Way-1 : Named class
        FI fi = new C1();
        fi.m();

        // Way-2 : Anonymous class
        FI fi2 = new FI() {
            public void m() {
                System.out.println("AC->m()");
            }
        };
        fi2.m();

        // Function aka Lambda Expression -> an implementation of Functional Interface
        FI fi3 = () -> {
        };
        fi3.m();

    }
}
