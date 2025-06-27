package com.npci;


/*

    How to create/manage a thread in Java:

    way-1: Using Thread class instantiation ( for learning purpose only )
    way-2: Using thread-pool (recommended way )

 */

//class Task implements Runnable {
//    public void run() {
//        //...
//        String threadName = Thread.currentThread().getName();
//        System.out.println("Thread is running: " + threadName);
//    }
//}

public class MultiThreadApplication {
    public static void main(String[] args) {

//        Runnable task = () -> {
//            //...
//            String threadName = Thread.currentThread().getName();
//            System.out.println("Thread is running: " + threadName);
//        };
//        Thread thread = new Thread(task, "Thread-1");
//        thread.start(); // allocate new stack and start the thread

        TransferService transferService = new TransferService();

        Runnable aToB = () -> {
            transferService.transfer("A", "B", 100);
        };
        Runnable cToD = () -> {
            transferService.transfer("C", "D", 100);
        };

        Thread thread1 = new Thread(aToB, "Thread-1");
        Thread thread2 = new Thread(cToD, "Thread-2");

        thread1.start();
        thread2.start();

    }
}
