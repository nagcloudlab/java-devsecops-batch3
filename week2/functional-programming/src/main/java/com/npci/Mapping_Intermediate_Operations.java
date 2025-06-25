package com.npci;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Mapping_Intermediate_Operations {
    public static void main(String[] args) {

        List<String> breakfastMenu = List.of("Pancakes", "Omelette", "Fruit Salad", "Smoothie");

        // map() operation to convert each item in the breakfast menu to uppercase
        breakfastMenu
                .stream()
                .map(item -> item.toUpperCase()) // Convert each item to uppercase
                .forEach(System.out::println);

        // flatMap() operation to flatten a list of lists

        List<Integer> numbers = List.of(1, 2, 3);
        // n- > n, n*n , n*n*n
        numbers
                .stream()
                .flatMap(n -> Stream.of(n, n * n, n * n * n))
                .filter(n -> n > 10)// Flatten the stream of numbers to include n, n*n, and n*n*n
                .forEach(System.out::println);


        List<String> menuLines = new ArrayList<>();
        menuLines.add("idly,vada,dosa");
        menuLines.add("poori,upma,chapati");
        menuLines.add("meals");
        menuLines.add("biryani,parotta");

        menuLines
                .stream()
                .flatMap(line -> Stream.of(line.split(",")))
                .map(String::toUpperCase)
                .forEach(System.out::println);


    }
}
