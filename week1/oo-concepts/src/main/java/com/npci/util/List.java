package com.npci.util;

public interface List extends Collection{
    void add(int index, String item);
    String get(int index);
    void set(int index, String item);
    void remove(int index);
}
