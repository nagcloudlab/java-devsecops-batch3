package com.npci;


public class Wait_Notify_Example {

    static Object lock = new Object();
    static int[] buffer = new int[10]; // shared buffer
    static int count = 0;

    public static void main(String[] args) {

        // wait and notify example

        // Produce task e.g produce 50 items
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (count == buffer.length) { // buffer is full
                        try {
                            lock.wait(); // wait for consumer to consume
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    buffer[count++] = i; // produce item
                    System.out.println("Produced: " + i);
                    lock.notify(); // notify consumer
                }
            }
        });

        // consume task e.g consume 45 items
        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 45; i++) {
                synchronized (lock) {
                    while (count == 0) { // buffer is empty
                        try {
                            lock.wait(); // wait for producer to produce
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int item = buffer[--count]; // consume item
                    // slow consumer
                    try {
                        Thread.sleep(2000); // simulate processing time
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Consumed: " + item);
                    lock.notify(); // notify producer
                }
            }
        });

        producerThread.start();
        consumerThread.start();

    }
}