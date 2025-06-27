package com.npci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockApiExample {
    public static void main(String[] args) {

        //Object lock = new Object(); // Using a simple object as a lock
        ReentrantLock lock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


        Runnable task1 = () -> {

            try {
                //lock.lock();
                //lock.tryLock(3, TimeUnit.SECONDS);
                readWriteLock.writeLock().lock();
                System.out.println("Task 1 is running");
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                //lock.unlock();
                readWriteLock.writeLock().unlock();
                System.out.println("Task 1 has finished");
            }
        };

        Runnable task2 = () -> {

            try {
                //lock.lock();
                //readWriteLock.readLock().lock();
                readWriteLock.writeLock().lock();
                System.out.println("Task 2 is running");
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                //lock.unlock();
                readWriteLock.writeLock().unlock();
                System.out.println("Task 2 has finished");
            }
        };

        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(10);
        executorService.submit(task1);
        executorService.submit(task2);

    }
}
