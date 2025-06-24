package com.npci;

import com.npci.model.Apple;

import java.util.ArrayList;
import java.util.List;


// style 1: Imperative style
// solving any problem using step-by-step instructions
// intention & implementation are tightly coupled
// we can't re-use the implementation with different intentions

// style 2: Declarative style
// solving any problem by declaring what to do, not how to do it
// intention & implementation are loosely coupled
// how?
// using value | object parameters

// Intention

// Why_We_Need_Function
// To write concise code

public class Why_We_Need_Function {
    public static void main(String[] args) {

        List<Apple> appleInventory = List.of(
                new Apple("red", 150.00),
                new Apple("green", 120.00),
                new Apple("green", 130.00),
                new Apple("red", 160.00)
        );

        // Req-1: filter 'green' apples
        System.out.println(
                filterApples(appleInventory, apple -> apple.getColor().equals("green"))
        );
        // Req-2 : filter 'heavy' apples
        System.out.println(
                filterApples(appleInventory, apple -> apple.getWeight() > 150)
        );


    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate predicate) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                filteredApples.add(apple);
            }
        }
        return filteredApples;
    }


}
