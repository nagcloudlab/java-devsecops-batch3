package com.npci;

import com.npci.model.Dish;
import com.npci.model.DishType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {
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


        // Req-1 : get low calorie (calories < 400), sorted by calories asc, dish's names,

        System.out.println(
//                getLowCalorieDishNames_v1(menu)
                getLowCalorieDishNames_v2(menu)
        );

    }

    //--------------------------
    // data processing
    //--------------------------
    // filtering
    // sorting
    // mapping aka transforming
    // grouping
    // partitioning
    // aggregating
    // statistics
    // anyMatch, allMatch, noneMatch
    // ... and many more

    // data processing pipeline in imperative style

    // problem with imperative style:
    // -> too much code
    // -> hard to read
    // -> hard to parallelize
    // -> hard to maintain
    // -> hard to test

    // solution: use functional style programming with Java Streams API

    private static List<String> getLowCalorieDishNames_v1(List<Dish> menu) {
        // step-1: filter low calorie dishes ( calories < 400 )
        List<Dish> lowCalorieDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalorieDishes.add(d);
            }
        }
        // step-2: sort low calorie dishes by calories
        class CaloriesComparator implements java.util.Comparator<Dish> {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        }
        lowCalorieDishes.sort(new CaloriesComparator());
        // step-3: get dish names
        List<String> lowCalorieDishNames = new ArrayList<>();
        for (Dish d : lowCalorieDishes) {
            lowCalorieDishNames.add(d.getName());
        }
        return lowCalorieDishNames;
    }

    private static List<String> getLowCalorieDishNames_v2(List<Dish> menu) {
        return menu
                .stream()
                .filter(d -> d.getCalories() < 400)
                .sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

}


