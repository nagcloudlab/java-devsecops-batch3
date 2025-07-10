package com.example.memory.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapStressDemo {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        List<byte[]> survivors = new ArrayList<>();
        int iteration = 0;

        while (true) {

            // Allocate 5 MB at a time
            byte[] chunk = new byte[5 * _1MB]; // 5Mb // Eden Space allocation

            if (iteration % 5 == 0) {
                survivors.add(chunk); // keep references to push into Old Gen
            }

            if (survivors.size() > 10) {
                survivors.clear(); // allow Old Gen to be GC'd
                System.out.println("Cleared survivor list");
            }

            System.out.println("Allocated chunk #" + (++iteration));
            // Thread.sleep(200); // slow down allocation
        }
    }
}