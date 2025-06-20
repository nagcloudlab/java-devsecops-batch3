package com.npci;

class Abc {
    // 2 types of variables
    // a. class aka static variables
    static int staVar = 1;
    // b. object aka instance variables
    int insVar = 2;

    // constructor(s)

    // 2 types of methods
    // a. class aka static methods
    static void staMethod() {
        System.out.println(staVar);
        //System.out.println(insVar);
    }

    // b. object aka instance methods
    void insMethod() {
        System.out.println(staVar);
        System.out.println(insVar);
    }
}

public class Application3 {
    public static void main(String[] args) {

        Abc.staVar = 123;
        Abc.staMethod();

        Abc abc = new Abc();
        abc.insVar = 456;
        abc.insMethod();



    }
}
