package com.npci;

public class RaceConditionExample {
    public static void main(String[] args) {

        Counter counter = new Counter();

        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(incrementTask, "Thread 1");
        Thread thread2 = new Thread(incrementTask, "Thread 2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount()); // 2000
    }
}
