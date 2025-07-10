package com.example.class_loader;

public class Example {
    static int a = 10;

    static {
        System.out.println("Static block executed");
        a = 100;
    }

    public static void main(String[] args) {
        System.out.println("Main method");
    }
}
