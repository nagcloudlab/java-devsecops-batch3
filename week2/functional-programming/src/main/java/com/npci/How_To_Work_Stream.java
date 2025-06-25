package com.npci;

import java.util.Random;
import java.util.stream.Stream;

public class How_To_Work_Stream {
    public static void main(String[] args) {

        /*
        stream
           step-1: build/create stream from source
           step-2: compose with intermediate operations ( e.g filter, map, sorted, etc. )
           step-3: terminal operation -> to collect result ( e.g forEach, collect, reduce, etc. )
         */


        Random random = new Random();
        // build stream from source
        Stream.generate(() -> {
                    return random.nextInt();
                })
                .peek(n -> System.out.println("Generated: " + n)) // peek to see generated numbers
                // intermediate operations
                .filter(i -> i > 0)
                .peek(n -> System.out.println("Filtered: " + n)) // peek to see filtered numbers
                // terminal operations
                .forEach(System.out::println);


    }
}
