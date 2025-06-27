package com.npci;

public class ThreadLifeCycle {
    public static void main(String[] args) {

        // RUNNABLE -> RUNNING -> TERMINATED
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 1; i <= 100; i++) {
                System.out.println("Thread: " + threadName + " - Count: " + i);
                //...
                // BLOCKED
                // WAITING
                // TIMED_WAITING
            }
        };


        for (int i = 1; i <= 100; i++) {
            Thread t1 = new Thread(task, "Thread-" + i);
            t1.start();// Thread state changes to RUNNABLE after start
        }

    }
}
