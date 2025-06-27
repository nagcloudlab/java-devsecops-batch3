package com.npci;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Runnable seniorTask = () -> {
            try {
                System.out.println("Senior task is running");
                Thread.sleep(2000); // Simulating some work
                System.out.println("Senior task completed");
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("senior: go home..");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Senior task was interrupted");
            }
        };

        Runnable juniorTask = () -> {
            try {
                System.out.println("Junior task is running");
                Thread.sleep(5000); // Simulating some work
                System.out.println("Junior task completed");
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("junior: go home..");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Junior task was interrupted");
            }
        };


        Thread seniorThread = new Thread(seniorTask);
        Thread juniorThread = new Thread(juniorTask);

        seniorThread.start();
        juniorThread.start();


    }
}
