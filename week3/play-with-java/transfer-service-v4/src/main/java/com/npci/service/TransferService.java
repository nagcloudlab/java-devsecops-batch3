package com.npci.service;

public interface TransferService {

    String initiateTransfer(String fromAccountNumber, String toAccountNumber, double amount);

}
