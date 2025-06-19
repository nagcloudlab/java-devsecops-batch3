package com.npci.live;

/**
 * author : Nag
 */
public abstract class LivingThing {
    public void eat() {
        System.out.println("LT::eat()");
    }

    public void sleep() {
        System.out.println("LT::sleep()");
    }
    public abstract void work();
}
