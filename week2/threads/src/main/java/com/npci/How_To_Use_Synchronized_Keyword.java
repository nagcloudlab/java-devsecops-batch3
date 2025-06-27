package com.npci;

import java.util.Vector;

class Building {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static void room1() {
        synchronized (lock1) {
            System.out.println("Room 1 is being used by " + Thread.currentThread().getName());
            try {
                Thread.sleep(30 * 1000); // Simulating some work in room 1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Room 1 is now free by " + Thread.currentThread().getName());
        }
    }

    static void room2() {
        synchronized (lock2) {
            System.out.println("Room 2 is being used by " + Thread.currentThread().getName());
            try {
                Thread.sleep(30 * 1000); // Simulating some work in room 1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Room 2 is now free by " + Thread.currentThread().getName());
        }
    }


}


public class How_To_Use_Synchronized_Keyword {
    public static void main(String[] args) {

        Runnable room1Task = () -> {
            Building.room1();
        };
        Runnable room2Task = () -> {
            Building.room2();
        };

        Thread thread1 = new Thread(room1Task, "Thread 1");
        Thread thread2 = new Thread(room2Task, "Thread 2");

        thread1.start();
        thread2.start();


        Vector<String> vector = new Vector<>(); // Using Vector as it is synchronized by default ( thread-safe)

        Runnable addDataTask = () -> {
            synchronized (vector) {
                for (int i = 0; i < 10; i++) {
                    vector.add("Data " + i);
                    System.out.println(Thread.currentThread().getName() + " added Data " + i);
                }
            }
        };
        Runnable removeDataTask = () -> {
            synchronized (vector) {
                for (int i = 0; i < 10; i++) {
                    if (!vector.isEmpty()) {
                        String data = vector.remove(0);
                        System.out.println(Thread.currentThread().getName() + " removed " + data);
                    }
                }
            }
        };

        Thread thread3 = new Thread(addDataTask, "Thread 3");
        Thread thread4 = new Thread(removeDataTask, "Thread 4");

        thread3.start();
        thread4.start();

    }
}
