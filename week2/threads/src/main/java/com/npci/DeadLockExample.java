package com.npci;

public class DeadLockExample {
    public static void main(String[] args) {

        final String resource1 = "Resource1";
        final String resource2 = "Resource2";

        // Thread 1 tries to lock resource1 then resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked " + resource1);
                try {
                    // Simulate some work with resource1
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Trying to lock " + resource2);
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked " + resource2);
                }
            }
        });

        // Thread 2 tries to lock resource2 then resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: Locked " + resource1);
                try {
                    // Simulate some work with resource2
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Trying to lock " + resource1);
                synchronized (resource2) {
                    System.out.println("Thread 2: Locked " + resource2);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
