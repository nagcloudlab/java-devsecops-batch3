package com.npci;

class Person {

    static int count = 0; // object variables

    int id; // 0
    String name; // null
    //int count = 0; // object variables

    public Person() {
    }

    void sayName() {
        //int count=0; // local variables
        count++;
        System.out.println("im " + this.name + "(" + count + ")");

    }

}

public class Application1 {
    public static void main(String[] args) {

        // values
        int v = 123;
        // object -> group of values + objects
        Person p1 = new Person();
        p1.id = 123;
        p1.name = "John";

        Person p2 = new Person();
        p2.id = 456;
        p2.name = "Jane";

        p1.sayName();
        p1.sayName();

        p2.sayName();


    }
}
