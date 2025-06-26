package com.npci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFraemworkExample {
    public static void main(String[] args) {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.println("Task is running " + i + " by " + threadName);
            }
            System.out.println();
        };

        // Thread t1 = new Thread(task);
        // t1.start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        executorService.execute(task);

    }
}
