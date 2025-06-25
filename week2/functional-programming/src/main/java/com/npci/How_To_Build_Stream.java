package com.npci;

import java.io.IOException;
import java.util.Random;
import java.util.stream.Stream;

public class How_To_Build_Stream {
    public static void main(String[] args) throws IOException {

        // common data sources
        // values
        // collections
        // files
        // db resultset
        // network sockets
        // kafka topics

        //----------------------------
        // Finite Stream
        //----------------------------

        // stream from value(s)
        Stream.of("idly", "dosa", "vada", "poori")
                .forEach(System.out::println);
        //----------------------------
        // stream from array
        String[] southIndianBreakfast = {"idly", "dosa", "vada", "poori"};
        Stream.of(southIndianBreakfast)
                .forEach(System.out::println);
        //----------------------------
        // stream from collection
        java.util.List<String> southIndianBreakfastList = java.util.List.of("idly", "dosa", "vada", "poori");
        southIndianBreakfastList.stream()
                .forEach(System.out::println);
        //----------------------------
        // stream from file
        java.nio.file.Files.lines(java.nio.file.Path.of("/Users/nag/java-devsecops-batch3/week2/functional-programming/menu.txt"))
                .forEach(System.out::println);
        //----------------------------

        //-----------------------------
        // infinite Stream
        //-----------------------------

//        Random random = new Random();
//        Stream.generate(() -> {
//                    return random.nextInt(); // range: -2,147,483,648 to 2,147,483,647
//                })
//                .forEach(System.out::println);

        Stream.iterate(0, n -> n + 1)
                .forEach(System.out::println);


    }
}
