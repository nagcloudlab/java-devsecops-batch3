package com.npci;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollectionsExample {
    public static void main(String[] args) {

        Vector<String> vector = new Vector<>(); // thread-safe collection
        ArrayList<String> list = new ArrayList<>(); // not thread-safe collection
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(); // use with attention to performance
        copyOnWriteArrayList.add("hello");
        copyOnWriteArrayList.add("hello");
        System.out.println(copyOnWriteArrayList.get(0)); // prints "hello"

        //...
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String,String> syncHashMap = Collections.synchronizedMap(hashMap); // synchronized wrapper around HashMap
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(); // thread-safe map

    }
}
