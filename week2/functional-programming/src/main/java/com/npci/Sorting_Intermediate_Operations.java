package com.npci;

import com.npci.model.Dish;
import com.npci.model.DishType;

import java.util.Arrays;
import java.util.List;

public class Sorting_Intermediate_Operations {
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


        menu
                .stream()
                .sorted((d1, d2) -> Double.compare(d1.getCalories(), d2.getCalories()))
                .forEach(System.out::println);


    }
}
