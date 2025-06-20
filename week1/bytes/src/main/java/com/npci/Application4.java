package com.npci;

class Foo {
    static int staVar;
    int insVar;
}

public class Application4 {
    public static void main(String[] args) {

        int v = 0;
        System.out.println(v);

        System.out.println(Foo.staVar);

        Foo f = new Foo();
        System.out.println(f.insVar);

    }
}
