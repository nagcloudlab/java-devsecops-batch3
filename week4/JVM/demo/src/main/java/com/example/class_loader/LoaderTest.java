package com.example.class_loader;

public class LoaderTest {
    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader();
        Class<?> clazz = loader.loadClass("java.util.HashMap");
        System.out.println("Loaded class: " + clazz.getName());
    }
}
