package com.npci.live1;

public class Human extends LivingThing {

    public void study() {
        System.out.println("Human::study()");
    }

    // overriding the work method from LivingThing
    public void work() {
        System.out.println("Human::work()");
    }
}
