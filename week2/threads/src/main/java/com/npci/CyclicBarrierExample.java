package com.npci;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Runnable eatVegTask = () -> {
            System.out.println("eat-item-1");
            System.out.println("eat-item-2");
            System.out.println("eat-item-3");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("go for wash hands after eating veg");

        };

        Runnable eatNonVegTask = () -> {
            System.out.println("eat-item-1");
            System.out.println("eat-item-2");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("eat-item-3");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("go for wash hands after eating non-veg");

        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(eatVegTask);
        executorService.execute(eatNonVegTask);

        executorService.shutdown();

    }

}
