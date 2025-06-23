package com.npci;

import com.npci.util.LinkedList;

import java.util.Iterator;
import java.util.Objects;

public class Application2 {
    public static void main(String[] args) {

        LinkedList<String> list = new LinkedList<>();
        list.add("person1"); // 0
        list.add("person3"); // 2
        list.add("person5");
        //list.add(123);
        list.add(1, "person2"); // 1

        // loop // traverse // iterate
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String data = it.next();
            System.out.println(data);
        }

        // java 1.5 & above
        for (String data : list) {
            System.out.println(data);
        }

    }
}
