package com.example.class_loader;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        // System.out.println("ClassLoader of this class: " +
        // ClassLoaderDemo.class.getClassLoader());
        // System.out.println("ClassLoader of ArrayList: " +
        // java.util.ArrayList.class.getClassLoader());
        // System.out.println("ClassLoader of javax.crypto.Cipher: " +
        // javax.crypto.Cipher.class.getClassLoader());
        // System.out.println("ClassLoader of com.sun.tools.javac.Main: " +
        // com.sun.tools.javac.Main.class.getClassLoader());
        System.out.println("-----------------------------");
        ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
        while (cl != null) {
            System.out.println("Loader: " + cl);
            cl = cl.getParent();
        }
        System.out.println("Parent: Bootstrap ClassLoader = null");

    }
}
