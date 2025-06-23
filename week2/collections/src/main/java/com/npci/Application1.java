package com.npci;

//class Account {
//    int number;
//
//    public Account(int number) {
//        this.number = number;
//    }
//}

// array
public class Application1 {
    public static void main(String[] args) {

        int[] numbers = new int[3];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        System.out.println(numbers[0]);
        System.out.println(numbers[1]);
        System.out.println(numbers[2]);

        //---------------------------

        int[] numbers2 = {10, 20, 30};

        //-----------------------------

        // how to loop ?

        for (int i = 0; i < numbers2.length; i++) {
            System.out.println(numbers2[i]);
        }

        // for-each loop
       /*

        for(type var:iterable){

        }

        */

        //------------------------------------------

        for (int n : numbers2) {
            System.out.println(n);
        }

        //------------------------------------------

//        int x = 5;
//        Account[] accounts = new Account[x];
//        accounts[0] = new Account(1);
//        accounts[1] = new Account(2);
//        accounts[2] = new Account(3);
//        accounts[3] = new Account(4);
//        accounts[4] = new Account(5);
//
//        for (Account account : accounts) {
//            System.out.println(account.number);
//        }

        //------------------------------------------

    }
}
