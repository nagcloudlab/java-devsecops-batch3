package com.npci;

class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }
}

public class Application2 {
    public static void main(String[] args) {

//        Calculator calc = new Calculator();
//        int r1 = calc.add(1, 2);
//        int r2 = calc.sub(1, 2);
//        System.out.println(r1);
//        System.out.println(r2);

        int r1 = Calculator.add(1, 2);
        System.out.println(r1);
        int r2 = Calculator.sub(1, 2);
        System.out.println(r2);

    }
}
