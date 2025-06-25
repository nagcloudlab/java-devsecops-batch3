package com.npci;

import com.npci.model.Dish;
import com.npci.model.DishType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal_Operations {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, DishType.MEAT),
                new Dish("beef", false, 700, DishType.MEAT),
                new Dish("chicken", false, 400, DishType.MEAT),
                new Dish("french fries", true, 530, DishType.OTHER),
                new Dish("rice", true, 350, DishType.OTHER),
                new Dish("season fruit", true, 120, DishType.OTHER),
                new Dish("pizza", true, 550, DishType.OTHER),
                new Dish("prawns", false, 400, DishType.FISH),
                new Dish("salmon", false, 450, DishType.FISH));

        // Terminal operations are operations that produce a result or a side-effect,
        // and they are the final step in a stream pipeline.

        //1.collect() with Collectors

        //a.toList() - Collects the elements of the stream into a List.

        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.toList())
        );

        //b.toSet() - Collects the elements of the stream into a Set.

        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.toSet())
        );

        //c.toMap() - Collects the elements of the stream into a Map.
        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.toMap(Dish::getName, Dish::getCalories))
        );

        //d.joining() - Concatenates the elements of the stream into a single String.
        System.out.println(
                menu
                        .stream()
                        .map(Dish::getName)
                        .collect(Collectors.joining(", "))
        );

        //.partitioningBy() - Partitions the elements of the stream into two groups based on a predicate.
        System.out.println(
                menu
                        .stream()
                        .collect(Collectors.partitioningBy(Dish::isVegetarian))
        );

        //.groupingBy() - Groups the elements of the stream by a classifier function.
        System.out.println(
                menu
                        .stream()
                        .collect(Collectors.groupingBy(Dish::getType))
        );

        //.counting() - Counts the number of elements in the stream.
        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .collect(Collectors.counting())
        );

        //.summarizingInt() - Collects statistics, such as count, sum, min, average, and max, for the elements of the stream.
        System.out.println(
                menu
                        .stream()
                        .collect(Collectors.summarizingInt(Dish::getCalories))
        );

        // 2. anyMatch(), allMatch(), noneMatch() -> boolean operations on the stream elements

        System.out.println(
                menu
                        .stream()
                        .anyMatch(Dish::isVegetarian) // returns true if any dish is vegetarian
        );

        System.out.println(
                menu
                        .stream()
                        .allMatch(dish -> dish.getCalories() < 1000) // returns true if all dishes have less than 1000 calories
        );

        System.out.println(
                menu
                        .stream()
                        .noneMatch(dish -> dish.getCalories() > 1000) // returns true if no dish has more than 1000 calories
        );

        // 3. findFirst(), findAny() -> returns an Optional containing the first or any element of the stream

        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .findFirst() // returns the first vegetarian dish
                        .orElse(null) // returns null if no vegetarian dish is found
        );

        System.out.println(
                menu
                        .stream()
                        .filter(Dish::isVegetarian)
                        .findAny() // returns any vegetarian dish
                        .orElse(null) // returns null if no vegetarian dish is found
        );

        // 4. reduce() -> performs a reduction on the elements of the stream using an associative accumulation function

        System.out.println(
                menu
                        .stream()
                        .map(Dish::getCalories)
                        .reduce(0, Integer::sum) // returns the total calories of all dishes
        );

        System.out.println(
                menu
                        .stream()
                        .map(Dish::getCalories)
                        .reduce(Integer::max) // returns the maximum calories of all dishes
                        .orElse(0) // returns 0 if no dishes are present
        );

        // 5. forEach() -> performs an action for each element of the stream

        menu
                .stream()
                .filter(Dish::isVegetarian)
                .forEach(dish -> System.out.println("Vegetarian Dish: " + dish.getName())); // prints the name of each vegetarian dish

    }

}
