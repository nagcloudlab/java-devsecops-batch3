package com.npci.util;

// 10 years back
public interface Collection {
    // Create,Read,Update,Delete,Sort,Search
    void add(String item);

    void remove(String item);

    void clear();

    default void forEach() {
        privateMethod();
        // default. logic
    }

    static void sort(Collection collection) {

    }

    private void privateMethod() {
        // private logic
        // this method can only be accessed within this interface
    }

}
