package com.npci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {

        // connection pool size = 3

        Semaphore semaphore = new Semaphore(3);

        Runnable task1=()->{
            try {
                semaphore.acquire();
                System.out.println("Task 1 is running");
                Thread.sleep(5000); // Simulating work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Task 1 is done");
                semaphore.release();
            }
        };

        Runnable task2=()->{
            try {
                semaphore.acquire();
                System.out.println("Task 2 is running");
                Thread.sleep(2000); // Simulating work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Task 2 is done");
                semaphore.release();
            }
        };

        Runnable task3=()->{
            try {
                semaphore.acquire();
                System.out.println("Task 3 is running");
                Thread.sleep(3000); // Simulating work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Task 3 is done");
                semaphore.release();
            }
        };

        Runnable task4=()->{
            try {
                semaphore.acquire();
                System.out.println("Task 4 is running");
                Thread.sleep(1000); // Simulating work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("Task 4 is done");
                semaphore.release();
            }
        };


        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(10);
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.submit(task4);


    }
}
