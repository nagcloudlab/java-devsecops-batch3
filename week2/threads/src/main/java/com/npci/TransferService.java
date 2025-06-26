package com.npci;

public class TransferService {
    public void transfer(String from, String to, int amount) {
        System.out.println("Transferring " + amount + " from " + from + " to " + to);
        // Simulate some processing time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Transfer complete from " + from + " to " + to);
    }
}
