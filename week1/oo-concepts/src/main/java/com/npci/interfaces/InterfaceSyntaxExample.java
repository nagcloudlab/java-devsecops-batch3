package com.npci.interfaces;

// 10 years ago,
interface I1 {
    public static final int X = 10;
    public abstract void m1();
    public abstract void m2();
    // java 8 onwards
    public default void m5() {
        System.out.println("I1.m5() - Default Method");
    }
    // java 8 onwards
    public static void m6() {
        System.out.println("I1.m6() - Static Method");
    }
    // java 9 onwards
    private void  m7() {
        System.out.println("I1.m7() - private Method");
    }
}

interface I2 extends I1 {
    public static final int Y = 20;

    public abstract void m3();

    public abstract void m4();
}

class C1 implements I1 {
    public void m1() {
        System.out.println("C1.m1()");
    }

    public void m2() {
        System.out.println("C1.m2()");
    }
     public void m5() {
         System.out.println("C1.m5()");
     }
}

class C2 implements I2 {
    public void m1() {
        System.out.println("C2.m1()");
    }

    public void m2() {
        System.out.println("C2.m2()");
    }

    public void m3() {
        System.out.println("C2.m3()");
    }

    public void m4() {
        System.out.println("C2.m4()");
    }
}


interface A{}
interface B{}
class C3 implements A,B{}

public class InterfaceSyntaxExample {
    public static void main(String[] args) {

        //I1 i1=new I1(); // Cannot instantiate the type I1
        I1 i1 = new C1();
        //I1 i2 = new C2();


        i1.m1();
        i1.m2();
        i1.m5(); // Calls the default method from I1
        I1.m6();

    }
}
