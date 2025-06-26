package com.npci;

class Building {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static void room1() {
        synchronized (lock1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is in room 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " is leaving room 1");
        }
    }

    static void room2() {
        synchronized (lock1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is in room 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " is leaving room 2");
        }
    }
}

public class How_To_Use_Synchronized_Keyword {
    public static void main(String[] args) {

        Runnable room1Task = () -> Building.room1();
        Runnable room2Task = () -> Building.room2();

        Thread thread1 = new Thread(room1Task, "Thread 1");
        Thread thread2 = new Thread(room2Task, "Thread 2");

        thread1.start();
        thread2.start();
    }

}
