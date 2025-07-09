package com.example.class_loader;

class LazyLoading {
    static {
        System.out.println("LazyLoading class initialized!");
    }
}

public class Trigger {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Before loading");
        Class.forName("com.example.cl.LazyLoading"); // Triggers class loading + init
        System.out.println("After loading");
    }
}
