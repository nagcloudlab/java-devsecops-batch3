package com.npci;

import com.npci.model.Transaction;

import java.util.ArrayList;
import java.util.List;

class FoodUtil {
    // check is the food item is vegetarian
    public static boolean isVeg(String foodItem) {
        // List of vegetarian items
        List<String> vegItems = List.of("idly", "dosa", "paneer tikka", "samosa");
        // Check if the food item is in the list of vegetarian items
        return vegItems.contains(foodItem);
    }
}

public class Exercise {
    public static void main(String[] args) {

        List<String> menu = new ArrayList<>();
        // add both veg and non-veg items to the menu
        menu.add("idly");
        menu.add("dosa");
        menu.add("biryani");
        menu.add("chicken curry");
        menu.add("fish fry");
        menu.add("paneer tikka");
        menu.add("samosa");

        // remove all all 'veg' items from the menu

    }
}
