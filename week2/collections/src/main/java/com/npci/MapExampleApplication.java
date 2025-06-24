package com.npci;

import com.npci.model.Account;
import com.npci.model.Car;
import com.npci.model.Owner;
import com.npci.model.Transaction_History;

import java.util.*;

public class MapExampleApplication {

    public static void main(String[] args) {

        // TreeMap
        // HashMap
        // LinkedHashMap
        // hashtable  ( legacy class ) (synchronized)

        //---------------------------------------------------------

//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("soundar", "vdZvcvcZvcjahcZJHcvzHcc");
//        map.put("venkat", "adasdasdasd");

        //----------------------------------------------------------

//        String data = map.get("soundar");
//        System.out.println("Data for key 'soundar': " + data);

        //----------------------------------------------------------

//        Owner owner1 = new Owner("Soundar", "Universe", "1234567890");
//        Owner owner2 = new Owner("Venkat", "Earth", "0987654321");
//
//        Car car1 = new Car("Honda", "City", 2020);
//        Car car2 = new Car("Toyota", "Corolla", 2021);
//
//        //Map<Owner, Car> carsMap = new TreeMap<>(); // key must be Comparable
//        Map<Owner, Car> carsMap = new HashMap<>(); // key must implement equals() and hashCode()
//        carsMap.put(owner1, car1);
//        carsMap.put(owner2, car2);

        //-----------------------------------------------------------


//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter owner name to search for car:");
//        String ownerName = input.nextLine();
//        System.out.println("Enter owner contact number to search for car:");
//        String ownerContact = input.nextLine();
//
//        Owner key = new Owner(ownerName, "", ownerContact);
//        Car car = carsMap.get(key);
//        if (car != null) {
//            System.out.println("Car found: " + car);
//        } else {
//            System.out.println("No car found for the given owner.");
//        }


        //------------------------------------------------------------

        Account account1 = new Account("1234567890", "Soundar", 10000.0);
        Account account2 = new Account("0987654321", "Venkat", 20000.0);

        List<Transaction_History> account1_transactions = new ArrayList<>();
        account1_transactions.add(new Transaction_History("txn_1", "1234567890", "credit", 5000.0, "2023-10-01"));
        account1_transactions.add(new Transaction_History("txn_2", "1234567890", "debit", 2000.0, "2023-10-02"));

        List<Transaction_History> account2_transactions = new ArrayList<>();
        account2_transactions.add(new Transaction_History("txn_3", "0987654321", "credit", 10000.0, "2023-10-01"));
        account2_transactions.add(new Transaction_History("txn_4", "0987654321", "debit", 5000.0, "2023-10-02"));

        Map<Account, List<Transaction_History>> txns = new HashMap<>();
        txns.put(account1, account1_transactions);
        txns.put(account2, account2_transactions);

        //-------------------------------------------------------------

        List<Transaction_History> transactionHistoryList = txns.get(account1);
        if (transactionHistoryList != null) {
            System.out.println("Transaction history for account " + account1.getAccountNumber() + ":");
            for (Transaction_History txn : transactionHistoryList) {
                System.out.println(txn);
            }
        } else {
            System.out.println("No transactions found for the given account.");
        }


    }

}
