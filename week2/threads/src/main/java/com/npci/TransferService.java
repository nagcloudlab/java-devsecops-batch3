package com.npci;

public class TransferService {
    public void transfer(String from,String to,double amount) {
        System.out.println(Thread.currentThread().getName() + " - Transferring " + amount + " from " + from + " to " + to);
        try {
            Thread.sleep(1000); // Simulating a delay for the transfer
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - Transfer completed from " + from + " to " + to);
    }
}
