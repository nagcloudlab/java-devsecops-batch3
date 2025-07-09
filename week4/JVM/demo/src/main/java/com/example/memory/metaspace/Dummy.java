package com.example.memory.metaspace;

// Dummy.java
public class Dummy {
    static {
        System.out.println("Dummy class initialized by: " + Dummy.class.getClassLoader());
    }
}
