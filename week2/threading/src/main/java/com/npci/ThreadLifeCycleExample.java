package com.npci;

public class ThreadLifeCycleExample {

    public static void main(String[] args) {

        // Runnable state to Running state
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println("Thread Name: " + threadName + " - Count: " + i);
                // -> BLOCKED state , if resource is locked by another thread
                // -> WAITING state , if notification expected from another thread
                // -> TIMED_WAITING state , if waiting for a specific time
                if (threadName.equals("Thread-1")) {
                    try {
                        Thread.sleep(1000); // Thread goes to TIMED_WAITING state
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (threadName.equals("Thread-2")) {
                    try {
                        Thread.sleep(2000); // Thread goes to TIMED_WAITING state
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            // Thread goes to Terminated state after completion
        };


        Thread thread1 = new Thread(task, "Thread-1"); // New state
        System.out.println(thread1.getState());
        Thread thread2 = new Thread(task, "Thread-2");
        System.out.println(thread2.getState());

        // New state to Runnable state , 1MB memory allocated , to create method-frame , method frame hold local
        thread1.start();
        thread2.start();

    }

}
