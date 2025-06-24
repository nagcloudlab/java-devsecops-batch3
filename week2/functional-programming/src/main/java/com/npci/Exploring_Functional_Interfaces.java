package com.npci;

import java.util.function.*;
import java.util.function.Predicate;

public class Exploring_Functional_Interfaces {
    public static void main(String[] args) {

        Predicate<String> p1 = s -> {
            return s.equals("red");
        };
        boolean b = p1.test("green");
        System.out.println(b);

        Consumer<String> c1 = s -> {
            System.out.println("the color is " + s);
        };
        c1.accept("green");

        Supplier<String> s1 = () -> {
            return "red";
        };
        String color = s1.get();
        System.out.println(color);

        Function<String, String> f1 = s -> {
            return s.toUpperCase();
        };

        //-----------------------------------------------

        BiPredicate<String, String> biPredicate = (x, y) -> {
            return x.equals("red") && y.equals("green");
        };
        BiConsumer<String, String> biConsumer = (x, y) -> {
            System.out.println(x + " " + y);
        };
        BiFunction<String, String, String> biFunction = (x, y) -> {
            return x + " " + y;
        };
        ///-----------------------------------------------
        UnaryOperator<String> unaryOperator = s -> s.equals("red") ? "green" : "red";
        BinaryOperator<String> binaryOperator = (x, y) -> {
            return x + " " + y;
        };
        ///-----------------------------------------------
        // a function 2 int params, add and return result as int
        BiFunction<Integer, Integer, Integer> add = (n1, n2) -> {
            return n1 + n2;
        };
        int result = add.apply(1, 2);
        System.out.println(result);
        ///-----------------------------------------------
        IntBinaryOperator intBinaryOperator = (x, y) -> {
            return x + y;
        };
        int result2 = intBinaryOperator.applyAsInt(1, 2);
        System.out.println(result2);


        LongBinaryOperator longBinaryOperator = (x, y) -> {
            return x + y;
        };

        DoubleBinaryOperator doubleBinaryOperator = (x, y) -> {
            return x + y;
        };

        ///-----------------------------------------------

        ToIntFunction<String> stringToInt = s -> {
            return s.length();
        };

        //....

    }
}
