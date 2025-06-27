package com.npci;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable task = () -> {
            try {
                System.out.println("Task is running");
                Thread.sleep(2000); // Simulating some work
                countDownLatch.countDown(); // Decrement the count
                System.out.println("Task completed, count down to " + countDownLatch.getCount());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Task was interrupted");
            }
        };
        executorService.execute(task);
        executorService.execute(task);
        executorService.execute(task);


        try {
            countDownLatch.await();
            System.out.println("All tasks completed, proceeding with the next step.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
