package com.npci;

/*

    How to create new threads in Java?

    2 ways to create a thread in Java:

    1. On demand thread creation using Thread class ( learning purpose only)
    2. By thread-pool  ( production applications use this approach)

 */

//
//class Task implements Runnable {
//    public void run() {
//        String threadName = Thread.currentThread().getName();
//        System.out.println("Thread " + threadName + " is running");
//    }
//}

public class MultiThreadedApplication {
    public static void main(String[] args) {

        TransferService transferService = new TransferService();

        //Task task = new Task();
        Runnable task1 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread " + threadName + " is running");
            transferService.transfer("A", "B", 1000);
        };
        Runnable task2 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread " + threadName + " is running");
            transferService.transfer("C", "D", 1000);
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        // Start the threads
        thread1.start();
        thread2.start();

    }
}
