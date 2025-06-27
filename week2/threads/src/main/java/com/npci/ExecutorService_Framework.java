package com.npci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService_Framework {
    public static void main(String[] args) {

        //ExecutorService executorService= Executors.newSingleThreadExecutor();
        //ExecutorService executorService= Executors.newFixedThreadPool(8);
        //ExecutorService executorService= Executors.newCachedThreadPool();


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable task = () -> {
            try {
                System.out.println("Task started by " + Thread.currentThread().getName());
                Thread.sleep(2000); // Simulating a long-running task
                System.out.println("Task completed by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task interrupted: " + e.getMessage());
            }
        };
        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }

        executorService.shutdown(); // Initiates an orderly shutdown of the executor service

    }
}
