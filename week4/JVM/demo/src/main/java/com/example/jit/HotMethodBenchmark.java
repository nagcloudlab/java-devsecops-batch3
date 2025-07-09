package com.example.jit;

public class HotMethodBenchmark {
    public static void main(String[] args) {
        long start = System.nanoTime();

        long result = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            result += compute(i);
        }

        long end = System.nanoTime();
        System.out.println("Result = " + result);
        System.out.println("Elapsed time (ms): " + (end - start) / 1_000_000);
    }

    public static int compute(int x) {
        return x % 10;
    }
}
