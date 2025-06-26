package com.npci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);

        Runnable task1 = () -> {
            try {
                semaphore.acquire();
                System.out.println("Task 1 is running");
                Thread.sleep(5000);
                System.out.println("Task 1 is completed");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        Runnable task2 = () -> {
            try {
                semaphore.acquire();
                System.out.println("Task 2 is running");
                Thread.sleep(5000);
                System.out.println("Task 2 is completed");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task3 = () -> {
            try {
                semaphore.acquire();
                System.out.println("Task 3 is running");
                Thread.sleep(5000);
                System.out.println("Task 3 is completed");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);

        executorService.shutdown();

    }

}
