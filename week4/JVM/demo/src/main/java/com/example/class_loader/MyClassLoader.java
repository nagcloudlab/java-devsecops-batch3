package com.example.class_loader;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Custom class loader loading: " + name);
        return super.findClass(name);
    }
}
