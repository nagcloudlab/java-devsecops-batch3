package com.npci;

public class Counter {
    private long count = 0;

    public synchronized void increment() {
        count++;
        /*
            thread,
            -> read current value of count from heap to CPU register
            -> increment the value in CPU register
            -> write the incremented value back to heap
         */
    }

    public long getCount() {
        return count;
    }
}
