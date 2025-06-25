package com.npci.model;

public enum DishType {
    MEAT,
    FISH,
    OTHER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
