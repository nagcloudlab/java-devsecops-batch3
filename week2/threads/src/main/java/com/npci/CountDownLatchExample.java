package com.npci;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Runnable task = () -> {
            System.out.println("Task is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        executorService.execute(task);
        executorService.execute(task);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks have completed");
    }
}
