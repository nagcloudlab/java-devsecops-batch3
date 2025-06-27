package com.npci;

/*

 s/w

    2 types of tasks

    - CPU bound tasks aka computational tasks
    - IO bound tasks aka read / write with external systems



    computational tasks
    # of threads <= # of cores in the system

    IO tasks
    # of threads can be more than # of cores in the system

 */

import java.util.Scanner;

public class HowManyThreadsExample {

    static void compute() {
        // Simulate a CPU bound task
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " is computing...");
        while (true) {
        }
    }

    static void io() {
        // Simulate an IO bound task
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " is doing IO...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your input: ");
        String input = scanner.nextLine();
        System.out.println("Thread " + threadName + " received input: " + input);
    }

    public static void main(String[] args) {

        //compute();
        io();


    }
}
