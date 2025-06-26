package com.npci;

public class Counter {

    private int count;

    public Counter() {
        this.count = 0;
    }

    public synchronized void increment() {
        count++;
        /*
         * 
         * thread
         * - reads the value of count from heap to stack
         * increment the value of count
         * write the value of count back to heap
         * 
         */
    }

    public int getCount() {
        return count;
    }

}
